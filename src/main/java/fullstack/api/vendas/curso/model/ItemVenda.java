package fullstack.api.vendas.curso.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "item_venda")
public class ItemVenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@NonNull
	@JoinColumn(name = "id_venda")
	private Venda venda;
	@ManyToOne
	@NonNull
	@JoinColumn(name = "id_produto")
	private Produto produto;
	@Column
	@NonNull
	private Integer quantidade;

	public ItemVenda() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
