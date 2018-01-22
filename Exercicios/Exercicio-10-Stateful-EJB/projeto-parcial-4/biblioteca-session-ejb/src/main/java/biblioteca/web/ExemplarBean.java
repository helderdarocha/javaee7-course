 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.ExemplarService;
import biblioteca.jpa.Exemplar;
import biblioteca.jpa.ExemplarEletronico;
import biblioteca.jpa.ExemplarImpresso;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class ExemplarBean implements Serializable {

    public static int ELETRONICO = 1;
    public static int IMPRESSO = 2;

    @EJB
    ExemplarService service;

    @Inject
    private Conversation conversation;

    private Exemplar current;

    private int tipo;

    @PostConstruct
    public void init() {
        if (conversation.isTransient() == false) {
            conversation.end();
        }
        conversation.begin();
    }

    private void setup() {
        if (tipo == ELETRONICO) {
            current = new ExemplarEletronico();
            this.tipo = ELETRONICO;
        } else {
            current = new ExemplarImpresso();
            this.tipo = IMPRESSO;
        }

    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
        setup();
    }

    public Exemplar findByID(int id) {
        return service.findByID(id);
    }

    public Long getTamanho() {
        if (current instanceof ExemplarEletronico) {
            return ExemplarEletronico.class.cast(current).getTamanho();
        }
        return -1L;
    }

    public void setTamanho(Long tamanho) {
        if (current instanceof ExemplarEletronico) {
            ExemplarEletronico.class.cast(current).setTamanho(tamanho);
        }
    }

    public int getPaginas() {
        if (current instanceof ExemplarImpresso) {
            return ExemplarImpresso.class.cast(current).getPaginas();
        }
        return -1;
    }

    public void setPaginas(int paginas) {
        if (current instanceof ExemplarImpresso) {
            ExemplarImpresso.class.cast(current).setPaginas(paginas);
        }
    }

    public Exemplar getCurrent() {
        return current;
    }

    public void setCurrent(Exemplar exemplar) {
        this.current = exemplar;
        if (current instanceof ExemplarImpresso) {
            this.tipo = IMPRESSO;
        } else {
            this.tipo = ELETRONICO;
        }
    }

    public String gravar() {
        service.update(current);
        conversation.end();
        return "exemplares";
    }

    public String cancelar() {
        conversation.end();
        return "exemplares";
    }

    public String delete(Exemplar exemplar) {
        service.delete(exemplar);
        return "exemplares";
    }

    public String edit(Exemplar exemplar) {
        this.setCurrent(exemplar);
        return "exemplar";
    }
}
