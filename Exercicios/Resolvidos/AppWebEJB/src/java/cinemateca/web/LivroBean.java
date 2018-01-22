/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemateca.web;

import cinemateca.Livro;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("livroBean")
public class LivroBean {
    private String titulo;
    private String autor;
    
    @EJB
    LivroBeanEJB dao;
    
    public String criarLivro() {
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        
        dao.criarLivro(livro);
        
        return "listaDeLivros";
    }
    
    public List<Livro> getLivros() {
        return dao.getLivros();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
