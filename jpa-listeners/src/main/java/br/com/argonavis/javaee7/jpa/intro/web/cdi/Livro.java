package br.com.argonavis.javaee7.jpa.intro.web.cdi;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@EntityListeners(LivroListener.class)
@NamedQueries({
	  @NamedQuery(name="selectAll",  query="SELECT livro FROM Livro livro"),
	  @NamedQuery(name="selectById", query="SELECT livro FROM Livro livro WHERE livro.id=:id")
	}) 
public class Livro implements Serializable {

	@Id
	@SequenceGenerator(name="livro_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="livro_id_seq")
	private Long id;
	private String titulo;
	private int paginas;
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct chamado.");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("@PreDestroy chamado.");
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}   
	public int getPaginas() {
		return this.paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
}
