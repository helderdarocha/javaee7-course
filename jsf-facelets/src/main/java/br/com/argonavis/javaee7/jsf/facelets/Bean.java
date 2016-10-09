package br.com.argonavis.javaee7.jsf.facelets;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Bean implements Serializable {
	private static final long serialVersionUID = -6659893450771815104L;
	
	private String hidden = "I am hidden here. Leave me alone!";
	private String pass = "B16 53CR3T";
	private String input = "A short line of nothing.";
	private String description = "Once upon a time, in a place very, very far a way, there was an aardvark who thought he was a fish.";
	
	private Integer sorte = 207;
	private Boolean dark = false;
	private String filme; // guardando o IMDB (para nao precisar de Converter neste exemplo)
	private Integer[] turnos;
	private String[] selecaoFilmes; // lista de strings com IMDB 
	
	private Integer[] sequencia = {1,2,3,4,5,6,7,8,9};
	
	public String goHome() {
		return "index";
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getSorte() {
		return sorte;
	}
	
	public Integer jogar() {
		this.sorte = (int)(Math.random() * 1000);
		return sorte;
	}

	public Boolean getDark() {
		return dark;
	}

	public void setDark(Boolean dark) {
		this.dark = dark;
	}

	public String getFilme() {
		return filme;
	}

	public void setFilme(String filme) {
		this.filme = filme;
	}

	public Integer[] getTurnos() {
		return turnos;
	}

	public void setTurnos(Integer[] turnos) {
		this.turnos = turnos;
	}

	public String[] getSelecaoFilmes() {
		return selecaoFilmes;
	}

	public void setSelecaoFilmes(String[] selecaoFilmes) {
		this.selecaoFilmes = selecaoFilmes;
	}

	public Integer[] getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer[] sequencia) {
		this.sequencia = sequencia;
	}
}
