package br.com.argonavis.javaee7.jpa.intro.ejb.web;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.argonavis.javaee7.jpa.intro.ejb.Livro;
import br.com.argonavis.javaee7.jpa.intro.ejb.LivroDAOSessionBean;

@WebServlet("/LivroTestEjbServlet")
public class LivroTestEjbServlet extends HttpServlet {

	@PersistenceContext(unitName = "tutorial-jpa-ejb")
	EntityManager em;

	@EJB
	LivroDAOSessionBean bean;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Writer out = response.getWriter();
		
		Livro outro = new Livro();
		outro.setPaginas(250);
		outro.setTitulo("Outro livro");
		Livro inserted = bean.insert(outro);
		
		out.write("<p>Inserted: " + inserted.getId() + "</p>");
		
		Livro dead = bean.findByID(52L);
		dead.setTitulo("Ex-book");
		bean.update(dead);

		List<Livro> livros = bean.findAll();
		for (Livro livro : livros) {
			out.write("<p>" + livro.getId() + ": " + livro.getTitulo() + " " + livro.getPaginas() + " paginas</p>");
		}

	}

}
