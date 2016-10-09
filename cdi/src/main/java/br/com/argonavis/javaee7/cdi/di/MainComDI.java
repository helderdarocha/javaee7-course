package br.com.argonavis.javaee7.cdi.di;

import br.com.argonavis.javaee7.cdi.Livro;

public class MainComDI {

	public static void main(String[] args) {
		
		// Criação de dependências
		BibliotecaStorage jdbc = new BibliotecaJdbc();
		BibliotecaStorage nosql = new BibliotecaNoSql();
		BibliotecaStorage test = new BibliotecaTeste();
		BibliotecaStorage remota = new BibliotecaRemote();
		
		// Não pude usar a interface porque preciso chamar método DI da implementação
		BibliotecaImpl biblioteca = new BibliotecaImpl();
		
		// injeção de uma dependência
		biblioteca.setBibliotecaStorage(jdbc);

        Livro livro = new Livro("The Selfish Gene", new String[] {"Richard Dawkins"});
        biblioteca.emprestar(livro);
	}

}
