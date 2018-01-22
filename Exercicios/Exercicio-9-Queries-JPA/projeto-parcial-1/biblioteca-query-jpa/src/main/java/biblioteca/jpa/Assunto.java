/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author helderdarocha
 */
@Entity
public class Assunto implements Serializable, Comparable {

    @EmbeddedId
    private AssuntoPK id;
    
    private String descricao;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="contexto_ddcSummary", referencedColumnName="ddcSummary"),
        @JoinColumn(name="contexto_ddcClass", referencedColumnName="ddcClass")
    })
    private Assunto contexto;
    
    @OneToMany(mappedBy = "contexto", cascade=CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="assuntos_ddcSummary", referencedColumnName="ddcSummary", insertable = false, updatable = false),
        @JoinColumn(name="assuntos_ddcClass", referencedColumnName="ddcClass", insertable = false, updatable = false)
    })
    private List<Assunto> assuntos = new ArrayList<>(10);
    
    public AssuntoPK getId() {
        return id;
    }

    public void setId(AssuntoPK id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Assunto getContexto() {
        return contexto;
    }

    public void setContexto(Assunto contexto) {
        this.contexto = contexto;
    }

    public List<Assunto> getAssuntos() {
        Collections.sort(assuntos);
        return assuntos;
    }

    public void setAssuntos(List<Assunto> assuntos) {
        this.assuntos = assuntos;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assunto)) {
            return false;
        }
        Assunto other = (Assunto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(contexto != null) {
            return contexto.toString() + "/" + id.getDdcClass();
        }
        return id.getDdcClass();
    }

    @Override
    public int compareTo(Object o) {
        return this.id.compareTo(((Assunto)o).id);
    }

}
