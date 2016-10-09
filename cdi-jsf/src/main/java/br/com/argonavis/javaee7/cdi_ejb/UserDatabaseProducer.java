package br.com.argonavis.javaee7.cdi_ejb;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserDatabaseProducer {

	@Produces @PersistenceContext
	static EntityManager userDatabase;
	
}
