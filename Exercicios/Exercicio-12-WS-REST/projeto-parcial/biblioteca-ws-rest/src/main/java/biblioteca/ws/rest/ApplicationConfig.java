/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ws.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

//EXERCICIO: defina o contexto raiz do serviço REST (ex: /restapi)
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(biblioteca.ejb.AutorEJB.class);
        return resources;
    }
    
}
