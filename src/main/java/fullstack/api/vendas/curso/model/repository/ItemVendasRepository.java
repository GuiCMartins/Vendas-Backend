package fullstack.api.vendas.curso.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fullstack.api.vendas.curso.model.ItemVenda;

public interface ItemVendasRepository extends JpaRepository<ItemVenda, Long>{

}
