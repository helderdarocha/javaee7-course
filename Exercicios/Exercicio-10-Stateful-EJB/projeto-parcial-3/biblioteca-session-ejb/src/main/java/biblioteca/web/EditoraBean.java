/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.EditoraService;
import biblioteca.jpa.Editora;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class EditoraBean {
    @EJB EditoraService service;
    
    private Editora current; 
    private String textoBusca = "";
    
    @PostConstruct
    public void init() {
        current = new Editora();
    }

    public String getTextoBusca() {
        return textoBusca;
    }

    public void setTextoBusca(String textoBusca) {
        this.textoBusca = textoBusca;
    }

    public Editora getCurrent() {
        return current;
    }

    public void setCurrent(Editora editora) {
        this.current = editora;
    }
    
    public Collection<Editora> getEditoras() {
        return service.getEditorasFilterBy(textoBusca);
    }
    
    public Editora findByID(int id) {
        return service.findByID(id);
    }
    
    public String gravar() {
        service.update(current);
        return "editoras";
    }
    
    public String delete(Editora editora) {
        service.delete(editora);
        return "editoras";
    }
    
    public String edit(Editora editora) {
        this.setCurrent(editora);
        return "editora";
    }
}
