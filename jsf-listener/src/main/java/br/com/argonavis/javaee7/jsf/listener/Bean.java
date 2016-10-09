package br.com.argonavis.javaee7.jsf.listener;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class Bean implements Serializable {

	private static final long serialVersionUID = 3456258647772437067L;
	
	private Filme filme = new Filme("tt0013442", "Nosferatu, eine Symphonie des Grauens", "F. W. Murnau", 1922, 81);
	
	private String buttonId = "";

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	// Lida com HTTP Action event
	public String navegarParaPaginaInicial() {
		return "index";
	}
	
	// Lida com UI Action event
	public void processadorAction(ActionEvent e) {
		this.setButtonId(e.getComponent().getId());
		System.out.println("Bean.processadorAction: " + this.buttonId);
	}
	
	
	// Lida com UI ValueChange event
	public void processadorValueChange(ValueChangeEvent e) {
		System.out.println("Componente: " + e.getComponent().getClass());
	}

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}
}
