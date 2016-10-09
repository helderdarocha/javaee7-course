package br.com.argonavis.javaee7.jpa.queries;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQueries({
	  @NamedQuery(name="selectAllIngressos",  query="SELECT i FROM Ingresso i")
}) 
public class Ingresso implements Serializable {
	
	@Id
	@SequenceGenerator(name="ingresso_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ingresso_id_seq")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private StatusIngresso status;
	private BigDecimal valor;
	
	@OneToOne(mappedBy="ingresso", cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Participante participante;
	
	public StatusIngresso getStatus() {
		return status;
	}
	public void setStatus(StatusIngresso status) {
		this.status = status;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Participante getParticipante() {
		return participante;
	}
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
