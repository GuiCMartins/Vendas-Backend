package fullstack.api.vendas.curso.rest.dashboard;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fullstack.api.vendas.curso.model.repository.ClienteRepository;
import fullstack.api.vendas.curso.model.repository.ProdutoRepository;
import fullstack.api.vendas.curso.model.repository.VendasRepository;
import fullstack.api.vendas.curso.model.repository.projections.VendasPorMes;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private VendasRepository vendasRepository;
	
	@GetMapping
	public DashboardData getDashboard() {
		long vendasCount = vendasRepository.count();
		long produtosCount = produtoRepository.count();
		long clientesCount = clienteRepository.count();
		
		int anoCorrente = LocalDate.now().getYear();
		List<VendasPorMes> vendasPorMes = vendasRepository.obterVendasPorMes(anoCorrente);
		
		return new DashboardData(produtosCount, clientesCount, vendasCount, vendasPorMes);
	}
}
