package fullstack.api.vendas.curso.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "venda")
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NonNull
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	@NonNull
	@Column(name = "forma_pagamento")
	private FormaPagamento formaPagamento;

	@OneToMany(mappedBy = "venda")
	private List<ItemVenda> itens;

	@Column
	@NonNull
	private BigDecimal total;
	
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

	public Venda() {
		super();
	}

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
