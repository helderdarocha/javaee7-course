package br.com.argonavis.javaee7.jpa.queries;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

@WebServlet("/CorridaJpqlServlet")
public class CorridaJpqlServlet extends HttpServlet {

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

			Corrida corrida = em.find(Corrida.class, 1L); // só existe um objeto
			
			if (corrida == null) {
				out.write("<h1>Rode primeiro o CorridaCreateServlet</h1>");
			} else {
				
				// EXERCICIO: escolha 6 queries abaixo e implemente usando JPQL nos métodos correspondentes
				// (alguns estão parcialmente ou totalmente resolvidos)
				query1(out);
				query2(out);
				query3(out);
				query4(out);
				query5(out);
				query6(out);
				query7(out);
				query8(out);
				query9(out);
				query10(out);
				query11(out);
				query12(out);
				query13(out);
				query14(out);

			}
			
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

	// EXERCICIOS (estes métodos são chamados no servlet)
	
	private void query1(Writer out) throws IOException {
		out.write("<h4>1. Todos os participantes</h4>");
		String query = "select p from Participante p";
		TypedQuery<Participante> q = em.createQuery(query, Participante.class);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+"</li>");
		}
		out.write("</ul>");
	}
	private void query2(Writer out) throws IOException {
		out.write("<h4>2. Todos os participantes que não têm ingresso reservado</h4>");
		String query = "select p from Participante p where p.ingresso is null";
		TypedQuery<Participante> q = em.createQuery(query, Participante.class);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+"</li>");
		}
		out.write("</ul>");
	}
	private void query3(Writer out) throws IOException {
		out.write("<h4>3. Todos os participantes que não agendaram etapas</h4>");
		String query = "select p from Participante p where p.etapas is empty";
		TypedQuery<Participante> q = em.createQuery(query, Participante.class);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+"</li>");
		}
		out.write("</ul>");
	}
	private void query4(Writer out) throws IOException {
		out.write("<h4>4. Todos os participantes que reservaram ingresso mas não pagaram</h4>");
		String query = "select p from Participante p where p.ingresso.status = :status";
		TypedQuery<Participante> q = em.createQuery(query, Participante.class);
		q.setParameter("status", StatusIngresso.NAO_PAGO);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+"</li>");
		}
		out.write("</ul>");
	}
	private void query5(Writer out) throws IOException {
		out.write("<h4>5. Todos os participantes que pagaram entre 3000 e 8000</h4>");
		String query = "select p from Participante p where p.ingresso.valor between :min and :max and p.ingresso.status = :status";
		TypedQuery<Participante> q = em.createQuery(query, Participante.class);
		q.setParameter("status", StatusIngresso.PAGO);
		q.setParameter("min", 3000);
		q.setParameter("max", 8000);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+" pagou "+p.getIngresso().getValor()+"</li>");
		}
		out.write("</ul>");
	}
	private void query6(Writer out) throws IOException {
		out.write("<h4>6. Todos os participantes cujo nome começa com 'M'</h4>");
		String query = "select p from Participante p where p.nome like 'M%'";
		TypedQuery<Participante> q = em.createQuery(query, Participante.class);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+"</li>");
		}
		out.write("</ul>");
	}
	private void query7(Writer out) throws IOException {
		out.write("<h4>7. Todas as etapas que passam por Moscou ou Rio de Janeiro</h4>");
		String query = "select e from Etapa e where e.origem.nome in(:loc1, :loc2) or e.destino.nome in(:loc1, :loc2) ";
		TypedQuery<Etapa> q = em.createQuery(query, Etapa.class);
		q.setParameter("loc1", "Moscow");
		q.setParameter("loc2", "Rio de Janeiro");
		
		List<Etapa> r = q.getResultList();
		out.write("<ul>");
		for(Etapa e : r) {
			out.write("<li>"+e.getNome()+" (origem: "+e.getOrigem().getNome()+", destino: "+e.getDestino().getNome()+")</li>");
		}
		out.write("</ul>");
	}
	private void query8(Writer out) throws IOException {
		out.write("<h4>8. Valor total já pago</h4>");
		String query = "select sum(i.valor) from Ingresso i where i.status = :status";
		TypedQuery<BigDecimal> q = em.createQuery(query, BigDecimal.class);
		q.setParameter("status", StatusIngresso.PAGO);
		
		BigDecimal r = q.getSingleResult();
		out.write("<p>$"+r+"</p>");
	}
	private void query9(Writer out) throws IOException {
		out.write("<h4>9. Valor total devido</h4>");
		String query = "select sum(i.valor) from Ingresso i where i.status = :status";
		TypedQuery<BigDecimal> q = em.createQuery(query, BigDecimal.class);
		q.setParameter("status", StatusIngresso.NAO_PAGO);
		
		BigDecimal r = q.getSingleResult();
		out.write("<p>$"+r+"</p>");
	}
	private void query10(Writer out) throws IOException {
		out.write("<h4>10. Média do valor cobrado</h4>");
		String query = "select avg(i.valor) from Ingresso i";
		TypedQuery<Double> q = em.createQuery(query, Double.class);
		
		Double r = q.getSingleResult();
		out.write("<p>$"+r+"</p>");
	}
	private void query11(Writer out) throws IOException {
		out.write("<h4>11. Participante com o nome mais longo, e comprimento do nome</h4>");
		String query = "select distinct p, length(p.nome) from Participante p where length(p.nome) >= (select max(length(p.nome)) from Participante p)";
		TypedQuery<Object[]> q = em.createQuery(query, Object[].class);
		
		Object[] r = q.getSingleResult();
		Participante rp = (Participante)r[0];
		Integer ri      = (Integer)r[1];
		out.write("<p>"+rp.getNome()+" ("+ri+" caracteres)</p>");
	}
	private void query12(Writer out) throws IOException {
		out.write("<h4>12. Participante que pagou mais que a media</h4>");
		String query = "select p from Participante p where p.ingresso.status=:status and p.ingresso.valor > (select avg(i.valor) from Ingresso i)";
		TypedQuery<Participante> q = em.createQuery(query, Participante.class);
		q.setParameter("status", StatusIngresso.PAGO);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+" pagou $"+p.getIngresso().getValor()+"</li>");
		}
		out.write("</ul>");
	}
	private void query13(Writer out) throws IOException {
		out.write("<h4>13. Lista de localidades (origem e destino) que serão visitadas</h4>");
		// Em EclipseLink (proprietario) pode ser: "select e.origem.nome from Etapa e union select e.destino.nome from Etapa e"
		// JPA 2.1 não suporta UNION, portanto os valores são devolvidos separados em um array
		String query = "select e.origem.nome, e.destino.nome from Etapa e";
        TypedQuery<Object[]> q = em.createQuery(query, Object[].class);
		
		List<Object[]> r = q.getResultList();
		out.write("<ul>");
		for(Object[] cidades : r) {
			for(Object cidade : cidades) { // 
			   out.write("<li>"+cidade+"</li>");
		    }
		}
		out.write("</ul>");
	}
	
	private void query14(Writer out) throws IOException {
		out.write("<h4>14. Lista de origens e destinos por onde passará um participante chamado Albert</h4>");
		String query = "select new br.com.argonavis.javaee7.jpa.queries.Lugares(e.origem.nome, e.destino.nome) "
				       + "from Participante p "
				       + "join fetch p.etapas e "
				       + "where p.nome like 'Albert%'"
				       ;
		TypedQuery<br.com.argonavis.javaee7.jpa.queries.Lugares> q = em.createQuery(query, br.com.argonavis.javaee7.jpa.queries.Lugares.class);
		
		List<Lugares> r = q.getResultList();
		out.write("<ul>");
		for(Lugares lugares : r) {
			out.write("<li>origem: "+lugares.getOrigem()+", destino: "+lugares.getDestino()+"</li>");
		}
		out.write("</ul>");
	}

}
