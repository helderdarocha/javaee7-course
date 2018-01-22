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

// EXERCICIO: Este bean mantem um Map associado ao escopo do componente que
// o chamar. Implemente os metodos addLivro, removeLivro e esvaziar

@Stateful
public class CestaLivrosEJB implements CestaLivrosService {
    
    private final Map<String, Exemplar> exemplares = new HashMap<>(); // state
    
    @PersistenceContext(unitName="biblioteca-PU")
    EntityManager em;

    // EXERCICIO c.2: Implemente o método para adicionar exemplar na cesta.
    // Veja dicas nos comentarios
    @Override
    public void addLivro(Exemplar e) {
        // 1) Se Map exemplares contem a chave (containsKey) igual ao ISBN
        //    do livro associado ao exemplar recebido como parametro
        //    a) inclua (put) o exemplar no mapa, usando ISBN como chave
        //    b) marque o exemplar como indisponivel
        //    c) sincronize com o banco (merge)
    }

    // EXERCICIO c.4: Implemente o método para remover um exemplar da cesta.
    // Veja dicas nos comentarios
    @Override
    public void removeLivro(Exemplar e) {
        // 1) Marque como disponivel
        // 2) Se Map exemplares contiver a chave com valor igual (containsKey)
        //    ao string com o ISBN do livro
        //    a) remova o objeto do Map exemplares usando ISBN como chave
        //    b) sincronize com o banco (merge)
    }

    // EXERCICIO c.3 (RESOLVIDO): Retorne uma collection contendo os valores do Map
    @Override
    public Collection<Exemplar> getConteudo() {
        return exemplares.values();
    }
    
    // getter
    @Override
    public Map<String, Exemplar> getConteudoMap() {
        return exemplares;
    }

    // EXERCICIO c.6: Implemente o método para esvaziar a cesta.
    // Veja dicas nos comentarios
    @Override
    public void esvaziar() {
        // Para cada Exemplar da cesta
        //   a) Marcar como disponivel *se* usuario for null (caso contrario, marcar como indiponivel)
        //   b) sincronizar com o banco (merge)
        // Esvaziar o Map exemplares.
    }


}
