package br.com.argonavis.javaee7.cdi.cdi;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;

import br.com.argonavis.javaee7.cdi.Livro;
import br.com.argonavis.javaee7.cdi.cdi.qualifier.LocalQualifier;
import br.com.argonavis.javaee7.cdi.di.BibliotecaStorage;

@LocalQualifier
@Alternative
@Priority(5)
public class LocalStorage implements BibliotecaStorage {

	public void atualizarStatus(Livro livro) {
		System.out.println("Atualizando base local");
	}

}
