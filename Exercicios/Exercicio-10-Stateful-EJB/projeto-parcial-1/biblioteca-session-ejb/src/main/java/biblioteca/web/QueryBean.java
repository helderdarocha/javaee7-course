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
        
        // EXERCICIO 10.b - Implementar os 6 queries abaixo usando JPQL ou Criteria
        public Long getExemplaresPorLivro() {
            // EXERCICIO b.1: Número de exemplares de livros tem títulos que começam com "The"
            return null;
        }
        
        public Collection<Exemplar> getExemplaresPorTipo() {
            // EXERCICIO b.2: Lista de exemplares do tipo eletrônico
            return null;
        }

        public Collection<Exemplar> getExemplaresComMaisPaginas() {
            // EXERCICIO b.3: Exemplares impressos com mais de 250 páginas
            return null;
        }
        
        public Collection<Exemplar> getExemplaresEmprestados() {
            // EXERCICIO b.4: Lista de exemplares que estão emprestados
            return null;
        }
        
        public Collection<Exemplar> getExemplaresEmprestadosPorUsuario() {
            // EXERCICIO b.5: Lista de exemplares emprestados a "joaquim"
            return null;
        }

        public Collection<Livro> getLivrosEmprestados() {
            // EXERCICIO b.6: Livros cujos exemplares estão *todos* emprestados
            return null;
        }

}
