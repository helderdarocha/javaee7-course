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
    public Collection<Livro> getLivrosComExemplares(boolean disponivel) {
        String jpql = "select distinct livro from Livro livro, Exemplar exemplar where exemplar.livro = livro and exemplar.disponivel = :disponivel";
        TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
        query.setParameter("disponivel", disponivel);
        return query.getResultList();
    }
    
    @Override
    public Collection<Exemplar> getExemplaresEmprestados(Usuario usuario) {
        String jpql = "select distinct e from Exemplar e where e.usuario = :usuario";
        TypedQuery<Exemplar> query = em.createQuery(jpql, Exemplar.class);
        query.setParameter("usuario", usuario);
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

    @Override
    public Collection<ExemplarImpresso> getImpressoByLivro(Livro livro, boolean disponivel) {
        String jpql = "select exemplar from ExemplarImpresso exemplar where exemplar.livro = :livro and exemplar.disponivel = :disponivel";
        TypedQuery<ExemplarImpresso> query = em.createQuery(jpql, ExemplarImpresso.class);
        query.setParameter("livro", livro);
        query.setParameter("disponivel", disponivel);
        return query.getResultList();
    }

    @Override
    public Collection<ExemplarEletronico> getEbookByLivro(Livro livro, boolean disponivel) {
        String jpql = "select exemplar from ExemplarEletronico exemplar where exemplar.livro = :livro and exemplar.disponivel = :disponivel";
        TypedQuery<ExemplarEletronico> query = em.createQuery(jpql, ExemplarEletronico.class);
        query.setParameter("livro", livro);
        query.setParameter("disponivel", disponivel);
        return query.getResultList();
    }

    @Override
    public void devolver(Exemplar e) {
        e = em.merge(e);
        e.setDisponivel(true);
        Usuario u = e.getUsuario();
        em.merge(u);
        u.getEmprestimos().remove(e);
        e.setUsuario(null);
    }

}
