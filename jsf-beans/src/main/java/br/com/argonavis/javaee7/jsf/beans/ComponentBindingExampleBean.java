package br.com.argonavis.javaee7.jsf.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIData;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectItems;
import javax.faces.component.UISelectMany;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@Named("cbb")
@RequestScoped // nao use um escopo maior com binding!
public class ComponentBindingExampleBean implements Serializable {

	private static final long serialVersionUID = -1964729890841234794L;

	UIData detalhesFilme;
	
	UIOutput tituloFilme;
	UIOutput diretorFilme;
	UIOutput imdbFilme;
	UIOutput duracaoFilme;
	UIOutput anoFilme;
	
	UISelectOne  menuFilmes;
	UISelectMany menuOpcoes;
	UISelectItems itemsFilmes;
	
	private static final Filme[] filmes = new Filme[] {
			new Filme("tt0062622", "2001: A Space Odyssey", "Stanley Kubrick", 1968, 160),
			new Filme("tt0013442", "Nosferatu, eine Symphonie des Grauens", "F. W. Murnau", 1922, 81),
			new Filme("tt1937390", "Nymphomaniac", "Lars von Trier", 2013, 330),
			new Filme("tt1527186", "Melancolia", "Lars von Trier", 2011, 136),
			new Filme("tt0113083", "La Flor de mi Secreto", "Pedro Almodovar", 1995, 103),
			new Filme("tt0101765", "La double vie de Véronique", "Krzysztof Kieslowski", 1991, 98)
	};

	public UIData getDetalhesFilme() {
		return detalhesFilme;
	}

	public void setDetalhesFilme(UIData detalhesFilme) {
		this.detalhesFilme = detalhesFilme;
	}

	public UIOutput getTituloFilme() {
		return tituloFilme;
	}

	public void setTituloFilme(UIOutput tituloFilme) {
		this.tituloFilme = tituloFilme;
	}

	public UIOutput getDiretorFilme() {
		return diretorFilme;
	}

	public void setDiretorFilme(UIOutput diretorFilme) {
		this.diretorFilme = diretorFilme;
	}

	public UIOutput getImdbFilme() {
		return imdbFilme;
	}

	public void setImdbFilme(UIOutput imdbFilme) {
		this.imdbFilme = imdbFilme;
	}

	public UIOutput getAnoFilme() {
		return anoFilme;
	}

	public void setAnoFilme(UIOutput anoFilme) {
		this.anoFilme = anoFilme;
	}

	public UISelectOne getMenuFilmes() {
		return menuFilmes;
	}

	public void setMenuFilmes(UISelectOne menuFilmes) { // will only be set on submit
		this.menuFilmes = menuFilmes;
		System.out.println("AAAAA");
		if(this.menuFilmes != null) {
			System.out.println(this.menuFilmes);
			if(this.menuFilmes.getValue() != null) {
				System.out.println(this.menuFilmes.getValue());
				this.detalhesFilme.setValue(this.menuFilmes.getValue());
			}
		}
	}

	public UISelectMany getMenuOpcoes() {
		return menuOpcoes;
	}

	public void setMenuOpcoes(UISelectMany menuOpcoes) {
		this.menuOpcoes = menuOpcoes;
	}

	public UIOutput getDuracaoFilme() {
		return duracaoFilme;
	}

	public void setDuracaoFilme(UIOutput duracaoFilme) {
		this.duracaoFilme = duracaoFilme;
	}

	public UISelectItems getItemsFilmes() {
		return itemsFilmes;
	}

	public void setItemsFilmes(UISelectItems itemsFilmes) {
		this.itemsFilmes = itemsFilmes;
		this.itemsFilmes.setValue(filmes);
	}
	
	public void detailConfig(AjaxBehaviorEvent e) {
		this.detalhesFilme.setRendered(true);
	}
	
}
