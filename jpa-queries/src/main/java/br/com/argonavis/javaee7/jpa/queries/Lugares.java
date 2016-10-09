package br.com.argonavis.javaee7.jpa.queries;

public class Lugares {
	private String origem, destino;

	public String getOrigem() {
		return origem;
	}

	public String getDestino() {
		return destino;
	}

	public Lugares(String origem, String destino) {
		this.origem = origem;
		this.destino = destino;
	}
}