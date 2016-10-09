package br.com.argonavis.javaee7.cdi.cdi;

import javax.enterprise.inject.Any;
import javax.inject.Inject;

import br.com.argonavis.javaee7.cdi.Biblioteca;
import br.com.argonavis.javaee7.cdi.Livro;
import br.com.argonavis.javaee7.cdi.cdi.qualifier.NetworkQualifier;

public class BibliotecaImpl implements Biblioteca {
	@Inject 
	@Any
	private BibliotecaStorage storage;

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
