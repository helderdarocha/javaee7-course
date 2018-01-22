/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web.jsf;

import biblioteca.jpa.Autor;
import biblioteca.web.AutorBean;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("autorConverter")
public class AutorConverter implements Converter {

    //NÃO É MAIS SUPORTADO - BUG QUE SERA CORRIGIDO NA VERSAO 2.3
    //@Inject
    //private AutorBean bean; 
    
    // WORKAROUND
    private AutorBean bean = CDI.current().select(AutorBean.class).get();

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent comp, String id) {
        return bean.findByID(Integer.parseInt(id));
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent comp, Object obj) {
        if (obj == null) {
            return "0";
        }
        return "" + ((Autor)obj).getId();
    }
}
