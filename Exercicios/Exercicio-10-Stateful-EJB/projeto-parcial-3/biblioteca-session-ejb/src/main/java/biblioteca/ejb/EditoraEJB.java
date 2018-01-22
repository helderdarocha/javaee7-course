/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Editora;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class EditoraEJB implements EditoraService {

    @PersistenceContext(unitName="biblioteca-PU")
    EntityManager em;

    @Override
    public Editora findByID(int id) {
        return em.find(Editora.class, id);
    }

    @Override
    public Collection<Editora> getAll() {
        return em.createNamedQuery("getEditoras", Editora.class).getResultList();
    }

    @Override
    public int insert(Editora editora) {
        em.persist(editora);
        return em.merge(editora).getId();
    }

    @Override
    public void update(Editora editora) {
        em.merge(editora);
    }

    @Override
    public void delete(Editora editora) {
        em.remove(em.merge(editora));
    }

    @Override
    public Collection<Editora> getEditorasFilterBy(String filter) {
        String jpql = "select e from Editora e where lower(e.nome) like :filter";
        TypedQuery<Editora> query = em.createQuery(jpql, Editora.class);
        query.setParameter("filter", "%" + filter.toLowerCase() + "%");
        return query.getResultList();
    }

}
