/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.AssuntoService;
import biblioteca.ejb.AutorService;
import biblioteca.ejb.EditoraService;
import biblioteca.jpa.Livro;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import biblioteca.ejb.LivroService;
import biblioteca.jpa.Assunto;
import biblioteca.jpa.Autor;
import biblioteca.jpa.Editora;

@Named
@RequestScoped
public class BibliotecaBean {
    
    @EJB LivroService livroService;
    @EJB AutorService autorService;
    @EJB EditoraService editoraService;
    @EJB AssuntoService assuntoService;
    
    public Assunto getAssunto(String cdd, int summary) {
        return assuntoService.getAssunto(cdd, summary);
    }
    
    public Collection<Assunto> getAssuntos() {
        return assuntoService.getRoots();
    }
    
    public Collection<Livro> getLivros() {
        return livroService.getAll();
    }
    
    public Collection<Autor> getAutores() {
        return autorService.getAll();
    }
    
    public Collection<Editora> getEditoras() {
        return editoraService.getAll();
    }

}
