/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Assunto;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
