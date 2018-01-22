package biblioteca;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Não havendo um arquivo beans.xml para explicitamente ligar o suporte a CDI,
 * para que uma classe possa ser injetada usando @Inject, ela deve declarar
 * algum escopo CDI (ex: @RequestScoped). @Named registra um nome necessário
 * caso a classe seja acessada via JSTL (ex: em arquivos XHTML). O nome
 * registrado por default é o nome da classe, com a primeira letra minúscula (ou
 * sem alterações, se começar com duas letras maiúsculas).
 */
@SessionScoped
@Named
public class Biblioteca implements Serializable {

    // Os próximos três membros da classe são static, e portanto executados
    // quando a classe for carregada. O objetivo é criar uma base de dados
    // de teste, na memória, para uso no exemplo.
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

    // Os próximos dois métodos são os únicos membros públicos desta classe.
    // O método getLivros() pode ser acessado como propriedade se o bean
    // for usado via JSTL: ex: biblioteca.livros.
    
    /**
     * Retorna uma lista contendo todos os livros armazenados no acervo.
     * @return Collection contendo todos os objetos Livro do acervo.
     */
    public Collection<Livro> getLivros() {
        return acervo.values();
    }

    /**
     * Retorna um livro dado o seu ISBN, ou null se o livro não existir.
     * @param isbn ISBN do livro
     * @return Livro com o ISBN passado como parâmetro
     */
    public Livro getLivro(String isbn) {
        return acervo.get(isbn);
    }
}
