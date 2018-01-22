/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Autor;
import biblioteca.jpa.Livro;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class LivroEJB implements LivroService {

    @PersistenceContext(unitName="biblioteca-PU")
    EntityManager em;

    @Override
    public Livro findByID(int id) {
        return em.find(Livro.class, id);
    }

    @Override
    public Livro findByISBN(String isbn) {
        String jpql = "select distinct livro from Livro livro where livro.isbn = :isbn";
        TypedQuery<Livro> query = em.createQuery(jpql, Livro.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }
    
    @Override
    public Collection<Livro> getByCriteria(String matchTitulo, String matchIdioma, String matchAutor, String matchEditora, String matchAssunto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Livro> query = cb.createQuery(Livro.class);
        Root<Livro> root = query.from(Livro.class);
        
        List<Predicate> predicates = new ArrayList<>();

        // Se houver texto no campo titulo
        if(matchTitulo != null && !matchTitulo.trim().isEmpty()) {
            Path<String> path = root.get("titulo");
            predicates.add(cb.like(cb.lower(path), "%"+matchTitulo.toLowerCase()+"%"));
        }
        
        // Se houver texto no campo idioma
        if(matchIdioma != null && !matchIdioma.trim().isEmpty()) {
            Path<String> path = root.get("idioma");
            predicates.add(cb.equal(cb.lower(path), matchIdioma.toLowerCase()));
        }

        // Se houver texto no campo autor
        if(matchAutor != null && !matchAutor.trim().isEmpty()) {
            Join<Livro, Autor> autor = root.join("autores");
            Path<String> path = autor.get("nome");
            predicates.add(cb.like(cb.lower(path), "%"+matchAutor.toLowerCase()+"%"));
        }
        
        // Se houver texto no campo editora
        if(matchEditora != null && !matchEditora.trim().isEmpty()) {
            Path<String> path = root.get("editora").get("nome");
            predicates.add(cb.like(cb.lower(path), "%"+matchEditora.toLowerCase()+"%"));
        }
        
        // Se houver texto no campo assunto
        if(matchAssunto != null && !matchAssunto.trim().isEmpty()) {
            List<Predicate> orPredicates = new ArrayList<>();
            Path[] paths = {root.get("assunto").get("descricao"),
                            root.get("assunto").get("contexto").get("descricao"),
                            root.get("assunto").get("contexto").get("contexto").get("descricao")};
            for(int i = 0; i < paths.length; i++) {
                orPredicates.add(cb.like(cb.lower(paths[i]), "%"+matchAssunto.toLowerCase()+"%"));
            }
            
            Path assuntoPath = root.get("assunto").get("id").get("ddcClass");
            orPredicates.add(cb.like(cb.lower(assuntoPath), matchAssunto.toLowerCase()+"%"));
            
            predicates.add(cb.or(orPredicates.toArray(new Predicate[orPredicates.size()])));
        }

        if(!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }
        
        query.select(root);
        TypedQuery<Livro> q = em.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public Collection<Livro> getAll() {
        return em.createNamedQuery("getLivros", Livro.class).getResultList();
    }

    @Override
    public int insert(Livro livro) {
        em.persist(livro);
        return em.merge(livro).getId();
    }

    @Override
    public void update(Livro livro) {
        em.merge(livro);
    }

    @Override
    public void delete(Livro livro) {
        em.remove(em.merge(livro));
    }

    @Override
    public Collection<Locale> getLocales() {
        String jpql = "select distinct livro.idioma from Livro livro";
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        List<String> idiomas = query.getResultList();
        List<Locale> locales = new ArrayList<>();
        for(String idioma : idiomas) {
            Locale locale = Locale.forLanguageTag(idioma);
            if(locale == null) {
                locale = new Locale(idioma);
            }
            locales.add(locale);
        }
        return locales;
    }

}
