package br.com.argonavis.javaee7.jsf.beanvalidation;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class Destinatario implements Serializable {

	private static final long serialVersionUID = 7544685274681209396L;
	
	private Endereco endereco = new Endereco();
	private String nome;
	@NotNull
	private String email;
	@NotNull
	private String telefone;
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
