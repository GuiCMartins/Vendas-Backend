package fullstack.api.vendas.curso.rest.clientes;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import fullstack.api.vendas.curso.model.Cliente;

public class ClienteFormRequest {

	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	private String nome;
	private String cpf;
	private String telefone;
	private String endereco;
	private String email;
	public Long getId() {
		return id;
	}
	
	public ClienteFormRequest() {
		super();
	}

	public ClienteFormRequest(Long id, LocalDate dataNascimento, LocalDate dataCadastro, String nome, String cpf,
			String telefone, String endereco, String email) {
		super();
		this.id = id;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Cliente toModel() {
		return new Cliente(id, dataNascimento, dataCadastro, nome, cpf, endereco, telefone, email);
	}
	
	public static ClienteFormRequest fromModel(Cliente cliente) {
		return new ClienteFormRequest(cliente.getId(), cliente.getDataNascimento(), cliente.getDataCadastro(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEndereco(), cliente.getEmail());
	}
}
