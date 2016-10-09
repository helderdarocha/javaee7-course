package br.com.argonavis.javaee7.cdi.cdi;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;

import br.com.argonavis.javaee7.cdi.Livro;

public class BibliotecaStorageFactory {
	
	@Produces @Alternative
	public BibliotecaStorage createStorage() {
		System.out.println("Usando produces para instanciar storage");
		return new BibliotecaStorage() {
			public void atualizarStatus(Livro livro) {
				System.out.println("Usando storage criado via Produces");
			}
			
		};
	}
}
