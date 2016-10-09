package br.com.argonavis.javaee7.jsf.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@SessionScoped
@Named("vbb")
public class ValueBindingExampleBean implements Serializable {
	
	///// 1
	
    private int exponent = 0;
    private int result;
    
	public int getExponent() {
		return exponent;
	}
	public void setExponent(int exponent) {
		this.exponent = exponent;
	}
	public int getResult() {
		return (int)Math.pow(2,exponent);
	}
	
	///// 2
    
    private static final Filme[] filmes = new Filme[] {
			new Filme("tt0062622", "2001: A Space Odyssey", "Stanley Kubrick", 1968, 160),
			new Filme("tt0013442", "Nosferatu, eine Symphonie des Grauens", "F. W. Murnau", 1922, 81),
			new Filme("tt1937390", "Nymphomaniac", "Lars von Trier", 2013, 330),
			new Filme("tt1527186", "Melancolia", "Lars von Trier", 2011, 136),
			new Filme("tt0113083", "La Flor de mi Secreto", "Pedro Almodovar", 1995, 103),
			new Filme("tt0101765", "La double vie de Véronique", "Krzysztof Kieslowski", 1991, 98)
	};

	public Filme[] getFilmes() {
		return filmes;
	}
	
	///// 3
	
    private Filme[] selectedFilmes = new Filme[] {
    		filmes[1],
    		filmes[3]
    };
    
	public void setSelectedFilmes(Filme[] selectedFilmes) {
		System.out.println(">>>>>>>S " + selectedFilmes);
		this.selectedFilmes = selectedFilmes;
	}
	public Filme[] getSelectedFilmes() {
		System.out.println(">>>>>>>G " + selectedFilmes);
		return selectedFilmes;
	}
	public void change(AjaxBehaviorEvent e) {
		System.out.println(">>>>>>>C " + selectedFilmes);
	}
    
}
