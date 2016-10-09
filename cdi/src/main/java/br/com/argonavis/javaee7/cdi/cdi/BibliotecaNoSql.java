package br.com.argonavis.javaee7.cdi.cdi;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;

import br.com.argonavis.javaee7.cdi.Livro;
import br.com.argonavis.javaee7.cdi.cdi.qualifier.LocalQualifier;

@Alternative
@LocalQualifier
@Priority(10)
public class BibliotecaNoSql implements BibliotecaStorage {
	public void atualizarStatus(Livro livro) {
		System.out.println("Atualizando banco NoSQL");
	}
}
