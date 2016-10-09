package br.com.argonavis.javaee7.jsf.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="horarioConverter")
public class HorarioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String dateString) {
		
		DateFormat format = createDateFormat(arg1);
		Date date = null;
		    try {
				date = format.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		
		return date;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent component, Object date) {
		
		DateFormat format = createDateFormat(component);
		System.out.println("Called getAsString - " + format.format(date));
		return format.format(date);
	}
	
	private DateFormat createDateFormat(UIComponent arg1) {
		String locale  = (String)arg1.getAttributes().get("locale");
		String pattern = (String)arg1.getAttributes().get("pattern");
		DateFormat df = new SimpleDateFormat(pattern, new Locale(locale));
		return df;
	}

}
