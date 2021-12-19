package fullstack.api.vendas.curso.rest.produtos;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fullstack.api.vendas.curso.model.Produto;
import fullstack.api.vendas.curso.model.repository.ProdutoRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@GetMapping
	public List<ProdutoFormRequest> getLista() {
		return repository.findAll().stream().map(p -> ProdutoFormRequest.fromModel(p)).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoFormRequest> getById(@PathVariable Long id) {
		try {
			Produto produto = repository.getById(id);
			
			ProdutoFormRequest p = ProdutoFormRequest.fromModel(produto);

			return ResponseEntity.ok(p);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ProdutoFormRequest salvar(@RequestBody ProdutoFormRequest produto) {
		Produto p = produto.toModel();
		repository.save(p);

		return ProdutoFormRequest.fromModel(p);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ProdutoFormRequest produto) {

		try {
			Produto antigoProduto = repository.getById(id);
			Produto novoProduto = produto.toModel();

			novoProduto.setId(antigoProduto.getId());
			novoProduto.setDataCadastro(antigoProduto.getDataCadastro());
			repository.save(novoProduto);

			return ResponseEntity.ok().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {

		try {
			Produto produto = repository.getById(id);
			
			repository.delete(produto);

			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
