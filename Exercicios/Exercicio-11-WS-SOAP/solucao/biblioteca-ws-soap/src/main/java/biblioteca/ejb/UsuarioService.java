/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Usuario;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface UsuarioService {
    Usuario findByID(int id);
    
    Collection<Usuario> getAll();
    
    int insert(Usuario usuario);
    void update(Usuario usuario);
    void delete(Usuario usuario);

    public Usuario fakeLogin(String nome, String senha);
}
