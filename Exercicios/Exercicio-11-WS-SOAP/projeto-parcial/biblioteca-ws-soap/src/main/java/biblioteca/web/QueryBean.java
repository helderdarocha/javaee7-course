/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.web;

import biblioteca.jpa.Autor;
import biblioteca.jpa.Editora;
import biblioteca.jpa.Exemplar;
import biblioteca.jpa.ExemplarEletronico;
import biblioteca.jpa.Livro;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author helderdarocha
 */
@Named(value = "queryBean")
@RequestScoped
public class QueryBean {

    @PersistenceContext(unitName = "biblioteca-PU")
    private EntityManager em;

    public Collection<Autor> getAutorPorNome() {
            String jpql="select a from Autor a where a.nome like '%an%'";
            TypedQuery<Autor> query = em.createQuery(jpql, Autor.class);
            return query.getResultList();
        }

        public Collection<Editora> getEditoraPorNome() {
            String jpql="select e from Editora e where e.nome like '%B%'";
            TypedQuery<Editora> query = em.createQuery(jpql, Editora.class);
            return query.getResultList();
        }

        public Collection<Livro> getLivroPorTitulo() {
            String jpql="select o from Livro o where o.titulo like '%The%'";
            TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
            return query.getResultList();
        }
        
        public Collection<Livro> getLivroPorAssunto() {
            String jpql="select o from Livro o where o.assunto.id.ddcClass like '5%'";
            TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
            return query.getResultList();
        }
        
        public Collection<Livro> getLivroPorAutor() {
            String jpql="select o from Livro o join o.autores autor where autor.nome like 'Mary%'";
            TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
            return query.getResultList();
        }

        public Collection<Autor> getAutorPorEditora() {
            String jpql="select a from Autor a join a.obras obra where obra.editora.nome like '%Boo%'";
            TypedQuery<Autor> query = em.createQuery(jpql, Autor.class);
            return query.getResultList();
        }
        
        // Exercicio 10 
        public Long getExemplaresPorLivro() {
            String jpql="select count(e) from Exemplar e where e.livro.titulo like '%The%'";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            return query.getSingleResult();
        }

        public Collection<Exemplar> getExemplaresComMaisPaginas() {
            String jpql="select e from ExemplarImpresso e where e.paginas > 250";
            TypedQuery<Exemplar> query = em.createQuery(jpql, Exemplar.class);
            return query.getResultList();
        }
       

        public Collection<Exemplar> getExemplaresEmprestadosPorUsuario() {
            String jpql="select e from Exemplar e where e.usuario.nome = 'joaquim'";
            TypedQuery<Exemplar> query = em.createQuery(jpql, Exemplar.class);
            return query.getResultList();
        }

        
        public Collection<Exemplar> getExemplaresEmprestados() {
            String jpql="select e from Exemplar e where e.usuario is not null";
            TypedQuery<Exemplar> query = em.createQuery(jpql, Exemplar.class);
            return query.getResultList();
        }
        
        public Collection<Exemplar> getExemplaresPorTipo() {
            String jpql="select e from Exemplar e where type(e) = :type";
            TypedQuery<Exemplar> query = em.createQuery(jpql, Exemplar.class);
            query.setParameter("type", ExemplarEletronico.class);
            return query.getResultList();
        }
        
                
        public Collection<Livro> getLivrosEmprestados() {
            String jpql="select distinct o from Livro o where (select count(e) from Exemplar e where e.livro = o and e.usuario is null) = 0";
            TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
            return query.getResultList();
        }

}
