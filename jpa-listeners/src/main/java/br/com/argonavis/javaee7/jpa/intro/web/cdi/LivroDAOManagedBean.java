/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.javaee7.jpa.intro.web.cdi;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Named
public class LivroDAOManagedBean {
	
	@Inject
	EntityManager em;

	@Transactional
    public Livro findByID(Long id) {
    	TypedQuery<Livro> query = em.createNamedQuery("selectById", Livro.class);
    	query.setParameter("id", id);
    	return query.getSingleResult();
    }
    
	@Transactional
    public List<Livro> findAll() {
    	return em.createNamedQuery("selectAll", Livro.class).getResultList();
    }
    
	@Transactional
    public void delete(Livro livro) {
    	em.remove(livro);
    }

	@Transactional
    public void update(Livro livro) {
    	em.merge(livro);
    }
    
	@Transactional
    public Livro insert(Livro livro) {
    	return em.merge(livro);
    }

}
