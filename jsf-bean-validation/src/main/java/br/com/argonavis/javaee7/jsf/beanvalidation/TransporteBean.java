package br.com.argonavis.javaee7.jsf.beanvalidation;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Size;

@Named("transporte")
@SessionScoped
public class TransporteBean implements Serializable {

	private static final long serialVersionUID = 3205924462180203652L;

	private Destinatario destinatario = new Destinatario();
	private Pacote pacote = new Pacote();

	@Size(max = 5, min = 5)
	private String codigo;

	public TransporteBean() {
	}

	public Destinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String enviar() {
		System.out.println("Enviando pacote!");
		return "enviado";
	}

	public String cancelar() {
		System.out.println("Cancelando pedido!");
		return "index";
	}

}
