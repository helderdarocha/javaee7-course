package br.com.argonavis.javaee7.jpa.intro.web.cdi;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

public class CdiResources {
	@PersistenceContext
	@Produces
	EntityManager em;
}
