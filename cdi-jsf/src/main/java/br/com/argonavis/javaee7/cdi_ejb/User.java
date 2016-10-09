package br.com.argonavis.javaee7.cdi_ejb;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
public class User {
	
	@NotNull
	@Size(min = 3, max = 25)
	@Id
	private String username;
	
	@NotNull
	@Size(min = 6, max = 20)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}