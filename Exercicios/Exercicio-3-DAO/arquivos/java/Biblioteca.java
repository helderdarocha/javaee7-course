package biblioteca;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class Biblioteca {
	private static Map<String, Livro> acervo = new HashMap<>();
	
	static {
		acervo.put("0785819118", criarLivro(1, "0785819118", "Origin of Species", "en"));
		acervo.put("1906787271", criarLivro(2, "1906787271", "Plato's Republic", "en"));
		acervo.put("9720301600", criarLivro(3, "9720301600", "Os Lusíadas", "pt"));
		acervo.put("0140624155", criarLivro(4, "0140624155", "Les Miserables", "fr"));
		acervo.put("5779310157", criarLivro(5, "5779310157", "Евгений Онегин", "ru"));
	}

	private static Livro criarLivro(int id, String isbn, String titulo, String idioma) {
		Livro livro = new Livro();
		livro.setId(id);
		livro.setIsbn(isbn);
		livro.setTitulo(titulo);
		livro.setIdioma(idioma);
		return livro;
	}
	
	public Collection<Livro> getLivros() {
		return acervo.values();
	}
	
	public Livro getLivro(String isbn) {
		return acervo.get(isbn);
	}
}
