package br.com.argonavis.javaee7.jpa.inheritance.tableperclass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
// é entidade mas nao cria tabela!
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
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
