/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Usuario;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsuarioEJB implements UsuarioService {

    @PersistenceContext(unitName="biblioteca-PU")
    EntityManager em;

    @Override
    public Usuario findByID(int id) {
        return em.find(Usuario.class, id);
    }

    @Override
    public Collection<Usuario> getAll() {
        return em.createNamedQuery("getUsuarios", Usuario.class).getResultList();
    }

    @Override
    public int insert(Usuario usuario) {
        em.persist(usuario);
        return em.merge(usuario).getId();
    }

    @Override
    public void update(Usuario usuario) {
        em.merge(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        em.remove(em.merge(usuario));
    }

}
