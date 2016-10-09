package br.com.argonavis.javaee7.cdi.di;

import br.com.argonavis.javaee7.cdi.Livro;

public class BibliotecaNoSql implements BibliotecaStorage {
	public void atualizarStatus(Livro livro) {
		System.out.println("Atualizando banco NoSQL");
	}
}
