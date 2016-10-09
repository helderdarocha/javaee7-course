package br.com.argonavis.javaee7.cdi.cdi;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;

import br.com.argonavis.javaee7.cdi.Livro;
import br.com.argonavis.javaee7.cdi.cdi.qualifier.NetworkQualifier;

@NetworkQualifier
public class BibliotecaRemote implements BibliotecaStorage {
	public void atualizarStatus(Livro livro) {
		System.out.println("Atualizando banco remoto");
	}
}
