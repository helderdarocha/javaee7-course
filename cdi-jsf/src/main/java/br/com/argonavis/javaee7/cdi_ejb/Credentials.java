package br.com.argonavis.javaee7.cdi_ejb;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;;

@Named
@RequestScoped
public class Credentials {
	private String username;
	private String password;
	
	private static final Logger logger = Logger.getLogger("Credentials");

	@NotNull
	@Size(min = 3, max = 25)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		logger.info("username: " + username);
		this.username = username;
	}

	@NotNull @Size(min=6, max=20)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTest() {
		return "Bah!";
	}
}
