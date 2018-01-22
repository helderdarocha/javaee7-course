/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.LivroService;
import biblioteca.jpa.Livro;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named
@RequestScoped
public class LivroBean {
    
    // EXERCICIO: Injete o EJB da fachada de serviços aqui
    
    private Livro livroAtual; // livro usado no formulário inserir / gravar
    
    @PostConstruct
    public void init() {
        livroAtual = new Livro();
    }

    public Livro getLivro() {
        return livroAtual;
    }

    public void setLivro(Livro livro) {
        this.livroAtual = livro;
    }
    
    public Collection<Livro> getLivros() {
        // EXERCICIO: implementar delegando chamada para o EJB
        return null;
    }

    public String gravar() {
        // EXERCICIO: implementar delegando chamada para o EJB
        return "livros";
    }
    
    public String delete(Livro livro) {
        // EXERCICIO: implementar delegando chamada para o EJB
        return "livros";
    }
    
    public String edit(Livro livro) {
        this.setLivro(livro);
        return "livro";
    }
    
}
