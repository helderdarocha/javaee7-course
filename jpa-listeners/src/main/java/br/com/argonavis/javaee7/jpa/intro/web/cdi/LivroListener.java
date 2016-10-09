package br.com.argonavis.javaee7.jpa.intro.web.cdi;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class LivroListener {
	
	private int counter = 0;
	
	@PostLoad
    public void carregado(Livro livro) {
		livro.setTitulo(livro.getTitulo().toUpperCase()); // muda apenas a instancia, pois não houve update
    	System.out.println(++counter + ": @PostLoad: " + livro.getId() + ": " + livro.getTitulo());
    }
	
	@PrePersist
    public void persistindo(Livro livro) {
    	System.out.println(++counter + ": @PrePersist: " + livro.getId() + ": " + livro.getTitulo());
    }
	
	@PostPersist
    public void persistido(Livro livro) {
    	System.out.println(++counter + ": @PostPersist: " + livro.getId() + ": " + livro.getTitulo());
    }
	
	@PreUpdate
    public void atualizando(Livro livro) {
		livro.setTitulo("Titulo alterado no listener"); // muda a entidade, pois logo após ocorre um update
    	System.out.println(++counter + ": @PreUpdate: " + livro.getId() + ": " + livro.getTitulo());
    }
	
	@PostUpdate
    public void atualizado(Livro livro) {
    	System.out.println(++counter + ": @PostUpdate: " + livro.getId() + ": " + livro.getTitulo());
    }
	
	@PreRemove
    public void removendo(Livro livro) {
    	System.out.println(++counter + ": @PreRemove: " + livro.getId() + ": " + livro.getTitulo());
    }
}
