package fullstack.api.vendas.curso.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fullstack.api.vendas.curso.model.Venda;
import fullstack.api.vendas.curso.model.repository.projections.VendasPorMes;

public interface VendasRepository extends JpaRepository<Venda, Long> {

	@Query(nativeQuery = true, value = "select "
			+ " extract(month from v.data_cadastro) as mes, "
			+ " sum(v.total) as valor from venda as v"
			+ " where extract(year from v.data_cadastro) = :ano "
			+ " group by extract(month from v.data_cadastro)"
			+ " order by extract(month from v.data_cadastro)")
	List<VendasPorMes> obterVendasPorMes(@Param("ano") Integer ano);

}
