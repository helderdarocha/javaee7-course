/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Autor;
import java.util.Collection;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
// EXERCICIO: mapeie este objeto como resource REST ao contexto /autor
public class AutorEJB implements AutorService {

    @PersistenceContext(unitName="biblioteca-PU")
    EntityManager em;

    @Override
    // EXERCICIO: Configure GET recebendo parametro id (produzindo JSON e XML)
    public Autor findByID(int id) {
        return em.find(Autor.class, id);
    }

    @Override
    // EXERCICIO: Configure GET (produzindo JSON e XML)
    public Collection<Autor> getAll() {
        return em.createNamedQuery("getAutores", Autor.class).getResultList();
    }

    @Override
    // EXERCICIO: Configure POST (consumindo JSON ou XML)
    public int insert(Autor autor) {
        em.persist(autor);
        return em.merge(autor).getId();
    }

    @Override
    // EXERCICIO: Configure PUT (consumindo JSON ou XML)
    public void update(Autor autor) {
        em.merge(autor);
    }

    @Override
    @Remove
    public void delete(Autor autor) {
        em.remove(em.merge(autor));
    }
    
    // EXERCICIO: Configure DELETE com parametro id
    public void remove(Integer id) {
        this.delete(this.findByID(id));
    }

    @Override
    // EXERCICIO: Configure GET com parametro filterby/{filtro} (produzindo JSON e XML)
    public Collection<Autor> getAutoresFilterBy(String filter) {
        String jpql = "select a from Autor a where lower(a.nome) like :filter";
        TypedQuery<Autor> query = em.createQuery(jpql, Autor.class);
        query.setParameter("filter", "%" + filter.toLowerCase() + "%");
        return query.getResultList();
    }

}
