package fullstack.api.vendas.curso.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fullstack.api.vendas.curso.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
