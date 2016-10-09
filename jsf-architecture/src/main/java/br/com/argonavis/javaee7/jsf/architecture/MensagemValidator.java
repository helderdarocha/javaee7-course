package br.com.argonavis.javaee7.jsf.architecture;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("mensagemValidator")
public class MensagemValidator implements Validator {
	public void validate(FacesContext context, UIComponent component, Object obj) throws ValidatorException {
		
		Mensagem m = (Mensagem)obj;
		
		System.out.println("MensagemValidator.validate: " + m);
		
		if(m.getValor().equals("erro2")) {
			throw new ValidatorException(new FacesMessage("Erro de validação!"));
		}
	}
}
