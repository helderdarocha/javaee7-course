package br.com.argonavis.javaee7.cdi.di;

import br.com.argonavis.javaee7.cdi.Biblioteca;
import br.com.argonavis.javaee7.cdi.Livro;

public class BibliotecaImpl implements Biblioteca {
	private BibliotecaStorage storage;

	/**
	 * Este método público permite a injeção da dependência
	 */
	public void setBibliotecaStorage(BibliotecaStorage ref) {
	    this.storage = ref;
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
