/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.UsuarioService;
import biblioteca.jpa.Usuario;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UsuarioBean {
    @EJB UsuarioService service;
    
    private Usuario current; 
    
    @PostConstruct
    public void init() {
        current = new Usuario();
    }

    public Usuario getCurrent() {
        return current;
    }
    
    public Usuario findByID(int id) {
        return service.findByID(id);
    }

    public void setCurrent(Usuario usuario) {
        this.current = usuario;
    }
    
    public String gravar() {
        service.update(current);
        return "usuarios";
    }
    
    public String delete(Usuario usuario) {
        service.delete(usuario);
        return "usuarioes";
    }
    
    public String edit(Usuario usuario) {
        this.setCurrent(usuario);
        return "usuario";
    }
}
