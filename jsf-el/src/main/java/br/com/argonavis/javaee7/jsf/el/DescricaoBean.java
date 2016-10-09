package br.com.argonavis.javaee7.jsf.el;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class DescricaoBean {

	private Filme filme = new Filme("tt0062622", "2001: A Space Odyssey", "Stanley Kubrick", 1968, 160);
	private Integer canal = 1;
	
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	public Integer getCanal() {
		return canal;
	}
	public void setCanal(Integer canal) {
		this.canal = canal;
	}
	

}
