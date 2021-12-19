package fullstack.api.vendas.curso.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Cliente() {
		super();
	}
	
	public Cliente(LocalDate dataNascimento, String nome, String cpf, String endereco, String telefone, String email) {
		super();
		this.dataNascimento = dataNascimento;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
	}

	public Cliente(Long id, LocalDate dataNascimento, LocalDate dataCadastro, String nome, String cpf, String endereco,
			String telefone, String email) {
		super();
		this.id = id;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
	}

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;
	@NonNull
	@Column(length = 100)
	private String nome;
	@NonNull
	@Column(length = 14)
	private String cpf;
	@NonNull
	@Column(length = 255)
	private String endereco;
	@Column(length = 14)
	private String telefone;
	@Column(length = 100)
	private String email;
	
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}
	
	public Long getId() {
		return id;
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
