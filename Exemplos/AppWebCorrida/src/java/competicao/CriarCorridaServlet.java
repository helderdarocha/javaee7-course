/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competicao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author helderdarocha
 */
@WebServlet(name = "CriarCorridaServlet", urlPatterns = {"/CriarCorridaServlet"})
public class CriarCorridaServlet extends HttpServlet {

    @PersistenceContext
	EntityManager em;

	@Resource
	UserTransaction ut;
	
	private Etapa criarEtapa(String nome, String origem, String destino) {
		Etapa etapa = new Etapa();
		etapa.setNome(nome);

		Localidade de = new Localidade();
		de.setNome(origem);
		etapa.setOrigem(de);
		
		Localidade para = new Localidade();
		para.setNome(destino);
		etapa.setDestino(para);
		
		return em.merge(etapa); // objeto devolvido é o objeto persistente - não precisa haver cascade no Corrida
		//return etapa; // objeto devolvido é transiente - só funciona se relacionamento de Corrida for cascade PERSIST/MERGE! 
	}
	
	private Participante criarParticipante(String nome) {
		Participante p = new Participante();
		p.setNome(nome);
		return em.merge(p); // objeto devolvido é persistente
	}
	
	private Ingresso criarIngresso(double preco, StatusIngresso status) {
		Ingresso ingresso = new Ingresso();
		ingresso.setValor(preco);
		ingresso.setStatus(status);
		return ingresso; // objeto devolvido aqui NÃO é persistente!
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		try {
			ut.begin();

			Etapa primeira = criarEtapa("Primeira", "Rio de Janeiro", "Paris");
			Etapa segunda  = criarEtapa("Segunda", "Berlin", "Moscow");
			Etapa terceira = criarEtapa("Terceira", "Tokyo", "Los Angeles");
			
			Corrida corrida = new Corrida();
			corrida.setNome("Tour du Monde 2016");
			corrida = em.merge(corrida);
			
			List<Etapa> etapas = new ArrayList<>();
			etapas.add(primeira);
			etapas.add(segunda);
			etapas.add(terceira);
			corrida.setEtapas(etapas); // não atualiza a tabela etapas!
			
			// É preciso incluir etapa.setCorrida(corrida) para cada etapa!
			primeira.setCorrida(corrida);
			segunda.setCorrida(corrida);
			terceira.setCorrida(corrida);
			
			// Ideal é criar um método add dentro de uma das classes, que chame os dois lados da operação!			
			
			
			// objetos devolvidos são persistentes (merge)
			Participante einstein = criarParticipante("Albert Einstein");
			Participante hipacia  = criarParticipante("Hipácia de Alexandria");
			Participante curie    = criarParticipante("Marie Curie");
			Participante verne    = criarParticipante("Julio Verne");
			Participante leonardo = criarParticipante("Leonardo da Vinci");
			Participante polo     = criarParticipante("Marco Polo");
			
			// criarIngresso devolve objetos novos não persistentes - quem vai persisti-los será o cascade!

            // Como o Participante é persistente, e propaga a persistencia via cascade para
            // o Ingresso, o código funciona corretamente e não provoca exceção
			einstein.setIngresso(criarIngresso(5000, StatusIngresso.PAGO)); 
			hipacia.setIngresso(criarIngresso(2500, StatusIngresso.PAGO));
			curie.setIngresso(criarIngresso(4000, StatusIngresso.PAGO));
			verne.setIngresso(criarIngresso(7500, StatusIngresso.NAO_PAGO));
			leonardo.setIngresso(criarIngresso(5000, StatusIngresso.PAGO));
			polo.setIngresso(criarIngresso(10000, StatusIngresso.NAO_PAGO));
			
			primeira.addParticipante(einstein); 
			primeira.addParticipante(hipacia);
			primeira.addParticipante(verne);
			primeira.addParticipante(polo);
			
			segunda.addParticipante(einstein);
			segunda.addParticipante(verne);
			segunda.addParticipante(curie);
			
			terceira.addParticipante(leonardo);
			terceira.addParticipante(verne);
			terceira.addParticipante(curie);
			terceira.addParticipante(polo);
			

			ut.commit();
		} catch (Exception e) {
		//	try {
		//		ut.rollback();
		//	} catch (Exception e1) {
		//		throw new ServletException(e1);
		//	}
			throw new ServletException(e);
		}
	}
}
