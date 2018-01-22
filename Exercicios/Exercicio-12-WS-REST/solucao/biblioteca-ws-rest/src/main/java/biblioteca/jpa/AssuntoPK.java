/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.jpa;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class AssuntoPK implements Serializable, Comparable {
    private int ddcSummary;
    private String ddcClass;

    public int getDdcSummary() {
        return ddcSummary;
    }

    public void setDdcSummary(int ddcSummary) {
        this.ddcSummary = ddcSummary;
    }

    public String getDdcClass() {
        return ddcClass;
    }

    public void setDdcClass(String ddcClass) {
        this.ddcClass = ddcClass;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.ddcSummary;
        hash = 29 * hash + Objects.hashCode(this.ddcClass);
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
        final AssuntoPK other = (AssuntoPK) obj;
        if (this.ddcSummary != other.ddcSummary) {
            return false;
        }
        if (!Objects.equals(this.ddcClass, other.ddcClass)) {
            return false;
        }
        return true;
    }
    
        @Override
    public int compareTo(Object o) {
        AssuntoPK other = (AssuntoPK)o;
        return Integer.parseInt(this.ddcSummary * 1000 + this.ddcClass) - Integer.parseInt(other.ddcSummary * 1000 + other.ddcClass);
    }
}
