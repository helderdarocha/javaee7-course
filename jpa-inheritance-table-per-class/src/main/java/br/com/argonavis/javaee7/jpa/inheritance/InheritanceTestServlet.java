package br.com.argonavis.javaee7.jpa.inheritance;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import br.com.argonavis.javaee7.jpa.inheritance.tableperclass.Veiculo;

@WebServlet("/InheritanceTestServlet")
public class InheritanceTestServlet extends HttpServlet {

	@PersistenceContext
	EntityManager em;

	@Resource
	UserTransaction ut;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Writer out = response.getWriter();
		response.setContentType("text/html");
		try {
			ut.begin();
			
			TypedQuery<Veiculo> query = em.createQuery("select v from Veiculo v", Veiculo.class);
			List<Veiculo> veiculos = query.getResultList();
			
			out.write("<table border><tr><td>ID</td><td>Capacidade</td><td>Veiculo</td></tr>");
			for(Veiculo v: veiculos) {
				out.write("<tr><td>"+v.getId()+"</td><td>"+v.getCapacidade()+"</td><td>"+v+"</td></tr>");
			}
			out.write("</table>");

			ut.commit();

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
