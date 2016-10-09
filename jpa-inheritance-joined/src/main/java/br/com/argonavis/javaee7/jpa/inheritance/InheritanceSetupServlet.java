package br.com.argonavis.javaee7.jpa.inheritance;

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

import br.com.argonavis.javaee7.jpa.inheritance.joined.Aviao;
import br.com.argonavis.javaee7.jpa.inheritance.joined.Caminhao;
import br.com.argonavis.javaee7.jpa.inheritance.joined.Onibus;
import br.com.argonavis.javaee7.jpa.inheritance.joined.Veiculo;


@WebServlet("/InheritanceSetupServlet")
public class InheritanceSetupServlet extends HttpServlet {

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

			Onibus microOnibus = new Onibus();
			microOnibus.setCapacidade(12);
			microOnibus.setPlaca("ABC-1234");
			microOnibus.setTipo("Transporte local");
			
			Onibus onibus      = new Onibus();
			onibus.setCapacidade(40);
			onibus.setPlaca("JPA-1234");
			onibus.setTipo("Viagem Interestadual");
			
			Aviao emb195       = new Aviao();
			emb195.setCapacidade(200);
			emb195.setModelo("Embraer EMB-195");
			emb195.setPrefixo("PP-EMB");
			
			Aviao caravan      = new Aviao();
			caravan.setCapacidade(9);
			caravan.setModelo("Cessna Caravan");
			caravan.setPrefixo("PT-JPA");
			
			Caminhao bau = new Caminhao();
			bau.setCargaMaxima(10000);
			bau.setCapacidade(2);
			bau.setPlaca("XYZ-9876");
			
			em.persist(microOnibus);
			em.persist(onibus);
			em.persist(emb195);
			em.persist(caravan);
			em.persist(bau);
			
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
