package br.com.argonavis.javaee7.jpa.queries.exemplos;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente {
	@Id
	private Long id;
	
	@OneToMany
	private Set<Pedido> pedidos;
}
