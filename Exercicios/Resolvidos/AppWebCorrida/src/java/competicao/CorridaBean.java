/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competicao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author helderdarocha
 */
@Named
@RequestScoped
public class CorridaBean {

    @PersistenceContext(unitName = "AppWebCorridaPU")
    private EntityManager em;

    @Transactional
    public String getTesteCorrida() {
        Corrida c = new Corrida();
        c.setNome("Competição Mundial");

        em.merge(c);
        
        return "funcionou";
    }

}
