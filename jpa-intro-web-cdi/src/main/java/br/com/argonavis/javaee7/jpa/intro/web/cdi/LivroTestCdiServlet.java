package br.com.argonavis.javaee7.jpa.intro.web.cdi;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LivroTestCdiServlet")
public class LivroTestCdiServlet extends HttpServlet {

	@Inject
	LivroDAOManagedBean bean;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Writer out = response.getWriter();
		
		Livro outro = new Livro();
		outro.setPaginas(250);
		outro.setTitulo("Outro livro");
		Livro inserted = bean.insert(outro);
		
		out.write("<p>Inserted: " + inserted.getId() + "</p>");
		
		Livro dead = bean.findByIDCriteria(52L);
		dead.setTitulo("Ex-book-X");
		bean.update(dead);

		List<Livro> livros = bean.findAll();
		for (Livro livro : livros) {
			out.write("<p>" + livro.getId() + ": " + livro.getTitulo() + " " + livro.getPaginas() + " paginas</p>");
		}

	}

}
