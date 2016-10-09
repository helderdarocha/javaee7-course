package br.com.argonavis.javaee7.jpa.intro.web.cdi;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CdiResources {
	@PersistenceContext
	@Produces
	EntityManager em;
}
