package br.com.argonavis.javaee7.jsf.listener;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

public class RegistroDeAlteracao implements ValueChangeListener {

	@Override
	public void processValueChange(ValueChangeEvent evt) throws AbortProcessingException {
		Filme antigo = (Filme)evt.getOldValue();
		Filme novo   = (Filme)evt.getNewValue();
		
		System.out.println("Filme anterior: " + antigo);
		System.out.println("Filme atual: " + novo + "\n");
	}
}
