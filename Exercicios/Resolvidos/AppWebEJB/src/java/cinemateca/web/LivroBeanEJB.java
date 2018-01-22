/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemateca.web;

import cinemateca.Livro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class LivroBeanEJB {
    
    @PersistenceContext(unitName="AppWebEJBPU")
    EntityManager em;
    
    public void criarLivro(Livro livro) {
        em.persist(livro);
    }
    
    public List<Livro> getLivros() {
        TypedQuery<Livro> query = 
                em.createQuery("select x from Livro x", Livro.class);
        return query.getResultList();
    }
}
