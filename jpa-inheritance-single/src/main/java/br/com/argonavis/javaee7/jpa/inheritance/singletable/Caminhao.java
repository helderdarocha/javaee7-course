package br.com.argonavis.javaee7.jpa.inheritance.singletable;

import javax.persistence.Entity;

@Entity
public class Caminhao extends VeiculoDeCarga {
	private String placa;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
}
