/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Exemplar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class CrashRecoveryBean {
    
    @EJB ExemplarService exemplarService;

    @PostConstruct
    public void init() {
        // TODO: incluir flag para só fazer isto se houve saída anormal
        // verificar a consistencia da base de Exemplares
        for(Exemplar e: exemplarService.getAll()) {
            if(!e.isDisponivel() && e.getUsuario() == null) { // livro indisponivel e nao emprestado
                e.setDisponivel(true);
                exemplarService.update(e);
            }
        }
    }
}
