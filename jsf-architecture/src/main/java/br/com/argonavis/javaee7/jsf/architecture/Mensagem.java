package br.com.argonavis.javaee7.jsf.architecture;

import java.io.Serializable;

public class Mensagem implements Serializable {
	private static final long serialVersionUID = 8629670118250949768L;
	
	private String valor;
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public int hashCode() {
		return valor.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		return ((Mensagem)obj).valor == valor;
	}
	@Override
	public String toString() {
		return valor;
	}
	

}
