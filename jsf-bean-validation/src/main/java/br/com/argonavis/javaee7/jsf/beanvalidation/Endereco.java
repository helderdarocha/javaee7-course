package br.com.argonavis.javaee7.jsf.beanvalidation;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 8553133272700447275L;
	
	@NotNull
	private String rua;
	
	@NotNull
	private int numero;
	
	@NotNull
	@Pattern(message="Formato do CEP é NNNNN-NNN", regexp="\\d{5}-\\d{3}")
	private String cep;
	
	@NotNull(message="Cidade é obrigatória")
	private String cidade;
	
	@NotNull(message="UF é obrigatória")
	@Size(min=2, max=2)
	private String uf;
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

}
