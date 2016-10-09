package br.com.argonavis.javaee7.jpa.queries;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CdiResources {
	@PersistenceContext
	@Produces
	EntityManager em;
}
