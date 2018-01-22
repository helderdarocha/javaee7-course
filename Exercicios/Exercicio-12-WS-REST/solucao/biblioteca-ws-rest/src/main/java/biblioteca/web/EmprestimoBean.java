/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.ejb.CestaLivrosService;
import biblioteca.ejb.ExemplarService;
import biblioteca.jpa.Exemplar;
import biblioteca.jpa.Livro;
import biblioteca.jpa.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@Named
@SessionScoped
public class EmprestimoBean implements Serializable { // Login é necessário para acessar este bean

    @EJB CestaLivrosService cesta;
    @EJB ExemplarService exemplarService;
    
    @Inject 
    private FakeLoginBean loginBean;
    
    private Map<Integer, Boolean> idsDevolucao = new HashMap<>();
    private Collection<Exemplar> devolucoes;

    public Map<Integer, Boolean> getIdsDevolucao() {
        return idsDevolucao;
    }

    public Collection<Exemplar> getDevolucoes() {
        return devolucoes;
    }

    public Usuario getUsuario() {
        return loginBean.getUsuarioLogado();
    }
    
    public Collection<Exemplar> getConteudo() {
        return cesta.getConteudo(); 
    }
    
    public Collection<Livro> getLivrosComExemplaresDisponiveis() {
        return exemplarService.getLivrosComExemplares(true);
    }
    
    public Collection<Exemplar> getExemplaresEmprestados() {
        return exemplarService.getExemplaresEmprestados(getUsuario()); 
    }
    
    public boolean selecionado(Livro livro) {
        if (cesta.getConteudoMap().containsKey(livro.getIsbn())) {
            return true;
        }
        return false;
    }
    
    public int impressoCount(Livro livro) {
        return exemplarService.getImpressoByLivro(livro, true).size();
    }
     
    public int ebookCount(Livro livro) {
        return exemplarService.getEbookByLivro(livro, true).size();
    }
    
    public String addEbook(Livro livro) {
        return addLivro(exemplarService.getEbookByLivro(livro, true).iterator().next());
    }
    
    public String addImpresso(Livro livro) {
        return addLivro(exemplarService.getImpressoByLivro(livro, true).iterator().next());
    }
    
    public Collection<Exemplar> getByLivro(Livro livro) {
        return exemplarService.getByLivro(livro);
    }
    
    public String addLivro(Exemplar e) {
        cesta.addLivro(e); 
        return "cesta-emprestimo";
    }
    
    public String removeLivro(Exemplar e) {
        cesta.removeLivro(e); // remove e marca como disponivel
        return null;
    }
    
    public String emprestar() {
        cesta.emprestar(getUsuario());
        return "emprestados"; 
    }
    
    public String devolver() {
        // Get selected items.
        devolucoes = new ArrayList<Exemplar>();
        for (Exemplar e : getExemplaresEmprestados()) {
            if (idsDevolucao.get(e.getId())) {
                devolucoes.add(e);
                idsDevolucao.remove(e.getId());
            }
        }

        for(Exemplar e : devolucoes) {
            System.out.println(">>> devolvendo " + e.getId());
            exemplarService.devolver(e);
        }
        return "emprestados"; 
    }
    
    public String cancelar() {
        esvaziar();
        return "emprestimo";
    }
    
    public void esvaziar() {
        cesta.esvaziar();
    }
    
}
