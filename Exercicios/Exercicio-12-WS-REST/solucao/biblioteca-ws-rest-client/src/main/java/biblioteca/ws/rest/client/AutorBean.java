/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ws.rest.client;

import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Named
@RequestScoped
public class AutorBean {

    private Autor current; 
    private String textoBusca = "";
    
    private WebTarget resource;
    
    @PostConstruct
    public void init() {
        current = new Autor();
        resource = ClientBuilder.newClient().target("http://localhost:8080/biblioteca-ws-rest/restapi/autor");
    }

    public String getTextoBusca() {
        return textoBusca;
    }

    public void setTextoBusca(String textoBusca) {
        this.textoBusca = textoBusca;
    }

    public Autor getCurrent() {
        return current;
    }
    
    public Collection<Autor> getAutores() {
        if(textoBusca != null && textoBusca.trim().length() != 0 && !resource.toString().contains("/filterby/")) {
           resource = resource.path("/filterby/"+textoBusca);
           System.out.println(">>>" + resource.toString());
        }
        
        return resource
                .request(MediaType.APPLICATION_XML)
                .get(Autores.class)
                .getAutorList();
    }
    
    public Autor findByID(int id) {
        return resource.path(""+id)
                .request(MediaType.APPLICATION_JSON)
                .get(Autor.class);
    }

    public void setCurrent(Autor autor) {
        this.current = autor;
    }
    
    public String gravar() {
        Entity entity = Entity.entity(current, MediaType.APPLICATION_XML);
        resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                .put(entity);
        return "autores";
    }
    
    public String delete(Autor autor) {
        resource.path(""+autor.getId())
                .request()
                .delete();
        return "autores";
    }
    
    public String edit(Autor autor) {
        this.setCurrent(autor);
        return "autor";
    }
}
