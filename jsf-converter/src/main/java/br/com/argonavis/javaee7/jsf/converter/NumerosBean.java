package br.com.argonavis.javaee7.jsf.converter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class NumerosBean implements Serializable {

	private static final long serialVersionUID = -6006657680237638467L;

	private BigDecimal total = BigDecimal.valueOf(46392);
	private Double numero = 533232.33;
	private Integer contagem = 45395435;
	private List<BigDecimal> precos = new ArrayList<BigDecimal>();
	
	@PostConstruct
	public void init() {
		precos.add(BigDecimal.valueOf(334.54));
		precos.add(BigDecimal.valueOf(734.32));
		precos.add(BigDecimal.valueOf(23.94));
		precos.add(BigDecimal.valueOf(233.08));
		precos.add(BigDecimal.valueOf(153453.12));
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Double getNumero() {
		return numero;
	}
	public void setNumero(Double numero) {
		this.numero = numero;
	}
	public Integer getContagem() {
		return contagem;
	}
	public void setContagem(Integer contagem) {
		this.contagem = contagem;
	}
	public List<BigDecimal> getPrecos() {
		return precos;
	}
	public void setPrecos(List<BigDecimal> precos) {
		this.precos = precos;
	}
	
}
