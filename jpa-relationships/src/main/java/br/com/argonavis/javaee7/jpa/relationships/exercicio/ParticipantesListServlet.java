package br.com.argonavis.javaee7.jpa.relationships.exercicio;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

@WebServlet("/ParticipantesListServlet")
public class ParticipantesListServlet extends HttpServlet {

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

			List<Participante> participantes = em.createQuery("select p from Participante p", Participante.class)
					.getResultList();

			ut.commit();
			// Pode haver LazyInitializationException ao recuperar as coleções
			// (depende do provedor)

			if (participantes == null || participantes.isEmpty()) {
				out.write("<h1>Rode primeiro o CorridaCreateServlet</h1>");
			} else {
				out.write("<h1>Participantes</h1>");
				out.write("<table border>");
				out.write("<tr><td>Nome</td><td>Etapas reservadas</td></tr>");
				for (Participante participante : participantes) {
					out.write("<tr>");
					out.write("<td>" + participante.getNome() + "</td>");

					out.write("<td>");
					Set<Etapa> etapas = participante.getEtapas();
					StringBuilder lista = new StringBuilder();
					for (Etapa etapa : etapas) {
						lista.append(etapa.getNome()).append(", ");
					}
					out.write(lista.toString().substring(0, lista.length() - 2) + "</td>");
					out.write("</tr>");
				}
				out.write("</table>");
			}

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
