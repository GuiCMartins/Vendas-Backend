package fullstack.api.vendas.curso.rest.vendas;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fullstack.api.vendas.curso.model.Venda;
import fullstack.api.vendas.curso.model.repository.ItemVendasRepository;
import fullstack.api.vendas.curso.model.repository.VendasRepository;
import fullstack.api.vendas.curso.service.RelatorioVendasService;
import fullstack.api.vendas.curso.util.DateUtils;

@RestController
@RequestMapping("/api/vendas")
@CrossOrigin("*")
public class VendasController {

	@Autowired
	private VendasRepository repository;
	@Autowired
	private ItemVendasRepository itemVendaRepository;
	@Autowired
	private RelatorioVendasService relatorioVendasService;

	@PostMapping
	@Transactional
	public void realizarVenda(@RequestBody Venda venda) {
		try {
			repository.save(venda);
			venda.getItens().stream().forEach((item) -> item.setVenda(venda));
			itemVendaRepository.saveAll(venda.getItens());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@GetMapping("/relatorios-vendas")
	public ResponseEntity<byte[]> relatorioVendas(
			@RequestParam(value = "id", required = false, defaultValue = "") Long id,
			@RequestParam(value = "inicio", required = false, defaultValue = "") String inicio,
			@RequestParam(value = "fim", required = false, defaultValue = "") String fim) {

		Date dataInicio = DateUtils.fromStringDate(inicio);
		Date dataFim = DateUtils.fromStringDate(fim);
		
		if(dataInicio == null) {
			dataInicio = DateUtils.DATA_INICIO_PADRAO;
		}
		
		if(dataFim == null) {
			dataFim = DateUtils.DATA_FIM_PADRAO;
		}
		
		byte[] relatorioGerado = relatorioVendasService.gerarRelatorio(id, dataInicio, dataFim);

		HttpHeaders headers = new HttpHeaders();
		String fileName = "relatorio-vendas.pdf";

		headers.setContentDispositionFormData("inline; filename=\"" + fileName + "\"", fileName);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

		return new ResponseEntity<>(relatorioGerado, headers, HttpStatus.OK);
	}

}
