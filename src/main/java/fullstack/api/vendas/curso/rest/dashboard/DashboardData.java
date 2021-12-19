package fullstack.api.vendas.curso.rest.dashboard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fullstack.api.vendas.curso.model.repository.projections.VendasPorMes;

public class DashboardData {

	private Long produtos;
	private Long clientes;
	private Long vendas;
	private List<VendasPorMes> vendasPorMes;

	public DashboardData(Long produtos, Long clientes, Long vendas, List<VendasPorMes> vendasPorMes) {
		super();
		this.produtos = produtos;
		this.clientes = clientes;
		this.vendas = vendas;
		this.vendasPorMes = vendasPorMes;
		this.preencherMesesFaltantes();
	}

	public Long getProdutos() {
		return produtos;
	}

	public void setProdutos(Long produtos) {
		this.produtos = produtos;
	}

	public Long getClientes() {
		return clientes;
	}

	public void setClientes(Long clientes) {
		this.clientes = clientes;
	}

	public Long getVendas() {
		return vendas;
	}

	public void setVendas(Long vendas) {
		this.vendas = vendas;
	}

	public List<VendasPorMes> getVendasPorMes() {
		if (vendasPorMes == null) {
			vendasPorMes = new ArrayList<>();
		}
		return vendasPorMes;
	}

	public void setVendasPorMes(List<VendasPorMes> vendasPorMes) {
		this.vendasPorMes = vendasPorMes;
	}

	public void preencherMesesFaltantes() {
		if (!getVendasPorMes().isEmpty()) {

			int mesMaximo = getVendasPorMes().stream().mapToInt(VendasPorMes::getMes).max().getAsInt();

			List<Integer> listaMeses = IntStream.rangeClosed(1, mesMaximo).boxed().collect(Collectors.toList());

			List<Integer> mesesAdicionados = getVendasPorMes().stream().map(VendasPorMes::getMes)
					.collect(Collectors.toList());

			listaMeses.stream().forEach(mes -> {
				if (!mesesAdicionados.contains(mes)) {
					VendasPorMes vendaPorMes = new VendasPorMes() {

						@Override
						public BigDecimal getValor() {
							return BigDecimal.ZERO;
						}

						@Override
						public Integer getMes() {
							return mes;
						}
					};

					getVendasPorMes().add(vendaPorMes);
				}
			});

			getVendasPorMes().sort(Comparator.comparing(VendasPorMes::getMes));
		}
	}
}
