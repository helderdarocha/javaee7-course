/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemateca.web;

import cinemateca.Livro;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.enterprise.context.RequestScoped;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@RequestScoped
@Named("livroBean")
public class LivroBean {
    private String titulo;
    private String autor;
    
    @PersistenceContext(unitName="AppWebJPAPU")
    EntityManager em;
    
    @Transactional
    public String criarLivro() {
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        
        em.persist(livro);
        
        return "listaDeLivros";
    }
    
    public List<Livro> getLivros() {
        TypedQuery<Livro> query = em.createQuery("select x from Livro x", Livro.class);
        return query.getResultList();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
