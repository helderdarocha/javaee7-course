/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.jpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

// EXERCICIO: Transforme esta classe em uma entidade (@Entity e persistence.xml)
// EXERCICIO: Implemente relacionamento com Livro e Usuario
// EXERCICIO: Inclua um named query "getExemplares" para selecionar todos os exemplares (veja outros exemplos)
// EXERCICIO: Configure a hierarquia proposta no exercício (herança) em JPA
// EXERCICIO: Crie as subclasses ExemplarEletronico e ExemplarImpresso

public abstract class Exemplar implements Serializable {

    private int id;
    private boolean disponivel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Exemplar other = (Exemplar) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "biblioteca.jpa.Exemplar[ id=" + id + " ]";
    }
    
}
