package fullstack.api.vendas.curso.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fullstack.api.vendas.curso.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query(value = "select c from Cliente c where upper(c.nome) like upper(:nome) and c.cpf like :cpf")
	Page<Cliente> buscarPorNomeCpf(@Param("nome") String nome, @Param("cpf") String cpf, Pageable pageable);
	
}
