package fullstack.api.vendas.curso.service;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperRunManager;

@Service
public class RelatorioVendasService {

	@Value("classpath:reports/relatorio_vendas.jasper")
	private Resource relatorioVendas;

	@Autowired
	private DataSource dataSource;

	public byte[] gerarRelatorio(Long idCliente, Date dataInicio, Date dataFim) {
		try (Connection con = dataSource.getConnection();) {
			Map<String, Object> parametros = new HashMap<>();
			parametros.put("ID_CLIENTE", idCliente);
			parametros.put("DATA_INICIO", dataInicio);
			parametros.put("DATA_FIM", dataFim);
			return JasperRunManager.runReportToPdf(relatorioVendas.getInputStream(), parametros, con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
