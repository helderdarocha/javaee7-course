package br.com.argonavis.javaee7.jpa.queries.exemplos;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {
	
	@Id
	private Long id;
	private Double preco;
	private Double custo;
	private String fabricante;
	private String descricao;

}
