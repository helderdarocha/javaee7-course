/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.argonavis.javaee7.cdi_ejb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SessionScoped
@Named
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	Credentials credentials;
	
	@PersistenceContext
	EntityManager userDatabase;

	private User user;

	public void login() {
		System.out.println("username: " + credentials.getUsername() + ", password: " + credentials.getPassword());
		
		String query = "select u from User u where u.username = :username and u.password = :password";
		List<User> results = userDatabase.createQuery(query)
				.setParameter("username", credentials.getUsername())
				.setParameter("password", credentials.getPassword())
				.getResultList();
		
		if(!results.isEmpty()) {
			user = results.get(0);
		} else {
			System.out.println("Failed login");
		}
		
	}
	
	public void logout() {
		user = null;
	}
	
	public boolean isLoggedIn() {
		return user != null;
	}

}
