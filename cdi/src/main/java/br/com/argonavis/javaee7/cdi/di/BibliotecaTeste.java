package br.com.argonavis.javaee7.cdi.di;

import br.com.argonavis.javaee7.cdi.Livro;

public class BibliotecaTeste implements BibliotecaStorage {
	public void atualizarStatus(Livro livro) {
		System.out.println("Atualizando na Mock database");
	}
}