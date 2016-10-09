package br.com.argonavis.javaee7.cdi.di;

import br.com.argonavis.javaee7.cdi.Biblioteca;
import br.com.argonavis.javaee7.cdi.Livro;

public class MainSemDI {

	public static void main(String[] args) {
		Biblioteca biblioteca = new BibliotecaImplSemDI();

        Livro livro = new Livro("The Selfish Gene", new String[] {"Richard Dawkins"});
        biblioteca.emprestar(livro);
	}

}
