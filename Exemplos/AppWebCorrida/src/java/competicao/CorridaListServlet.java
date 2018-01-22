/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competicao;

import java.io.IOException;
import java.io.Writer;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

@WebServlet("/CorridaListServlet")
public class CorridaListServlet extends HttpServlet {

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

			Corrida corrida = em.find(Corrida.class, 4L); // só existe um objeto
			ut.commit();
			if (corrida == null) {
				out.write("<h1>Rode primeiro o CorridaCreateServlet</h1>");
			} else {

				out.write("<h1>Corrida: " + corrida.getNome() + "</h1>");

				for (Etapa etapa : corrida.getEtapas()) {
					out.write("<h2>Etapa: " + etapa.getNome() + "</h2>");
					out.write("<ul>");
					out.write("<li>De " + etapa.getOrigem().getNome() + "</li>");
					out.write("<li>Para " + etapa.getDestino().getNome() + "</li>");
					out.write("</ul>");

					out.write("<h3>Participantes</h3>");
					out.write("<table border>");
					out.write("<tr><td>Nome</td><td>Valor pago/devido</td><td>Status</td></tr>");
					for (Participante participante : etapa.getParticipantes()) {
						out.write("<tr>");
						out.write("<td>" + participante.getNome() + "</td>");
						out.write("<td>" + participante.getIngresso().getValor() + "</td>");
						out.write("<td>" + participante.getIngresso().getStatus() + "</td>");
						out.write("</tr>");
					}
					out.write("</table>");
				}
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
