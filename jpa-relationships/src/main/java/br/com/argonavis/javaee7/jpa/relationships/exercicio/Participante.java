package br.com.argonavis.javaee7.jpa.relationships.exercicio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries({
	  @NamedQuery(name="selectAllParticipantes",  query="SELECT p FROM Participante p")
}) 
public class Participante implements Serializable {
	
	@Id
	@SequenceGenerator(name="participante_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="participante_id_seq")
	private Long id;
	private String nome;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Ingresso ingresso;
	
	@ManyToMany(mappedBy="participantes")
	private Set<Etapa> etapas = new HashSet<>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Ingresso getIngresso() {
		return ingresso;
	}
	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}
	public Set<Etapa> getEtapas() {
		return etapas;
	}
	public void setEtapas(Set<Etapa> etapas) {
		this.etapas = etapas;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
