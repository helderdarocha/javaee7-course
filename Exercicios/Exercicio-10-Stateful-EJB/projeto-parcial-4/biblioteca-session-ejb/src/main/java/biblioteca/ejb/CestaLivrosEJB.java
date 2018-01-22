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

// EXERCICIO: Implemente o método emprestar() nesta classe

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

    // EXERCICIO d.1: Implemente o método emprestar (veja dicas nos comentarios)
    @Override
    public void emprestar(Usuario u) {
        // 1) Obtenha uma cópia persistente sincronizada com o banco do Usuario recebido (caso esteja detached)
        // 2) Para cada exemplar guardado no mapa
        //    a) associe o usuario recebido (setUsuario)
        //    b) adicione o Exemplar a lista de emprestimos do Usuario
        //    c) sincronize com o banco (merge)
        //    d) esvazie a cesta
    }

}
