package br.com.argonavis.javaee7.jpa.inheritance.tableperclass;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CAMINHAO_TPC")
public class Caminhao extends VeiculoDeCarga {
	private String placa;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
}
