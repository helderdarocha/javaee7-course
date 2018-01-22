/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.ws.rest.client;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "autors")
@XmlAccessorType (XmlAccessType.FIELD)
public class Autores implements Serializable {
    
    public Autores() {}

    @XmlElement(name="autor")
    private Collection<Autor> autorList;
 
    public Collection<Autor> getAutorList() {
        return autorList;
    }
 
    public void setAutorList(Collection<Autor> autorList) {
        this.autorList = autorList;
    }

    
}
