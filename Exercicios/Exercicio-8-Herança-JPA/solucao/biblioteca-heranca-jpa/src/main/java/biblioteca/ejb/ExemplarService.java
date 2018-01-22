/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Exemplar;
import biblioteca.jpa.Livro;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface ExemplarService {
    Exemplar findByID(int id);

    Collection<Exemplar> getAll();
    Collection<Exemplar> getByLivro(Livro livro);
    
    int insert(Exemplar exemplar);
    void update(Exemplar exemplar);
    void delete(Exemplar exemplar);
}
