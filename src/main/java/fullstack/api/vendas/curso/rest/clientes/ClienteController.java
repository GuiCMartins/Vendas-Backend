package fullstack.api.vendas.curso.rest.clientes;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fullstack.api.vendas.curso.model.Cliente;
import fullstack.api.vendas.curso.model.repository.ClienteRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@PostMapping
	public ResponseEntity<ClienteFormRequest> salvar(@RequestBody ClienteFormRequest cliente) {
		Cliente novoCliente = cliente.toModel();
		repository.save(novoCliente);
		return ResponseEntity.ok(ClienteFormRequest.fromModel(novoCliente));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ClienteFormRequest cliente) {
		try {
			Cliente antigoCliente = repository.getById(id);
			Cliente novoCliente = cliente.toModel();

			novoCliente.setId(antigoCliente.getId());
			repository.save(novoCliente);

			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteFormRequest> getById(@PathVariable Long id) {
		return repository.findById(id).map(ClienteFormRequest::fromModel).map(response -> ResponseEntity.ok(response))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		return repository.findById(id).map(response -> {
			repository.delete(response);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public Page<ClienteFormRequest> getLista(@RequestParam(value="nome", required = false, defaultValue = "") String nome, @RequestParam(value="cpf", required = false, defaultValue = "") String cpf, Pageable pageable) {
		return repository.buscarPorNomeCpf("%"+nome+"%", "%"+cpf+"%", pageable).map(ClienteFormRequest::fromModel);
	}
}
