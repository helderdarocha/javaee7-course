package br.com.argonavis.javaee7.jsf.validator;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("transporte")
@SessionScoped
public class TransporteBean implements Serializable {

	private static final long serialVersionUID = 3205924462180203652L;

	private Destinatario destinatario = new Destinatario();
	private Pacote pacote = new Pacote();

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

	// Validação manual 2 - usando atributo validator de cada componente
	public void validarNull(FacesContext context, UIComponent comp, Object value) {
		System.out.println("Validando Nulls!");

		if(value == null || value.toString().length() == 0) {
			throw new ValidatorException(new FacesMessage("'"+comp.getId().toUpperCase()+"' é campo obrigatório!"));
		}
	}
	
	// Validação manual 1 - usando método de enviar que valida antes
	// ID é o clientID que tem formato formId:compId
	public String validarEnviar() {
		
		FacesContext context = FacesContext.getCurrentInstance();

		if(codigo.length() != 5) {
			context.addMessage("transporte:codigo", new FacesMessage("Código deve ter 5 digitos!"));
		}
		if(destinatario.getEmail() == null) {
			context.addMessage("transporte:email", new FacesMessage("E-mail é obrigatório!"));
		}
		if(pacote.getSeguro() > 10000.0 || pacote.getSeguro() < 1000.0) {
			context.addMessage("transporte:seguro", new FacesMessage("Seguro deve ser entre $1000 e $10000!"));
		}
		
		if (context.getMessageList().size() > 0) {
			return (null);
		} else {
			return enviar();
		}

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
