package br.com.argonavis.javaee7.cdi.cdi;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.com.argonavis.javaee7.cdi.Biblioteca;
import br.com.argonavis.javaee7.cdi.Livro;
import br.com.argonavis.javaee7.cdi.cdi.qualifier.LocalQualifier;
import br.com.argonavis.javaee7.cdi.cdi.qualifier.NetworkQualifier;
import br.com.argonavis.javaee7.cdi.cdi.qualifier.UnitTestQualifier;

public class BibliotecaImpl2 implements Biblioteca {
	private BibliotecaStorage storage;
	
	@Inject 
	@LocalQualifier
	private BibliotecaStorage local;
	
	@Inject 
	@NetworkQualifier
	private BibliotecaStorage remoto;
	
	@Inject 
	@UnitTestQualifier
	private BibliotecaStorage teste;

	@PostConstruct
	protected void init() {
		BibliotecaStorage[] options = {local, remoto, teste};
		storage = options[(int)Math.floor(Math.random() * 3)];
	}

	public void emprestar(Livro livro) {
	    livro.setStatus(Livro.Status.EMPRESTADO);
	    System.out.println("Livro " + livro + " emprestado.");
	    storage.atualizarStatus(livro);
	}

	public void devolver(Livro livro) {
	    livro.setStatus(Livro.Status.DISPONIVEL);
	    System.out.println("Livro " + livro + " devolvido.");
	    storage.atualizarStatus(livro);
	}
}
