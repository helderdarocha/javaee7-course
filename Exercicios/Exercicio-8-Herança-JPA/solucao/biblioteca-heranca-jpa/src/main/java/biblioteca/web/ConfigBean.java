/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.AssuntoService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ConfigBean {
    
    @EJB AssuntoService assuntoService;
    
    public boolean isAppNova() {
        try {
            return assuntoService.dataSize() == 0;
        } catch(Exception e) {
            return true; // no tables created
        }
    }
    
    public String configure() {
        assuntoService.configure();
        return "index";
    }

}
