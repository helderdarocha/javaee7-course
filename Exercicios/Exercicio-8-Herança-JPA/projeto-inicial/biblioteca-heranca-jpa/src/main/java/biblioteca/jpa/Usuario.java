/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

// EXERCICIO: Transforme esta classe em uma entidade (@Entity e persistence.xml)
// EXERCICIO: Implemente relacionamento com Livro e Usuario
// EXERCICIO: Inclua um named query "getUsuarios" para selecionar todos os exemplares (veja outros exemplos)
// EXERCICIO: Configure o mapeamento com Exemplares
public class Usuario implements Serializable {

    private int id;
    private String nome;
    private Collection<Exemplar> emprestimos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Exemplar> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(Collection<Exemplar> emprestimos) {
        this.emprestimos = emprestimos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + id;
    }
    
}
