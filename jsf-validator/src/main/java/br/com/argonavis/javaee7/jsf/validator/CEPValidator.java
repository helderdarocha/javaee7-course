package br.com.argonavis.javaee7.jsf.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("CEPValidator")
public class CEPValidator implements Validator {
	
	private Pattern pattern = Pattern.compile("\\d{5}-\\d{3}");

	@Override
	public void validate(FacesContext ctx, UIComponent comp, Object value) throws ValidatorException {
		
		Matcher matcher = pattern.matcher(value.toString());
		if(!matcher.matches()) {
			FacesMessage msg = new FacesMessage("CEP deve ter o formato NNNNN-NNN.");
			throw new ValidatorException(msg);
		}

	}
	
}
