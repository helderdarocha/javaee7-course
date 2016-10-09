package br.com.argonavis.javaee7.jpa.inheritance.singletable;

import javax.persistence.Entity;

@Entity
public class Aviao extends Veiculo {
	private String modelo;
	private String prefixo;
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPrefixo() {
		return prefixo;
	}
	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}
	
}
