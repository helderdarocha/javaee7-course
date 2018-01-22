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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ExemplarBean implements Serializable {
    
    public static int ELETRONICO = 1;
    public static int IMPRESSO   = 2;

    @EJB
    ExemplarService service;

    private Exemplar current; 

    private int tipo;
    
    // EXERCÍCIO: Quando implementar a hierarquia de classes, descomente as linhas comentadas abaixo
    private void init() {
        if(tipo == ELETRONICO) {
            //current = new ExemplarEletronico();
        } else {
            //current = new ExemplarImpresso();
        }
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
        init();
    }
    
    public Exemplar findByID(int id) {
        return service.findByID(id);
    }

    // EXERCÍCIO: Quando implementar a hierarquia de classes, descomente as linhas comentadas abaixo
    public Long getTamanho() {
        //if(current instanceof ExemplarEletronico) {
        //    return ExemplarEletronico.class.cast(current).getTamanho();
        //} 
        return -1L;
    }

    public void setTamanho(Long tamanho) {
        //if(current instanceof ExemplarEletronico) {
        //    ExemplarEletronico.class.cast(current).setTamanho(tamanho);
        //}
    }

    public int getPaginas() {
        //if(current instanceof ExemplarImpresso) {
        //    return ExemplarImpresso.class.cast(current).getPaginas();
        //}
        return -1;
    }

    public void setPaginas(int paginas) {
        //if(current instanceof ExemplarImpresso) {
        //    ExemplarImpresso.class.cast(current).setPaginas(paginas);
        //}
    }

    public Exemplar getCurrent() {
        return current;
    }

    public void setCurrent(Exemplar exemplar) {
        this.current = exemplar;
    }

    public String gravar() {
        service.update(current);
        return "exemplares";
    }
    
    public String cancelar() {
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
