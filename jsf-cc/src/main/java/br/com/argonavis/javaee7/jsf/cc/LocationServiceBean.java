package br.com.argonavis.javaee7.jsf.cc;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named("locationService")
@SessionScoped
public class LocationServiceBean implements Serializable {

	private static final long serialVersionUID = -3551751960614867521L;

	private Location location = new Location();
	
	public void register(ActionEvent evt) {
		System.out.println("Location " + location + " registered!");
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
