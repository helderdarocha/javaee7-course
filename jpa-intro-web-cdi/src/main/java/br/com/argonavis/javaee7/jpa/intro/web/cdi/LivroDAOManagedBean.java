/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.javaee7.jpa.intro.web.cdi;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Named
public class LivroDAOManagedBean {

	@Inject
	EntityManager em;

	public Livro findByID(Long id) {
		TypedQuery<Livro> query = em.createNamedQuery("selectById", Livro.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Transactional
	public Livro findByIDCriteria(Long id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Livro> criteria = builder.createQuery(Livro.class);
		Root<Livro> queryRoot = criteria.from(Livro.class);
		criteria.where(builder.equal(queryRoot.get(Livro_.id), id));
		TypedQuery<Livro> q = em.createQuery(criteria);
		return q.getSingleResult();

	}

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
