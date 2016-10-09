package br.com.argonavis.javaee7.jpa.inheritance.tableperclass;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class VeiculoDeCarga extends Veiculo {
	private double cargaMaxima;

	public double getCargaMaxima() {
		return cargaMaxima;
	}

	public void setCargaMaxima(double cargaMaxima) {
		this.cargaMaxima = cargaMaxima;
	}
	
}
