package br.com.argonavis.javaee7.jsf.facelets;

import java.io.Serializable;

public class Filme implements Serializable {
	
	private static final long serialVersionUID = -6580551555656090949L;
	
	private int id;
	private String imdb;
	private String titulo;
	private String diretor;
	private int ano;
	private int duracao;
	
	public Filme(String imdb, String titulo, String diretor, int ano, int duracao) {
		super();
		this.imdb = imdb;
		this.titulo = titulo;
		this.diretor = diretor;
		this.ano = ano;
		this.duracao = duracao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getImdb() {
		return imdb;
	}
	public void setImdb(String imdb) {
		this.imdb = imdb;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	
	public String toString() {
		return id + ": " 
	            + imdb + " '" 
				+ titulo + "', diretor: " 
	            + diretor + " (" 
				+ ano + ") duração: " 
	            + duracao + " minutos.";
	}
	
	public boolean equals(Object o) {
		return ((Filme)o).imdb == this.imdb;
	}
	public int hashCode() {
		return imdb.hashCode();
	}
	
}
