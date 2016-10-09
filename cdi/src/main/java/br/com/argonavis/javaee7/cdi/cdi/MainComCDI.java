package br.com.argonavis.javaee7.cdi.cdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import br.com.argonavis.javaee7.cdi.Livro;

public class MainComCDI {

	public static void main(String[] args) {
		
		// Configuração do container (necessário para rodar standalone)
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		
		// Dependencia default será injetada automaticamente
		BibliotecaImpl biblioteca = 
				(BibliotecaImpl)container.instance().select(BibliotecaImpl.class).get();
		
        Livro livro = new Livro("The Selfish Gene", new String[] {"Richard Dawkins"});
        biblioteca.emprestar(livro);
        
        BibliotecaImpl2 biblioteca2 = 
				(BibliotecaImpl2)container.instance().select(BibliotecaImpl2.class).get();
		
        biblioteca2.emprestar(livro);
	}

}
