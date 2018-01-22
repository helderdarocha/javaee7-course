/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Autor;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AutorEJB implements AutorService {

    @PersistenceContext(unitName="biblioteca-PU")
    EntityManager em;

    @Override
    public Autor findByID(int id) {
        return em.find(Autor.class, id);
    }

    @Override
    public Collection<Autor> getAll() {
        return em.createNamedQuery("getAutores", Autor.class).getResultList();
    }

    @Override
    public int insert(Autor autor) {
        em.persist(autor);
        return em.merge(autor).getId();
    }

    @Override
    public void update(Autor autor) {
        em.merge(autor);
    }

    @Override
    public void delete(Autor autor) {
        em.remove(em.merge(autor));
    }

    @Override
    public Collection<Autor> getAutoresFilterBy(String filter) {
        String jpql = "select a from Autor a where lower(a.nome) like :filter";
        TypedQuery<Autor> query = em.createQuery(jpql, Autor.class);
        query.setParameter("filter", "%" + filter.toLowerCase() + "%");
        return query.getResultList();
    }

}
