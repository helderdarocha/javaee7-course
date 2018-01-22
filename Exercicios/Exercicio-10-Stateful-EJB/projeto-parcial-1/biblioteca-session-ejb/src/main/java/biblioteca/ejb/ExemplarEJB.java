/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Exemplar;
import biblioteca.jpa.ExemplarEletronico;
import biblioteca.jpa.ExemplarImpresso;
import biblioteca.jpa.Livro;
import biblioteca.jpa.Usuario;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

// EXERCICIO: Implemente os metodos getLivrosComExemplares

@Stateless
public class ExemplarEJB implements ExemplarService {

    @PersistenceContext(unitName="biblioteca-PU")
    EntityManager em;

    @Override
    public Exemplar findByID(int id) {
        return em.find(Exemplar.class, id);
    }

    @Override
    public Collection<Exemplar> getByLivro(Livro livro) {
        String jpql = "select distinct exemplar from Exemplar exemplar where exemplar.livro = :livro";
        TypedQuery<Exemplar> query = em.createQuery(jpql, Exemplar.class);
        query.setParameter("livro", livro);
        return query.getResultList();
    }

    @Override
    public Collection<Exemplar> getAll() {
        return em.createNamedQuery("getExemplares", Exemplar.class).getResultList();
    }

    @Override
    public int insert(Exemplar exemplar) {
        em.persist(exemplar);
        return em.merge(exemplar).getId();
    }

    @Override
    public void update(Exemplar exemplar) {
        em.merge(exemplar);
    }

    @Override
    public void delete(Exemplar exemplar) {
        em.remove(em.merge(exemplar));
    }

}
