/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Exemplar;
import biblioteca.jpa.Usuario;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class CestaLivrosEJB implements CestaLivrosService {
    
    private final Map<String, Exemplar> exemplares = new HashMap<>(); // state
    
    @PersistenceContext(unitName="biblioteca-PU")
    EntityManager em;

    @Override
    public void addLivro(Exemplar e) {
        if(!exemplares.containsKey(e.getLivro().getIsbn())) { // permite apenas um exemplar de cada livro
            exemplares.put(e.getLivro().getIsbn(), e);
            e.setDisponivel(false);
            em.merge(e);
        }
    }

    @Override
    public void removeLivro(Exemplar e) {
        e.setDisponivel(true);
        if(exemplares.containsKey(e.getLivro().getIsbn())) {
            exemplares.remove(e.getLivro().getIsbn());
            em.merge(e);
        }
    }

    @Override
    public Collection<Exemplar> getConteudo() {
        return exemplares.values();
    }
    
    @Override
    public Map<String, Exemplar> getConteudoMap() {
        return exemplares;
    }

    @Override
    public void esvaziar() {
        for(Exemplar e : exemplares.values()) {
            e.setDisponivel(e.getUsuario() == null);
            em.merge(e);
        }
        exemplares.clear();
    }

    @Override
    public void emprestar(Usuario u) {
        u = em.merge(u);
        for(Exemplar e : this.getConteudo()) {
            e.setUsuario(u);
            u.getEmprestimos().add(e);
            em.merge(e);
        }
        esvaziar();
    }

}
