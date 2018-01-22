/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ejb;

import biblioteca.jpa.Assunto;
import biblioteca.jpa.AssuntoPK;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.EntityManager;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AssuntosSaxHandler extends DefaultHandler {
    
    EntityManager em;
            
    public AssuntosSaxHandler(EntityManager em) {
        this.em = em;
    }
    
    int ddcSummary;
    String ddcClass;
    String ddcDescription = "";
    boolean entry = false;
    Map<String, Assunto> ddc = new TreeMap<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if(qName.equals("entry")) {
            entry = true;
            try {
                String d = atts.getValue("summary");
                if (d != null) {
                    ddcSummary = Integer.parseInt(d); 
                }
            } catch (NumberFormatException e) {}
            
            ddcClass = atts.getValue("class");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(entry) {
            ddcDescription += new String(ch, start, length);
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("entry")) {
           entry = false;
           Assunto assunto = new Assunto();
           AssuntoPK key = new AssuntoPK();
           key.setDdcClass(ddcClass);
           key.setDdcSummary(ddcSummary);
           assunto.setId(key);
           assunto.setDescricao(ddcDescription);
           
           if(assunto.getId().getDdcSummary() == 1) {
               ddc.put(assunto.getId().getDdcClass(), assunto);
           }
           
           if(assunto.getId().getDdcSummary() == 2) {
               String group = assunto.getId().getDdcClass().substring(0,1) + "00";
               int position = Integer.parseInt(assunto.getId().getDdcClass().substring(1,2));
               Assunto root = ddc.get(group);
               root.getAssuntos().add(position, assunto);
               assunto.setContexto(root);
           }
           
           if(assunto.getId().getDdcSummary() == 3) {
               String group = assunto.getId().getDdcClass().substring(0,1) + "00";
               int position = Integer.parseInt(assunto.getId().getDdcClass().substring(1,2));
               Assunto root = ddc.get(group);
               Assunto level2 = root.getAssuntos().get(position);
               int position2 = Integer.parseInt(assunto.getId().getDdcClass().substring(2,3));
               level2.getAssuntos().add(position2, assunto);
               assunto.setContexto(level2);
           }
           
           ddcDescription = "";
           ddcClass = null;
           ddcSummary = 0;
        }
    }
    
    @Override
    public void endDocument() throws SAXException {
        for(Assunto a : ddc.values()) {
            em.persist(a);
        }
    }
    
}
