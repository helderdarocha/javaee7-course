/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Assunto;
import biblioteca.jpa.Autor;
import biblioteca.jpa.Editora;
import biblioteca.jpa.ExemplarEletronico;
import biblioteca.jpa.ExemplarImpresso;
import biblioteca.jpa.Livro;
import biblioteca.jpa.Usuario;
import biblioteca.util.LoginUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author helderdarocha
 */
@Stateless
public class TestServiceEJB {

    @PersistenceContext(unitName = "biblioteca-PU")
    private EntityManager em;
    
    @EJB AssuntoService assuntoService;

    public void removeAllEntities(List<String> entities) {
        for(String entity : entities) {
            em.createQuery("delete from "+entity+" e").executeUpdate();
        }
    }

    public void createTestData() {
        // Autor
        String[] nomesAutores = {"Mary West", "Karl Und Der Dog", "Anderlaine do Nascimento", "Fubano de Tal", "Zambygumba Al Zubamm"};
        Autor[] autores = new Autor[nomesAutores.length];
        for(int i = 0; i < nomesAutores.length; i++) {
            Autor autor = new Autor();
            autor.setNome(nomesAutores[i]);
            em.persist(autor);
            autores[i] = em.merge(autor);
        }
        
        // Editora
        String[] nomesEditoras = {"Nobody's Books", "Livros Hipopótamo", "Gumba Dumba", "Big Books"};
        Editora[] editoras = new Editora[nomesEditoras.length];
        for(int i = 0; i < nomesEditoras.length; i++) {
            Editora editora = new Editora();
            editora.setNome(nomesEditoras[i]);
            em.persist(editora);
            editoras[i] = em.merge(editora);
        }
        
        // Livro
        createLivro("0123456789", "Era uma vez um bicho", "pt", editoras[0], new Autor[] {autores[2]}, assuntoService.getAssunto("592", 3));
        createLivro("3344556677", "The Big Foot", "en", editoras[3], new Autor[] {autores[0], autores[1]}, assuntoService.getAssunto("813", 3));
        createLivro("9786656656654", "Bugs and Bats", "en", editoras[1], new Autor[] {autores[1]}, assuntoService.getAssunto("590", 3));
        createLivro("4343434343", "O Espaguete Voador", "pt", editoras[0], new Autor[] {autores[3], autores[4]}, assuntoService.getAssunto("299", 3));
        createLivro("9786663636363", "Elfish para iniciantes", "pt", editoras[0], new Autor[] {autores[3]}, assuntoService.getAssunto("491", 3));
        createLivro("9876543210", "There was a bat in the hat", "en", editoras[1], new Autor[] {autores[0]}, assuntoService.getAssunto("827", 3));
        createLivro("4343434342", "Al Timbuk Nat Zugumba", "zu", editoras[2], new Autor[] {autores[2]}, assuntoService.getAssunto("135", 3));
        
        // Usuario
        String[] nomesUsuarios = {"maria", "joaquim"};
        for(String nome: nomesUsuarios) {
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setSenha(LoginUtils.getPasswordHash("java")); // a senha para todos é sempre "java"
            em.persist(usuario);
        }
        
        // Exemplar
    }
    
    private void createLivro(String isbn, String titulo, String idioma, Editora editora, Autor[] autores, Assunto assunto) {
        Livro livro = new Livro();
        livro.setIsbn(isbn);
        livro.setTitulo(titulo);
        livro.setIdioma(idioma);
        livro.setEditora(editora);
        livro.setAutores(Arrays.asList(autores));
        livro.setAssunto(assunto);
        em.persist(livro);
        
        // Cada livro criado incluir pelo menos um exemplar impresso
        int paginas = new Random().nextInt(100) * 3 + 50;
        criarImpresso(paginas, livro);
        
        // Criar mais exemplares randomicamente
        for(int i = 0; i < new Random().nextInt(2); i++) {
            criarImpresso(paginas, livro);
        }
        
        long bytes = new Random().nextInt(100000) * 3L + 50000;
        for(int i = 0; i < new Random().nextInt(10); i++) {
            criarEbook(bytes, livro);
        }
        
        
        // Exercício: Faça o mapeamento do cascade para não precisar realizar alterações abaixo - MERGE, PERSIST
        // (e não precisar seguir uma ordem para remover as entidades - DELETE)
        editora.getTitulos().add(livro);
        for(Autor a: autores) {
            a.getObras().add(livro);
        }
    }

    private void criarImpresso(int paginas, Livro livro) {
        ExemplarImpresso exemplar = new ExemplarImpresso();
        exemplar.setLivro(livro);
        exemplar.setPaginas( paginas );
        exemplar.setDisponivel(true);
        em.persist(exemplar);
    }

    private void criarEbook(long bytes, Livro livro) {
        ExemplarEletronico exemplar = new ExemplarEletronico();
        exemplar.setLivro(livro);
        exemplar.setTamanho( bytes );
        exemplar.setDisponivel(true);
        em.persist(exemplar);
    }

}
