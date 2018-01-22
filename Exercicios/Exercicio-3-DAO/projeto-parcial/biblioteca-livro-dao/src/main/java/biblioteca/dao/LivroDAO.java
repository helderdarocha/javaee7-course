/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dao;

import biblioteca.Livro;
import java.io.IOException;
import java.util.Collection;

public interface LivroDAO {
    Livro findByID(int id) throws IOException;
    Livro findByISBN(String isbn) throws IOException;
    Collection<Livro> getLivros() throws IOException;
    int insert(Livro livro) throws IOException;
    void update(Livro livro) throws IOException;
    void delete(Livro livro) throws IOException;
}
