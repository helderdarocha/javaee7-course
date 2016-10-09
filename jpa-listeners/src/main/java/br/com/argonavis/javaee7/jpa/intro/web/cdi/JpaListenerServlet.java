package br.com.argonavis.javaee7.jpa.intro.web.cdi;

import java.io.IOException;
import java.io.Writer;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

@WebServlet("/JpaListenerServlet")
public class JpaListenerServlet extends HttpServlet {

	@Inject
	LivroDAOManagedBean bean;

	@Inject
	UserTransaction ut;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			Writer out = response.getWriter();
			
			out.write("Veja também os logs do servidor!");

			// state - new
			Livro livro = new Livro();
			livro.setPaginas(100);
			livro.setTitulo("Primeiro Livro");
			out.write("<p>Livro criado: ID " + livro.getId() + ": " + livro.getTitulo() + "</p>");
			System.out.println("Livro criado: ID " + livro.getId() + ": " + livro.getTitulo() + "");

			// persist; state - persistent
			ut.begin(); // se a transação não for criada aqui, ela será criada no bean, caso contrário será propagada
			Livro primeiro = bean.insert(livro); // O DAO está usando merge(); na prática, tanto faz - gera SQL INSERT se ID for null; é mais versátil porém é menos eficiente
			ut.commit();

			// state transient
			out.write("<p>Livro inserido: ID " + primeiro.getId() + ": " + primeiro.getTitulo() + "</p>");
			System.out.println("Livro inserido: ID " + primeiro.getId() + ": " + primeiro.getTitulo() + "");

			// load; state - persistent
			ut.begin();
			Livro existente = bean.findByID(primeiro.getId()); // poderia também usar um query, mas não lançaria o evento Load

			// update; state persistent
			existente.setTitulo("Primeiro livro, segunda edição");
			bean.update(existente); // update usa merge() e gera um SQL UPDATE (a diferenca é apenas o ID que não é null)
			ut.commit();

			// state transient
			out.write("<p>Livro alterado: ID " + existente.getId() + ": " + existente.getTitulo() + "</p>");
			System.out.println("Livro alterado: ID " + existente.getId() + ": " + existente.getTitulo() + "");

			// remove
			ut.begin();
			Livro dead = bean.findByID(existente.getId());
			bean.delete(dead); // o objeto continua vivo e pode ser usado, mas não existe mais registro!
			ut.commit();

			// state - removed
			out.write("<p>Livro removido: ID" + dead.getId() + ": " + dead.getTitulo() + "</p>");
			System.out.println("Livro removido: ID " + dead.getId() + ": " + dead.getTitulo() + "");
			
			// Neste ponto a instância poderá ser reinserida no banco usando persist()
			// (nosso DAO nao oferece essa opção!)
			// Neste caso merge() não pode ser usado (causa uma exceção)

		} catch (Exception e) {
			try {
				ut.rollback();
			} catch (Exception e1) {
				throw new ServletException(e1);
			}
			throw new ServletException(e);
		}

	}

}
