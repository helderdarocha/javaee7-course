package br.com.argonavis.javaee7.jpa.inheritance.singletable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

@Entity
// Default é @Inheritance(strategy=InheritanceType.SINGLE_TABLE
// Default é @DiscriminatorColumn(name="DTYPE")
public abstract class Veiculo {
	@Id
	@SequenceGenerator(name="veiculo_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="veiculo_id_seq")
	private Long id;
	private int capacidade;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
}
