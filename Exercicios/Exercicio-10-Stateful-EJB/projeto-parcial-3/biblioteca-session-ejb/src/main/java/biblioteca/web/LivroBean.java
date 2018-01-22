/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.ExemplarService;
import biblioteca.ejb.LivroService;
import biblioteca.jpa.Assunto;
import biblioteca.jpa.Exemplar;
import biblioteca.jpa.ExemplarEletronico;
import biblioteca.jpa.ExemplarImpresso;
import biblioteca.jpa.Livro;
import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class LivroBean implements Serializable {

    @EJB
    LivroService service;
    @EJB
    ExemplarService exemplarService;

    @Inject
    private Conversation conversation;

    private Livro current; // livro usado no formulário inserir / gravar
    private Exemplar exemplar;
    private int tipoExemplar;

    private Assunto nivel1;
    private Assunto nivel2;
    private Long tamanho = -1L;
    private int paginas = -1;

    @PostConstruct
    public void init() {
        if (conversation.isTransient() == false) {
            conversation.end();
        }
        conversation.begin();
        current = new Livro();
    }

    public Locale getLocale(String idioma) {
        return Locale.forLanguageTag(idioma);
    }

    public Livro findByID(int id) {
        return service.findByID(id);
    }

    public Livro getCurrent() {
        return current;
    }

    public void setCurrent(Livro livro) {
        this.current = livro;
        if (livro.getAssunto() != null && livro.getAssunto().getContexto() != null && livro.getAssunto().getContexto().getContexto() != null) {
            nivel1 = livro.getAssunto().getContexto().getContexto();
            nivel2 = livro.getAssunto().getContexto();
        }
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
        this.current.setAssunto(null);
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public int getTipoExemplar() {
        return tipoExemplar;
    }

    public void setTipoExemplar(int tipoExemplar) {
        this.tipoExemplar = tipoExemplar;
    }

    public Collection<Exemplar> getExemplares() {
        return exemplarService.getByLivro(current);
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String gravar() {
        if (current.getId() == 0) {
            if (tipoExemplar == ExemplarBean.ELETRONICO) {
                exemplar = new ExemplarEletronico();
                ((ExemplarEletronico)exemplar).setTamanho(tamanho);
            } else if (tipoExemplar == ExemplarBean.IMPRESSO) {
                exemplar = new ExemplarImpresso();
                ((ExemplarImpresso)exemplar).setPaginas(paginas);
            }
            exemplar.setLivro(current);
            exemplar.setDisponivel(true);
            exemplarService.insert(exemplar);
        }
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
