/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web.jsf;

import biblioteca.jpa.Autor;
import biblioteca.web.BibliotecaBean;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("assuntoConverter")
public class AssuntoConverter implements Converter {

    //NÃO É MAIS SUPORTADO - BUG QUE SERA CORRIGIDO NA VERSAO 2.3
    //@Inject
    //private BibliotecaBean bean; 
    
    // WORKAROUND
    private BibliotecaBean bean = CDI.current().select(BibliotecaBean.class).get();

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent comp, String assunto) {
        String cdd = "";
        if(assunto.length() == 3) {
            return bean.getAssunto(assunto, 1);
        } else if (assunto.length() > 3) {
            cdd = assunto.substring(assunto.lastIndexOf("/")+1, assunto.length()); // "000/000" or "000/000/000"
            if(assunto.length() == 7) {
                return bean.getAssunto(cdd, 2);
            } else {
                return bean.getAssunto(cdd, 3);
            }
        } else return null;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent comp, Object obj) {
        if (obj == null) {
            return "";
        }
        return "" + obj;
    }
}
