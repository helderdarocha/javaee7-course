package br.com.argonavis.javaee7.jpa.queries;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.EntityType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

@WebServlet("/CorridaCriteriaServlet")
public class CorridaCriteriaServlet extends HttpServlet {

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
				// EXERCICIO: escolha 3 queries abaixo e implemente usando Criteria nos métodos correspondentes
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
	
	// EXERCICIOS - estes métodos são chamados no servlet
	
	private void query1(Writer out) throws IOException {
		out.write("<h4>1. Todos os participantes</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Participante> query = cb.createQuery(Participante.class);
		Root<Participante> root = query.from(Participante.class);
		query.select(root);
		
		TypedQuery<Participante> q = em.createQuery(query);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+"</li>");
		}
		out.write("</ul>");
	}
	
	private void query2(Writer out) throws IOException {
		out.write("<h4>2. Todos os participantes que não têm ingresso reservado</h4>");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Participante> query = cb.createQuery(Participante.class);
		Root<Participante> root = query.from(Participante.class);
		query.where(cb.isNull(root.get("ingresso")));
		query.select(root);
		
		TypedQuery<Participante> q = em.createQuery(query);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+"</li>");
		}
		out.write("</ul>");
	}
	
	private void query3(Writer out) throws IOException {
		out.write("<h4>3. Todos os participantes que não agendaram etapas</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Participante> query = cb.createQuery(Participante.class);
		Root<Participante> root = query.from(Participante.class);
		query.where(cb.isEmpty(root.get("etapas")));
		query.select(root);
		
		TypedQuery<Participante> q = em.createQuery(query);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+"</li>");
		}
		out.write("</ul>");
	}
	
	private void query4(Writer out) throws IOException {
		out.write("<h4>4. Todos os participantes que reservaram ingresso mas não pagaram</h4>");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Participante> query = cb.createQuery(Participante.class);
		Root<Participante> root = query.from(Participante.class);
		query.where(cb.equal(root.get("ingresso").get("status"), StatusIngresso.NAO_PAGO));
		query.select(root);
		
		TypedQuery<Participante> q = em.createQuery(query);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+"</li>");
		}
		out.write("</ul>");
	}
	
	private void query5(Writer out) throws IOException {
		out.write("<h4>5. Todos os participantes que pagaram entre 3000 e 8000</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Participante> query = cb.createQuery(Participante.class);
		Root<Participante> root = query.from(Participante.class);
		
		Predicate between = cb.between(root.get("ingresso").get("valor"), 
				                       cb.parameter(BigDecimal.class, "min"),  // declarando parametros
				                       cb.parameter(BigDecimal.class, "max"));
		Predicate equal = cb.equal(root.get("ingresso").get("status"), 
				                    StatusIngresso.PAGO);   
		Predicate where = cb.and(between, equal);
		
		query.where(where);
		query.select(root);
		
		TypedQuery<Participante> q = em.createQuery(query);

		q.setParameter("min", 3000); // passagem dos valores dos parametros
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

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Participante> query = cb.createQuery(Participante.class);
		Root<Participante> root = query.from(Participante.class);
		Predicate where = cb.like(root.get("nome"), "M%");
		query.where(where);
		query.select(root);
		
		TypedQuery<Participante> q = em.createQuery(query);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+"</li>");
		}
		out.write("</ul>");
	}
	
	private void query7(Writer out) throws IOException {
		out.write("<h4>7. Todas as etapas que passam por Moscou ou Rio de Janeiro</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Etapa> query = cb.createQuery(Etapa.class);
		Root<Etapa> root = query.from(Etapa.class);
		
		Path<String> orig = root.get("origem").get("nome");
		Path<String> dest = root.get("destino").get("nome");
		
		Predicate oin = orig.in(cb.parameter(String.class, "loc1"), 
				                 cb.parameter(String.class, "loc2"));
		Predicate din = dest.in(cb.parameter(String.class, "loc1"), 
                                 cb.parameter(String.class, "loc2"));
		Predicate where = cb.or(oin, din);   
		
		query.where(where);
		query.select(root);
		
		TypedQuery<Etapa> q = em.createQuery(query);

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

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> query = cb.createQuery(BigDecimal.class); // query retorna BigDecimal
		Root<Ingresso> root = query.from(Ingresso.class); // from Ingresso i
		
		Predicate where = cb.equal(root.get("status"), 
				                   cb.parameter(StatusIngresso.class, "status"));
		query.where(where);
		Expression<BigDecimal> total = cb.sum(root.get("valor"));
		query.select(total);
		
		TypedQuery<BigDecimal> q = em.createQuery(query);
		q.setParameter("status", StatusIngresso.PAGO);
		
		BigDecimal r = q.getSingleResult();
		out.write("<p>$"+r+"</p>");
	}
	
	private void query9(Writer out) throws IOException {
		out.write("<h4>9. Valor total devido</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> query = cb.createQuery(BigDecimal.class); // query retorna BigDecimal
		Root<Ingresso> root = query.from(Ingresso.class); // from Ingresso i
		
		Predicate where = cb.equal(root.get("status"), 
				                   cb.parameter(StatusIngresso.class, "status"));
		query.where(where);
		Expression<BigDecimal> total = cb.sum(root.get("valor"));
		query.select(total);
		
		TypedQuery<BigDecimal> q = em.createQuery(query);
		q.setParameter("status", StatusIngresso.NAO_PAGO);
		
		BigDecimal r = q.getSingleResult();
		out.write("<p>$"+r+"</p>");
	}
	
	private void query10(Writer out) throws IOException {
		out.write("<h4>10. Média do valor cobrado</h4>");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Double> query = cb.createQuery(Double.class); // avg retorna Double
		Root<Ingresso> root = query.from(Ingresso.class); // from Ingresso i
		Expression<Double> media = cb.avg(root.get("valor"));
		query.select(media);
		
		TypedQuery<Double> q = em.createQuery(query);
		
		Double r = q.getSingleResult();
		out.write("<p>$"+r+"</p>");
	}
	
	private void query11(Writer out) throws IOException {
		out.write("<h4>11. Participante com o nome mais longo, e comprimento do nome</h4>");

		// Query principal
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = cb.createQuery(Object[].class); // retorna dois resultados!
		Root<Participante> root = query.from(Participante.class);
		
		// Subquery
		Subquery<Integer> subQuery = query.subquery(Integer.class); // subquery retorna inteiro
		Root<Participante> sqRoot = subQuery.from(Participante.class);
		Expression<Integer> len = cb.length(sqRoot.get("nome"));  // calcula o comprimento do nome
		Expression<Integer> max = cb.max(len);                    // calcula o maior comprimento
		subQuery.select(max);

		Predicate gt = cb.greaterThan(cb.length(root.get("nome")), subQuery); // greaterThanOrEqual não suporta subquery!
		Predicate eq = cb.equal(cb.length(root.get("nome")), subQuery); 
		Predicate where = cb.or(gt, eq);
		query.where(where);
		query.select(cb.array(root, cb.length(root.get("nome")))).distinct(true);
		
		TypedQuery<Object[]> q = em.createQuery(query);
		
		Object[] r = q.getSingleResult();
		Participante rp = (Participante)r[0];
		Integer ri      = (Integer)r[1];
		out.write("<p>"+rp.getNome()+" ("+ri+" caracteres)</p>");
	}
	
	private void query12(Writer out) throws IOException {
		out.write("<h4>12. Participante que pagou mais que a media</h4>");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Participante> query = cb.createQuery(Participante.class);
		Root<Participante> root = query.from(Participante.class);
		
		Predicate status = cb.equal(root.get("ingresso").get("status"), StatusIngresso.PAGO);
		
		Subquery<Double> subquery = query.subquery(Double.class);
		Root<Ingresso> sqr = subquery.from(Ingresso.class); 
		Expression<Double> media = cb.avg(sqr.get("valor"));
		subquery.select(media);
		
		Predicate valor = cb.greaterThan(root.get("ingresso").get("valor"), subquery);
		query.where(cb.and(status, valor));
		query.select(root);
		
		TypedQuery<Participante> q = em.createQuery(query);
		
		List<Participante> r = q.getResultList();
		out.write("<ul>");
		for(Participante p : r) {
			out.write("<li>"+p.getNome()+" pagou $"+p.getIngresso().getValor()+"</li>");
		}
		out.write("</ul>");
	}
	
	private void query13(Writer out) throws IOException {
		out.write("<h4>13. Lista de localidades (origens e destinos) que serão visitadas</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = cb.createQuery(Object[].class); // 
		Root<Etapa> root = query.from(Etapa.class); // from Etapa
		//query.select(cb.array(root.get("origem").get("nome"), root.get("destino").get("nome")));
		query.multiselect(root.get("origem").get("nome"), root.get("destino").get("nome")); // equivalente
		
        TypedQuery<Object[]> q = em.createQuery(query);
		
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
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Lugares> query = cb.createQuery(Lugares.class);
		Root<Participante> root = query.from(Participante.class); 
		Join<Participante, Etapa> etapa = root.join("etapas");
		
		query.where(cb.like(root.get("nome"),  "Albert%"));
		
		query.select(cb.construct(Lugares.class, etapa.get("origem").get("nome"), etapa.get("destino").get("nome")));
		
		TypedQuery<Lugares> q = em.createQuery(query);
		
		List<Lugares> r = q.getResultList();
		out.write("<ul>");
		for(Lugares lugares : r) {
			out.write("<li>origem: "+lugares.getOrigem()+", destino: "+lugares.getDestino()+"</li>");
		}
		out.write("</ul>");
	}

}
