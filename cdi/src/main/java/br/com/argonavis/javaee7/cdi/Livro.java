package br.com.argonavis.javaee7.cdi;

import java.util.Arrays;

public class Livro {
	public enum Status {DISPONIVEL, EMPRESTADO};
    private String titulo;
    private String[] autores;
    private Status status;
    
	public Livro(String titulo, String[] autores) {
		super();
		this.titulo = titulo;
		this.autores = autores;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String[] getAutores() {
		return autores;
	}

	public void setAutores(String[] autores) {
		this.autores = autores;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Livro [titulo=" + titulo + ", autores=" + Arrays.toString(autores) + "]";
	}
	
	
    
}
