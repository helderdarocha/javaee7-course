/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao;

import biblioteca.Livro;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public interface LivroService {
    Livro findByID(int id);
    Livro findByISBN(String isbn);
    Collection<Livro> getLivros();
    int insert(Livro livro);
    void update(Livro livro);
    void delete(Livro livro);
}
