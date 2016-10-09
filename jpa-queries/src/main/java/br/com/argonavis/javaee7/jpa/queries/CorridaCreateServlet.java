package br.com.argonavis.javaee7.jpa.queries;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.HashSet;
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

@WebServlet("/CorridaCreateServlet")
public class CorridaCreateServlet extends HttpServlet {

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
		
		//return em.merge(etapa); // objeto devolvido é o objeto persistente - não precisa haver cascade no Corrida
		return etapa; // objeto devolvido é transiente - só funciona se relacionamento de Corrida for cascade PERSIST/MERGE! 
	}
	
	private Participante criarParticipante(String nome) {
		Participante p = new Participante();
		p.setNome(nome);
		return em.merge(p); // objeto devolvido é persistente
	}
	
	private Ingresso criarIngresso(BigDecimal preco, StatusIngresso status) {
		Ingresso ingresso = new Ingresso();
		ingresso.setValor(preco);
		ingresso.setStatus(status);
		return ingresso; // objeto devolvido aqui NÃO é persistente!
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Writer out = response.getWriter();
		response.setContentType("text/html");
		try {
			ut.begin();

			Etapa primeira = criarEtapa("Primeira", "Rio de Janeiro", "Paris");
			Etapa segunda  = criarEtapa("Segunda", "Berlin", "Moscow");
			Etapa terceira = criarEtapa("Terceira", "Tokyo", "Los Angeles");
			
			Corrida corrida = new Corrida();
			corrida.setNome("Tour du Monde 2016");
			corrida = em.merge(corrida);
			
			Set<Etapa> etapas = new HashSet<>();
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
			
			// extras para o query
			Participante darwin   = criarParticipante("Charles Darwin"); // sem ingresso e sem etapa
			Participante turing   = criarParticipante("Alan Turing"); // sem ingresso e sem etapa
			Participante rosalind = criarParticipante("Rosalind Franklin"); // sem etapa
			Participante jane     = criarParticipante("Jane Goodall"); // sem etapa
			
			// criarIngresso devolve objetos novos não persistentes - quem vai persisti-los será o cascade!

            // Como o Participante é persistente, e propaga a persistencia via cascade para
            // o Ingresso, o código funciona corretamente e não provoca exceção
			einstein.setIngresso(criarIngresso(BigDecimal.valueOf(5000), StatusIngresso.PAGO)); 
			hipacia.setIngresso(criarIngresso(BigDecimal.valueOf(2500), StatusIngresso.PAGO));
			curie.setIngresso(criarIngresso(BigDecimal.valueOf(4000), StatusIngresso.PAGO));
			verne.setIngresso(criarIngresso(BigDecimal.valueOf(7500), StatusIngresso.NAO_PAGO));
			leonardo.setIngresso(criarIngresso(BigDecimal.valueOf(5000), StatusIngresso.PAGO));
			polo.setIngresso(criarIngresso(BigDecimal.valueOf(10000), StatusIngresso.NAO_PAGO));
			
			// extras para o query
			rosalind.setIngresso(criarIngresso(BigDecimal.valueOf(6000), StatusIngresso.PAGO));
			jane.setIngresso(criarIngresso(BigDecimal.valueOf(7000), StatusIngresso.PAGO));
			
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
			
			//listarDadosMemoria(out, corrida);

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
	
	private void listarDadosMemoria(Writer out, Corrida corrida) throws IOException {
		out.write("<h1>Corrida: " + corrida.getNome() + "</h1>");
		
		for(Etapa etapa : corrida.getEtapas()) {
			out.write("<h2>Etapa: " + etapa.getNome() + "</h2>");
			out.write("<ul>");
			//out.write("<li>De "  +etapa.getOrigem().getNome()+ "</li>");
			//out.write("<li>Para "+etapa.getDestino().getNome()+ "</li>");
			out.write("</ul>");
			
			out.write("<h3>Participantes</h3>");
			out.write("<table border>");
			out.write("<tr><td>Nome</td><td>Valor pago/devido</td><td>Status</td></tr>");
			/*for(Participante participante : etapa.getParticipantes()) {
				out.write("<tr>");
				out.write("<td>"+participante.getNome()+"</td>");
				out.write("<td>"+participante.getIngresso().getValor()+"</td>");
				out.write("<td>"+participante.getIngresso().getStatus()+"</td>");
				out.write("</tr>");
			}*/
			out.write("</table>");
			
		}

	}

}
