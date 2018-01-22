/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.UsuarioService;
import biblioteca.jpa.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/** 
 * Autenticação em, Java EE ou é dependente de plataforma ou muito complexa. 
 * No Exercicio-15 é desenvolvido um módulo de login via facebook independente
 * de servidor de aplicação, com roles configurados em XML do Glassfish.
 * Neste exemplo o login apenas compara strings sem usar mecanismo de segurança.
 */
@Named
@SessionScoped
public class FakeLoginBean implements Serializable {

    private String nome;
    private String senha;
    private Usuario usuarioLogado;
    
    @EJB
    private UsuarioService service;
    
    @Produces
    @Named("usuarioLogado")
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String login() throws IOException {
        usuarioLogado = service.fakeLogin(nome, senha);
        if(usuarioLogado == null) {
            return logout();
        }
        return "index"; 
    }

    public String logout() throws IOException {
        usuarioLogado = null;
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        ectx.invalidateSession();
        return "fake-login";
    }
}
