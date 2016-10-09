package br.com.argonavis.javaee7.jsf.converter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("filmeConverter")
public class FilmeConverter implements Converter {

	// Para simular uma base de dados onde filmes são localizados pelo IMDB
	private Map<String, Filme> filmDatabase = new HashMap<>();

	@PostConstruct
	public void init() {
		filmDatabase.put("tt0062622", new Filme("tt0062622", "2001: A Space Odyssey", "Stanley Kubrick", 1968, 160));
		filmDatabase.put("tt0013442", new Filme("tt0013442", "Nosferatu, eine Symphonie des Grauens", "F. W. Murnau", 1922, 81));
		filmDatabase.put("tt1937390", new Filme("tt1937390", "Nymphomaniac", "Lars von Trier", 2013, 330));
		filmDatabase.put("tt1527186", new Filme("tt1527186", "Melancolia", "Lars von Trier", 2011, 136));
		filmDatabase.put("tt0113083", new Filme("tt0113083", "La Flor de mi Secreto", "Pedro Almodovar", 1995, 103));
		filmDatabase.put("tt0101765", new Filme("tt0101765", "La double vie de Véronique", "Krzysztof Kieslowski", 1991, 98));
	};

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent componente, String imdb) {
		if(imdb == null || imdb == "") {
			return null;
		}
		Filme filme = filmDatabase.get(imdb);
		System.out.println("getAsObject: " + filme);
		return filme;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent componente, Object objFilme) {
		if(objFilme == null) return "";
		String imdb = ((Filme)objFilme).getImdb();
		System.out.println("getAsString: " + imdb);
		return imdb;
	}

}
