/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.LivroService;
import biblioteca.jpa.Livro;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LivroBean {
    @EJB LivroService service;
    
    private Livro current; // livro usado no formulário inserir / gravar
    
    @PostConstruct
    public void init() {
        current = new Livro();
    }

    public Livro getCurrent() {
        return current;
    }

    public void setCurrent(Livro livro) {
        this.current = livro;
    }
    
    public String gravar() {
        service.update(current);
        return "livros";
    }
    
    public String delete(Livro livro) {
        service.delete(livro);
        return "livros";
    }
    
    public String edit(Livro livro) {
        this.setCurrent(livro);
        return "livro";
    }
}
