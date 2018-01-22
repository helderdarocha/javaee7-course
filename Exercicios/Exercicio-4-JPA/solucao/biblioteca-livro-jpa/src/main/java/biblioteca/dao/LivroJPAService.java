/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao;

import biblioteca.Livro;
import java.io.IOException;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

public class LivroJPAService implements LivroService {

    @PersistenceContext(unitName="biblioteca-PU")
    EntityManager em;

    @Override @Transactional
    public Livro findByID(int id) {
        return em.find(Livro.class, id);
    }

    @Override @Transactional
    public Livro findByISBN(String isbn) {
        String jpql = "select distinct livro from Livro livro where isbn = :isbn";
        TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    @Override @Transactional
    public Collection<Livro> getLivros() {
        String query = "select livro from Livro livro";
        return em.createQuery(query, Livro.class).getResultList();
    }

    @Override @Transactional
    public int insert(Livro livro) {
        em.persist(livro);
        return em.merge(livro).getId();
    }

    @Override @Transactional
    public void update(Livro livro) {
        em.merge(livro);
    }

    @Override @Transactional
    public void delete(Livro livro) {
        em.remove(em.merge(livro));
    }

}
