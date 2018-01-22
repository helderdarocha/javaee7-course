/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Exemplar;
import biblioteca.jpa.ExemplarEletronico;
import biblioteca.jpa.ExemplarImpresso;
import biblioteca.jpa.Livro;
import biblioteca.jpa.Usuario;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface ExemplarService {
    Exemplar findByID(int id);

    Collection<Exemplar> getAll();
    Collection<Exemplar> getByLivro(Livro livro);
    Collection<Livro> getLivrosComExemplares(boolean disponivel);
    
    Collection<ExemplarImpresso> getImpressoByLivro(Livro livro, boolean disponivel);
    Collection<ExemplarEletronico> getEbookByLivro(Livro livro, boolean disponivel);
    
    int insert(Exemplar exemplar);
    void update(Exemplar exemplar);
    void delete(Exemplar exemplar);

}
