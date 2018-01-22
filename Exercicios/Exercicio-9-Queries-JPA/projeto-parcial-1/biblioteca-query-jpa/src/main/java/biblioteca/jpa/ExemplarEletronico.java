/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExemplarEletronico extends Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Long tamanho;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
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
        final ExemplarEletronico other = (ExemplarEletronico) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "biblioteca.jpa.ExemplarEletronico[ id=" + id + " ]";
    }
    
}
