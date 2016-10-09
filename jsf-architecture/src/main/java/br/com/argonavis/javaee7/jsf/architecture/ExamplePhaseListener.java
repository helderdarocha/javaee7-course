package br.com.argonavis.javaee7.jsf.architecture;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class ExamplePhaseListener implements PhaseListener {

	private UIInput getComponent(FacesContext ctx) {
		String clientId = "formulario:msg";
		UIViewRoot view = ctx.getViewRoot();
		if(view != null) {
			UIComponent component = view.findComponent(clientId);
			if (component != null) {
				if(component instanceof UIInput) {
					return (UIInput)component;
				}
			}
		}
		return null;
	}

	@Override
	public void afterPhase(PhaseEvent evt) {
		PhaseId phaseId = evt.getPhaseId();
		FacesContext ctx = evt.getFacesContext();
		
		System.out.println(">>>>> AFTER PhaseID: " + phaseId.getOrdinal() + ": " + phaseId.getName());
		UIInput inputComponent = this.getComponent(ctx);
		if(inputComponent != null) {
			System.out.println("UIInput.getSubmittedValue(): " + inputComponent.getSubmittedValue());
			System.out.println("UIInput.getValue(): " + inputComponent.getValue());
		}
		System.out.println();
	}

	@Override
	public void beforePhase(PhaseEvent evt) {
		PhaseId phaseId = evt.getPhaseId();
		FacesContext ctx = evt.getFacesContext();
				
		System.out.println("<<<<< BEFORE PhaseID: " + phaseId.getOrdinal() + ": " + phaseId.getName());
		UIInput inputComponent = this.getComponent(ctx);
		if(inputComponent != null) {
			System.out.println("UIInput.getSubmittedValue(): " + inputComponent.getSubmittedValue());
			System.out.println("UIInput.getValue(): " + inputComponent.getValue());
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
