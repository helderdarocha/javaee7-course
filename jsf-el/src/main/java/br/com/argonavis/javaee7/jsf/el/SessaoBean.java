package br.com.argonavis.javaee7.jsf.el;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SessaoBean implements Serializable {

	private static final long serialVersionUID = 9095259729958043151L;
	
	private Filme filme = new Filme("tt1527186", "Melancolia", "Lars von Trier", 2011, 136);
	private Integer sala = 1;
	
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	public Integer getSala() {
		return sala;
	}
	public void setSala(Integer sala) {
		this.sala = sala;
	}
	
	

}
