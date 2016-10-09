package br.com.argonavis.javaee7.jsf.architecture;

import javax.el.ELContext;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("mensagemConverter")
public class MensagemConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("MensagemConverter.getAsObject: " + arg2);
		
		if(arg2.equals("erro1")) {
			throw new ConverterException("Erro de conversão!");
		}

		Mensagem m = new Mensagem();
		m.setValor(arg2);
		return m;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		
		System.out.println("MensagemConverter.getAsString: " + arg2);
		return arg2.toString(); // tostring deste objeto contem o ID
	}

}
