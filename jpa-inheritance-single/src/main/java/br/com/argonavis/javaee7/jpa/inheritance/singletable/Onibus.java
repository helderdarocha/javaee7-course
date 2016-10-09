package br.com.argonavis.javaee7.jpa.inheritance.singletable;

import javax.persistence.Entity;

@Entity
public class Onibus extends Veiculo {
	private String placa;
	private String tipo;
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
