/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.javaee7.jpa.intro.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LivroDAOSessionBean {
	
	@PersistenceContext
	EntityManager em;

    public Livro findByID(Long id) {
    	Query query = em.createNamedQuery("selectById");
    	query.setParameter("id", id);
    	return (Livro)query.getSingleResult();
    }
    
    public List<Livro> findAll() {
    	return (List<Livro>)em.createNamedQuery("selectAll").getResultList();
    }
    
    public void delete(Livro livro) {
    	em.remove(livro);
    }

    public void update(Livro livro) {
    	em.merge(livro);
    }
    
    public Livro insert(Livro livro) {
    	return em.merge(livro);
    }

}
