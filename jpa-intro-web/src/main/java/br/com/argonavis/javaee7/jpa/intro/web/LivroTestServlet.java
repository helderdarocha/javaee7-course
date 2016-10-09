package br.com.argonavis.javaee7.jpa.intro.web;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@WebServlet("/LivroTestServlet")
public class LivroTestServlet extends HttpServlet {
	
	@PersistenceContext(unitName="tutorial-jpa")
	EntityManager em;
	
	@Resource
	private UserTransaction ut; // Transação JTA

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Writer out = response.getWriter(); 

		try {
			ut.begin(); // somente para demonstrar uso - bloco abaixo (leitura) não precisa estar em contexto transacional
		
			List<Livro> livros = em.createQuery("select livro from Livro livro").getResultList();
			for(Livro livro : livros) {
				out.write("<p>"+livro.getTitulo() + " " + livro.getPaginas() + " paginas</p>");
			}
			
			ut.commit();
			
		} catch (SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException e) {
			try {
				ut.rollback();
			} catch (SystemException e1) {
				throw new ServletException(e);
			}
			throw new ServletException(e);
		}

	}

}
