package br.com.argonavis.javaee7.jsf.architecture;

import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class MensagemBean implements Serializable {

	private static final long serialVersionUID = 893003243025347320L;

	private Mensagem mensagem;
	
	private UIInput inputComponent;
	
	@PostConstruct
	public void init() {
		System.out.println("MensagemBean CREATED");
	}

	public Mensagem getMensagem() {
		System.out.println("MensagemBean.getMensagem(): " + mensagem);
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
		System.out.println("MensagemBean.setMensagem(): " + mensagem);
	}
	
	public String processarMensagem() {
		System.out.println("MensagemBean.processarMensagem() - Processando a mensagem!");
		return "resultado";
	}

	public UIInput getInputComponent() {
		System.out.println("MensagemBean.getInputComponent()");
		return inputComponent;
	}

	public void setInputComponent(UIInput inputComponent) {
		this.inputComponent = inputComponent;
		if(inputComponent != null) {
			System.out.println("MensagemBean.setInputComponent("+inputComponent.getClientId()+")");
		}
	}
	
}
