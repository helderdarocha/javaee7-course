/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.LivroService;
import biblioteca.jpa.Livro;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

@Named
@ConversationScoped
public class LivroQueryBean implements Serializable {

    @EJB
    LivroService service;
    
    @Inject
    private Conversation conversation;

    private String matchTitulo;
    private String matchAutor;
    private String matchEditora;
    private String matchAssunto;
    private String matchIdioma;
    
    @PostConstruct
    public void init() {
        if (conversation.isTransient() == false) {
            conversation.end();
        }
        conversation.begin();
    }
    
    @PreDestroy
    public void destroy() {
        conversation.end();
    }

    public String getMatchIdioma() {
        return matchIdioma;
    }

    public void setMatchIdioma(String matchIdioma) {
        this.matchIdioma = matchIdioma;
    }

    public String getMatchTitulo() {
        return matchTitulo;
    }

    public void setMatchTitulo(String matchTitulo) {
        this.matchTitulo = matchTitulo;
    }

    public String getMatchAutor() {
        return matchAutor;
    }

    public void setMatchAutor(String matchAutor) {
        this.matchAutor = matchAutor;
    }

    public String getMatchEditora() {
        return matchEditora;
    }

    public void setMatchEditora(String matchEditora) {
        this.matchEditora = matchEditora;
    }

    public String getMatchAssunto() {
        return matchAssunto;
    }

    public void setMatchAssunto(String matchAssunto) {
        this.matchAssunto = matchAssunto;
    }

    public Collection<Livro> getLivros() {
        return service.getByCriteria(matchTitulo, matchIdioma, matchAutor, matchEditora, matchAssunto);
    }
    
    public Collection<Locale> getLocales() {
        return service.getLocales();
    }

}
