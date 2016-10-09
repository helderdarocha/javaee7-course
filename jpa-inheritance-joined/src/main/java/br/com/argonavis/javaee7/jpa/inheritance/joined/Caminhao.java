package br.com.argonavis.javaee7.jpa.inheritance.joined;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CAMINHAO_JOINED")
public class Caminhao extends VeiculoDeCarga {
	private String placa;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String toString() {
		return " placa: " + placa + " " + super.toString();
	}
}
