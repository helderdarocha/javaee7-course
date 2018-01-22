/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Livro;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class LivroEJB implements LivroService {

    @PersistenceContext(unitName="biblioteca-PU")
    EntityManager em;

    @Override
    public Livro findByID(int id) {
        return em.find(Livro.class, id);
    }

    @Override
    public Livro findByISBN(String isbn) {
        String jpql = "select distinct livro from Livro livro where isbn = :isbn";
        TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    @Override
    public Collection<Livro> getAll() {
        return em.createNamedQuery("getLivros", Livro.class).getResultList();
    }

    @Override
    public int insert(Livro livro) {
        em.persist(livro);
        return em.merge(livro).getId();
    }

    @Override
    public void update(Livro livro) {
        em.merge(livro);
    }

    @Override
    public void delete(Livro livro) {
        em.remove(em.merge(livro));
    }

}
