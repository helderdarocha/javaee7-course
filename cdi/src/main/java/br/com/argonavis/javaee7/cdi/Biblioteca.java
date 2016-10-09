package br.com.argonavis.javaee7.cdi;

public interface Biblioteca {
	void emprestar(Livro livro);
	void devolver(Livro livro);
}
