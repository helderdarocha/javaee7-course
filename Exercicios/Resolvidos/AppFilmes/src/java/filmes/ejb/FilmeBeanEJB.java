/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmes.ejb;

import filmes.entity.Filme;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Stateless
//@WebService
//@Path("filmes")
public class FilmeBeanEJB {
    
    @PersistenceContext(unitName="AppFilmesPU")
    EntityManager em;
    
    public void criarOuEditar(Filme filme) {
        em.merge(filme);
    }
    
    //@WebResult(name="filme") 
    //@GET 
    //@Produces({"application/xml", "application/json"})
    public List<Filme> getAll() {
        TypedQuery<Filme> query = 
                em.createQuery("select x from Filme x", Filme.class);
        return query.getResultList();
    }
    
    public void delete(Filme f) {
        em.remove(em.merge(f));
    }
    
    public void update(Filme f) {
        em.merge(f);
    }
}
