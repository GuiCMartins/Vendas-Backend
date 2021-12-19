package fullstack.api.vendas.curso.model.repository.projections;

import java.math.BigDecimal;

public interface VendasPorMes {
	Integer getMes();
	BigDecimal getValor();
}
