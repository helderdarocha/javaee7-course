/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Livro;
import java.io.IOException;
import java.util.Collection;
import javax.ejb.Local;

// EXERCICIO: Anote esta interface para que possa ser usada como
// uma interface EJB Local
public interface LivroService {
    Livro findByID(int id);
    Livro findByISBN(String isbn);
    Collection<Livro> getLivros();
    int insert(Livro livro);
    void update(Livro livro);
    void delete(Livro livro);
}
