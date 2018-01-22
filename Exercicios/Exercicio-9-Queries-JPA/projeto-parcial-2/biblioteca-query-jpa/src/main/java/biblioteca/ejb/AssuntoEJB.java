/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Assunto;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 *
 * @author helderdarocha
 */
@Singleton
@Startup
public class AssuntoEJB implements AssuntoService {

    @PersistenceContext(unitName = "biblioteca-PU")
    EntityManager em;

    public void configure() {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader reader = sp.getXMLReader();
            reader.setContentHandler(new AssuntosSaxHandler(em));

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream in = loader.getResourceAsStream("META-INF/ddc21.xml");

            reader.parse(new InputSource(in));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(AssuntoEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Collection<Assunto> getAll() {
        String jpql = "select distinct a from Assunto a order by a.id.ddcSummary asc, a.id.ddcClass asc";
        TypedQuery<Assunto> query = em.createQuery(jpql, Assunto.class);
        return query.getResultList();
    }
    
    @Override
    public Collection<Assunto> getByCriteria(int filtroSummary, String filtroClasse, String filtroDescricao) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Assunto> query = cb.createQuery(Assunto.class);
        Root<Assunto> root = query.from(Assunto.class);
        
        List<Predicate> predicates = new ArrayList<>();

        // Se houver texto no campo descricao
        if(filtroDescricao != null && !filtroDescricao.trim().isEmpty()) {
            Path<String> path = root.get("descricao");
            predicates.add(cb.like(cb.lower(path), "%"+filtroDescricao.toLowerCase()+"%"));
        }
        
        // Se houver texto no campo classe DDC
        if(filtroClasse != null && !filtroClasse.trim().isEmpty()) {
            Path<String> path = root.get("id").get("ddcClass");
            predicates.add(cb.like(path, filtroClasse+"%"));
        }
        
        // Filtro por summary
        if(filtroSummary != 0) { 
            Path<String> path = root.get("id").get("ddcSummary");
            predicates.add(cb.equal(path, filtroSummary));
        }

        if(!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }
        
        query.select(root);
        query.orderBy(cb.asc(root.get("id").get("ddcClass")));
        TypedQuery<Assunto> q = em.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public Assunto getAssunto(String cdd, int summary) {
        String jpql = "select distinct a from Assunto a where a.id.ddcClass = :ddc and a.id.ddcSummary = :summary";
        TypedQuery<Assunto> query = em.createQuery(jpql, Assunto.class);
        query.setParameter("ddc", cdd);
        query.setParameter("summary", summary);
        return query.getSingleResult();
    }

    @Override
    public Collection<Assunto> getRoots() {
        String jpql = "select distinct a from Assunto a where a.id.ddcSummary = 1 order by a.id asc";
        TypedQuery<Assunto> query = em.createQuery(jpql, Assunto.class);
        return query.getResultList();
    }
    
    @Override
    public long dataSize() {
        TypedQuery<Long> query = em.createQuery("select count(a) from Assunto a", Long.class);
        return query.getSingleResult();
    }

}
