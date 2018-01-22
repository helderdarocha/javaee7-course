/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.LivroService;
import biblioteca.jpa.Assunto;
import biblioteca.jpa.Livro;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class LivroBean implements Serializable {

    @EJB
    LivroService service;

    @Inject
    private Conversation conversation;

    private Livro current; // livro usado no formulário inserir / gravar

    private Assunto nivel1; // mapeia o conteudo do primeiro menu de assunto
    private Assunto nivel2; // mapeia o conteudo do segundo menu de assunto

    @PostConstruct
    public void init() {
        conversation.begin();   // Como usamos Ajax, é necessário ter um escopo maior que Request
        current = new Livro();
    }

    public Livro getCurrent() {
        return current;
    }

    public void setCurrent(Livro livro) {
        this.current = livro;
        
        // EXERCICIO: descomente o código abaixo depois de implementar o relacionamento Livro-Assunto
        /*
        if (livro.getAssunto() != null && livro.getAssunto().getContexto() != null && livro.getAssunto().getContexto().getContexto() != null) {
            nivel1 = livro.getAssunto().getContexto().getContexto();
            nivel2 = livro.getAssunto().getContexto();
        }
        */
    }

    public Assunto getNivel1() {
        return nivel1;
    }

    public void setNivel1(Assunto nivel1) {
        this.nivel1 = nivel1;
        this.nivel2 = null;
    }

    public Assunto getNivel2() {
        return nivel2;
    }

    public void setNivel2(Assunto nivel2) {
        this.nivel2 = nivel2;
        // EXERCICIO: descomente o código abaixo depois de implementar o relacionamento Livro-Assunto
        /*
        this.current.setAssunto(null);
        */
    }

    public String gravar() {
        service.update(current);
        conversation.end();
        return "livros";
    }
    
    public String cancelar() {
        conversation.end();
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
