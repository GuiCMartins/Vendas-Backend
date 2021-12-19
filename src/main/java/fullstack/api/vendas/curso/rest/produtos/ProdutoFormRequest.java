package fullstack.api.vendas.curso.rest.produtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import fullstack.api.vendas.curso.model.Produto;

public class ProdutoFormRequest {

	private Long id;
	private String sku;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate cadastro;

	public ProdutoFormRequest() {
	}

	public ProdutoFormRequest(Long id, String sku, String nome, String descricao, BigDecimal preco,
			LocalDate cadastro) {
		super();
		this.id = id;
		this.sku = sku;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.cadastro = cadastro;
	}

	public Produto toModel() {
		return new Produto(id, sku, nome, descricao, preco);
	}

	public static ProdutoFormRequest fromModel(Produto produto) {
		return new ProdutoFormRequest(produto.getId(), produto.getSku(), produto.getNome(), produto.getDescricao(),
				produto.getPreco(), produto.getDataCadastro());
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getCadastro() {
		return cadastro;
	}

	public void setCadastro(LocalDate cadastro) {
		this.cadastro = cadastro;
	}

}
