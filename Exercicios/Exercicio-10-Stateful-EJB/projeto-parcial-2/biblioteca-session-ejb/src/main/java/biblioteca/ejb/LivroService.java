/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Livro;
import java.util.Collection;
import java.util.Locale;
import javax.ejb.Local;

@Local
public interface LivroService {
    Livro findByID(int id);
    Livro findByISBN(String isbn);
    
    Collection<Livro> getAll();
    Collection<Livro> getByCriteria(String titulo, String idioma, String autor, String editora, String assunto);
    
    Collection<Locale> getLocales();
    
    int insert(Livro livro);
    void update(Livro livro);
    void delete(Livro livro);
}
