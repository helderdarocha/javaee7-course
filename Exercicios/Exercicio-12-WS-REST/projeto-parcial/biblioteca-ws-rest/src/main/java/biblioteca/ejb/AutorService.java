/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Autor;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface AutorService {
    Autor findByID(int id);
    
    Collection<Autor> getAll();
    Collection<Autor> getAutoresFilterBy(String filter);
    
    int insert(Autor autor);
    void update(Autor autor);
    void delete(Autor autor);
}
