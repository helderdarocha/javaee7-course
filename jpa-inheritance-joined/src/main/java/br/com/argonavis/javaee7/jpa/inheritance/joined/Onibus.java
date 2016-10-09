package br.com.argonavis.javaee7.jpa.inheritance.joined;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ONIBUS_JOINED")
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
	public String toString() {
		return tipo + " placa: " + placa;
	}
}
