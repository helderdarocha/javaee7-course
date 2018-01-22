/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.AssuntoService;
import biblioteca.jpa.Assunto;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

@Named
@ConversationScoped
public class AssuntoBean implements Serializable {

    @EJB
    AssuntoService service;
    
    @Inject
    private Conversation conversation;

    private String filtroDescricao;
    private String filtroClasse;
    private int filtroSummary;
    
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

    public String getFiltroDescricao() {
        return filtroDescricao;
    }

    public void setFiltroDescricao(String filtroDescricao) {
        this.filtroDescricao = filtroDescricao;
    }

    public String getFiltroClasse() {
        return filtroClasse;
    }

    public void setFiltroClasse(String filtroClasse) {
        this.filtroClasse = filtroClasse;
    }

    public int getFiltroSummary() {
        return filtroSummary;
    }

    public void setFiltroSummary(int filtroSummary) {
        this.filtroSummary = filtroSummary;
    }

    public Collection<Assunto> getAssuntos() {
        return service.getByCriteria(filtroSummary, filtroClasse, filtroDescricao);
    }

}
