package br.com.argonavis.javaee7.jsf.beanvalidation;

import java.io.Serializable;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;

public class Pacote implements Serializable {
	private static final long serialVersionUID = -7426659179262048289L;
	
	@Max(200)
	private int altura;
	
	@Max(200)
	private int largura;
	
	@Max(200)
	private int profundidade;
	private String conteudo;
	
	@DecimalMax("10000.00")
	@DecimalMin("1000.00")
	private double seguro;
	
	
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getLargura() {
		return largura;
	}
	public void setLargura(int largura) {
		this.largura = largura;
	}
	public int getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(int profundidade) {
		this.profundidade = profundidade;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public double getSeguro() {
		return seguro;
	}
	public void setSeguro(double seguro) {
		this.seguro = seguro;
	}
	
	
}
