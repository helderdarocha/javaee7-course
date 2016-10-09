package br.com.argonavis.javaee7.jsf.architecture;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

public class MensagemValueChangeListener implements ValueChangeListener {

	@Override
	public void processValueChange(ValueChangeEvent e) throws AbortProcessingException {
		System.out.println("ValueChangeEvent - changed " 
	                       + e.getOldValue() + " to " 
				           + e.getNewValue());
	}

}
