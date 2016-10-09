package br.com.argonavis.javaee7.jpa.relationships.exercicio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries({
	  @NamedQuery(name="selectAllEtapas",  query="SELECT e FROM Etapa e")
}) 
public class Etapa implements Serializable {
	
	@Id
	@SequenceGenerator(name="etapa_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="etapa_id_seq")
	private Long id;
	
	private String nome;
	
	@ManyToOne
	private Corrida corrida;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="nome", column=@Column(name="ORIGEM_NOME"))
	})
	private Localidade origem;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="nome", column=@Column(name="DESTINO_NOME"))
	})
	private Localidade destino;
	
	@ManyToMany
	private Set<Participante> participantes = new HashSet<>();
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Corrida getCorrida() {
		return corrida;
	}
	public void setCorrida(Corrida corrida) {
		this.corrida = corrida;
	}
	
	public Localidade getOrigem() {
		return origem;
	}
	public void setOrigem(Localidade origem) {
		this.origem = origem;
	}
	public Localidade getDestino() {
		return destino;
	}
	public void setDestino(Localidade destino) {
		this.destino = destino;
	}
	public Set<Participante> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(Set<Participante> participantes) {
		this.participantes = participantes;
	}
	
	public void addParticipante(Participante participante) {
		participante.getEtapas().add(this);
		this.participantes.add(participante);
	}
	
}
