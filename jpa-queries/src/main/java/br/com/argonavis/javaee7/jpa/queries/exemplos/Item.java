package br.com.argonavis.javaee7.jpa.queries.exemplos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {
	@Id
	private Long id;
	
	@ManyToOne
	private Pedido pedido;
}
