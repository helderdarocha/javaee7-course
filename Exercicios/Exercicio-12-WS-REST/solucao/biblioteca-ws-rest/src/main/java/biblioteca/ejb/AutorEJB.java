/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Autor;
import biblioteca.jpa.Editora;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("autor")
public class AutorEJB implements AutorService {

    @PersistenceContext(unitName="biblioteca-PU")
    EntityManager em;

    @Override
    @GET 
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Autor findByID(@PathParam("id") int id) {
        return em.find(Autor.class, id);
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Autor> getAll() {
        return em.createNamedQuery("getAutores", Autor.class).getResultList();
    }

    @Override
    @POST 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public int insert(Autor autor) {
        em.persist(autor);
        return em.merge(autor).getId();
    }

    @Override
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void update(Autor autor) {
        em.merge(autor);
    }

    @Override
    public void delete(Autor autor) {
        em.remove(em.merge(autor));
    }
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        this.delete(this.findByID(id));
    }

    @Override
    @GET
    @Path("filterby/{filter}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Autor> getAutoresFilterBy(@PathParam("filter") String filter) {
        String jpql = "select a from Autor a where lower(a.nome) like :filter";
        TypedQuery<Autor> query = em.createQuery(jpql, Autor.class);
        query.setParameter("filter", "%" + filter.toLowerCase() + "%");
        return query.getResultList();
    }

}
