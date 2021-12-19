package fullstack.api.vendas.curso.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Produto() {
	}

	public Produto(Long id, String sku, String nome, String descricao, BigDecimal preco) {
		super();
		this.id = id;
		this.sku = sku;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String sku;
	
	@Column(length = 100)
	private String nome;
	
	@Column(length = 255)
	private String descricao;
	
	@Column(precision = 16, scale = 2)
	private BigDecimal preco;
	
	@Column(name = "data_cadasto")
	private LocalDate dataCadastro;

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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
