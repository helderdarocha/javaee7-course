/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Editora;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface EditoraService {
    Editora findByID(int id);
    
    Collection<Editora> getAll();
    
    int insert(Editora editora);
    void update(Editora editora);
    void delete(Editora editora);
}
