package br.com.argonavis.javaee7.cdi.cdi;

import javax.enterprise.inject.Default;

import br.com.argonavis.javaee7.cdi.Livro;
import br.com.argonavis.javaee7.cdi.cdi.qualifier.UnitTestQualifier;

//@Default
@UnitTestQualifier
public class BibliotecaTeste implements BibliotecaStorage {
	public void atualizarStatus(Livro livro) {
		System.out.println("Atualizando na Mock database");
	}
}