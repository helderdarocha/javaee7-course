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

    // EXERCÍCIO: use @PersistenceContext(unitName="") para injetar aqui um EntityManager da unidade de persistência
    // definida no arquivo persistence.xml
    EntityManager em;
    
    // EXERCÍCIO: implemente os métodos do DAO com métodos do EntityManager. 

    @Override @Transactional
    public Livro findByID(int id) {
        return em.find(Livro.class, id);
    }

    @Override @Transactional
    public Livro findByISBN(String isbn) {
        String jpql = "EXERCICIO"; // IMPLEMENTE O QUERY!
        TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    @Override @Transactional
    public Collection<Livro> getLivros() {
        // EXERCÍCIO
        return null;
    }

    @Override @Transactional
    public int insert(Livro livro) {
        // EXERCÍCIO
        return 0;
    }

    @Override @Transactional
    public void update(Livro livro) {
        // EXERCÍCIO
    }

    @Override @Transactional
    public void delete(Livro livro) {
        em.remove(em.merge(livro));
    }

}
