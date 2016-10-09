package br.com.argonavis.javaee7.jsf.listener;

import javax.el.ELContext;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class RegistroDeClique implements ActionListener {
	
	// (1) Vale apenas para escopos CDI - RECOMENDADO!
	//@Inject Bean bean;

	@Override
	public void processAction(ActionEvent evt) throws AbortProcessingException {
		String componenteId = evt.getComponent().getId();
		System.out.println("RegistroDeClique.processActionEvent: " + componenteId);
		
		// (2) Vale para escopos JSF e escopos CDI) - FUNCIONA PARA APPS LEGADAS E NOVAS
		// Tentar achar usando ELResolver 
		FacesContext ctx = FacesContext.getCurrentInstance();
		ELContext elCtx = ctx.getELContext();
		Application app = ctx.getApplication();
		
		Bean bean = (Bean)app.getELResolver().getValue(elCtx, null, "bean");
		
		// (3) Vale apenas para escopos JSF - USE EM APPS ANTIGAS
		//Bean bean = (Bean)ctx.getExternalContext().getSessionMap().get("bean");
		
		bean.setButtonId(componenteId);
	}

}
