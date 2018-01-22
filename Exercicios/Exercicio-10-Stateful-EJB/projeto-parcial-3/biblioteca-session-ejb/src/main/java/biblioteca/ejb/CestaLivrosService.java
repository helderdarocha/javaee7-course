/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Exemplar;
import biblioteca.jpa.Usuario;
import java.util.Collection;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author helderdarocha
 */
@Local
public interface CestaLivrosService {
    void addLivro(Exemplar e);
    void removeLivro(Exemplar e);
    Collection<Exemplar> getConteudo();
    Map<String, Exemplar> getConteudoMap();
    void esvaziar();
}
