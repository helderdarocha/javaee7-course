package br.com.argonavis.javaee7.jpa.queries;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries({
	  @NamedQuery(name="selectAllCorridas",  query="SELECT c FROM Corrida c")
}) 
public class Corrida implements Serializable {
	@Id
	@SequenceGenerator(name="corrida_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="corrida_id_seq")
	private Long id;
	private String nome;
	
	@OneToMany(mappedBy="corrida", cascade={CascadeType.PERSIST})
	private Set<Etapa> etapas = new HashSet<>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Set<Etapa> getEtapas() {
		return etapas;
	}
	public void setEtapas(Set<Etapa> etapas) {
		this.etapas = etapas;
	}
	
}
