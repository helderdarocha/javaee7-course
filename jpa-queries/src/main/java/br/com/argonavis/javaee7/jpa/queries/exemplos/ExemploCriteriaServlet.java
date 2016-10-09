package br.com.argonavis.javaee7.jpa.queries.exemplos;

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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import br.com.argonavis.javaee7.jpa.queries.Corrida;
import br.com.argonavis.javaee7.jpa.queries.Etapa;
import br.com.argonavis.javaee7.jpa.queries.Ingresso;
import br.com.argonavis.javaee7.jpa.queries.Lugares;
import br.com.argonavis.javaee7.jpa.queries.Participante;
import br.com.argonavis.javaee7.jpa.queries.StatusIngresso;

@WebServlet("/ExemploCriteriaServlet")
public class ExemploCriteriaServlet extends HttpServlet {

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
				query15(out);
				query16(out);

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
	

	private void query1(Writer out) throws IOException {
		out.write("<h4>1)	Encontre todos os produtos que são chips e cuja margem de lucro é positiva</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = cb.createQuery(Produto.class); // select Produto
		Root<Produto> root = query.from(Produto.class); // from Produto
		
		Predicate igual = cb.equal(root.get("descricao"), "chip");
		Expression<Double> subtracao = cb.diff(root.get("preco"), root.get("custo"));
		Predicate maiorQue = cb.greaterThan(subtracao, 0.0);
		Predicate clausulaWhere = cb.and(igual, maiorQue);
		
		query.where(clausulaWhere);
		query.select(root);
		
		TypedQuery<Produto> q = em.createQuery(query);
		// ...
	}
	
	private void query2(Writer out) throws IOException {
		out.write("<h4>2)	Encontre todos os produtos cujo preço é pelo menos 1000 e no máximo 2000</h4>");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = cb.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);
		
		Predicate between = cb.between(root.get("preco"), 1000.0, 2000.0);
		
		query.where(between);
		query.select(root);
		
		TypedQuery<Produto> q = em.createQuery(query);
		
		// ...
	}
	
	private void query3(Writer out) throws IOException {
		out.write("<h4>3)	Encontre todos os produtos cujo fabricante é Sun ou Intel</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = cb.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);
		query.where(root.get("fabricante").in("Intel", "Sun"));
		query.select(root);
		
		TypedQuery<Produto> q = em.createQuery(query);
		
		//...
	}
	
	private void query4(Writer out) throws IOException {
		out.write("<h4>4) Encontre todos os produtos com IDs que começam com 12 e terminam em 3</h4>");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = cb.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);
		query.where(cb.like(root.get("id"), "12%3"));
		query.select(root);
		
		TypedQuery<Produto> q = em.createQuery(query);
		
		//...
	}
	
	private void query5(Writer out) throws IOException {
		out.write("<h4>5)	Encontre todos os produtos que têm descrições null</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = cb.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);
		query.where(cb.isNull(root.get("descricao")));
		query.select(root);
		
		TypedQuery<Produto> q = em.createQuery(query);
		
		//...
	}
	
	private void query6(Writer out) throws IOException {
		out.write("<h4>6)	Encontre todos os pedidos que não têm itens (coleção)</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> query = cb.createQuery(Pedido.class);
		Root<Pedido> root = query.from(Pedido.class);
		query.where(cb.isEmpty(root.get("itens")));
		query.select(root);
		
		TypedQuery<Pedido> q = em.createQuery(query);
		
		//...
	}
	
	private void query7(Writer out) throws IOException {
		out.write("<h4>7) Retorne os pedidos que contem um determinado item</h4>");
		
		Item item = new Item(); // item a ser testado
		// ...
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pedido> query = cb.createQuery(Pedido.class);
		Root<Pedido> root = query.from(Pedido.class);  
		Predicate isMember = cb.isMember(item, root.get("itens"));
		query.where(isMember);
		query.select(root);
		
		TypedQuery<Pedido> q = em.createQuery(query);
		
		//...
	}
	
	private void query8(Writer out) throws IOException {
		out.write("<h4>8)	Encontre produtos com preços entre 1000 e 2000 ou que tenham código 1001</h4>");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = cb.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);
		
		Predicate between = cb.between(root.get("preco"), 1000.0, 2000.0);
		Predicate igual   = cb.equal(root.get("codigo"), 1001);
		query.where(cb.and(between, igual));
		query.select(root);
		
		TypedQuery<Produto> q = em.createQuery(query);
		
		//...
	}
	
	private void query9(Writer out) throws IOException {
		out.write("<h4>9)	Selecione todos os clientes com pedidos que tenham total maior que 1000</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
		Root<Cliente> root = query.from(Cliente.class);
		
		Join<Cliente, Pedido> pedido = root.join("pedidos");
		
		query.where(cb.greaterThan(pedido.get("total"), 1000.0));
		query.select(root);
		
		TypedQuery<Cliente> q = em.createQuery(query);
	}
	
	private void query9_1(Writer out) throws IOException {
		out.write("<h4>9)	Selecione todos os clientes com pedidos que tenham total maior que 1000</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
		Root<Cliente> root  = query.from(Cliente.class);
		Path<Pedido> pedido = root.get("pedidos");

		query.where(cb.greaterThan(pedido.get("total"), 1000.0));
		query.select(root);
		
		TypedQuery<Cliente> q = em.createQuery(query);
	}
	
	private void query10(Writer out) throws IOException {
		out.write("<h4>10)	Selecione todos os lances onde o item é de categoria que começa com “Celular” "
				+ "e que tenha obtido lance acima de 1000</h4>");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Lance> query = cb.createQuery(Lance.class); 
		Root<Lance> root = query.from(Lance.class); 
		query.where(cb.and(cb.like(root.get("item").get("categoria").get("nome"), "Celular%"),
				           cb.greaterThan(root.get("item").get("lanceObtido").get("total"), 1000.0)));
		query.select(root);
		
		TypedQuery<Double> q = em.createQuery(query);

	}
	
	private void query10_2(Writer out) throws IOException {
		out.write("<h4>10)	Selecione todos os lances onde o item é de categoria que começa com “Celular” "
				+ "e que tenha obtido lance acima de 1000</h4>");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Lance> query = cb.createQuery(Lance.class); 
		Root<Lance> root = query.from(Lance.class); 
		
		Join<Lance, Item> item     = root.join("item");
		Join<Item, Categoria> cat  = item.join("categoria");
		Join<Item, Lance> vencedor = item.join("lanceObtido");
		
		query.where(cb.and(cb.like(cat.get("nome"), "Celular%"),
				           cb.greaterThan(vencedor.get("total"), 1000.0)));
		query.select(root);
		
		TypedQuery<Double> q = em.createQuery(query);
		
	}
	
	private void query11(Writer out) throws IOException {
		out.write("<h4>11)	Encontre a média do total de todos os pedidos</h4>");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Double> query = cb.createQuery(Double.class); // avg retorna Double
		Root<Pedido> root = query.from(Pedido.class); // from Ingresso i
		Expression<Double> media = cb.avg(root.get("total"));
		query.select(media);
		
	}
	
	private void query12(Writer out) throws IOException {
		out.write("<h4>12)	Obtenha a soma dos preços de todos os produtos dos pedidos feitos no bairro de Botafogo</h4>");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> query = cb.createQuery(BigDecimal.class); 
		Root<Pedido> root = query.from(Pedido.class);
		
		Join<Pedido, Item>    item     = root.join("itens");
		Join<Pedido, Cliente> cliente  = item.join("cliente");
		
		Predicate where = cb.and(
				cb.equal(cliente.get("bairro"), "Botafogo"),
				cb.equal(cliente.get("cidade"), "Rio de Janeiro")
		);
		query.where(where);
		Expression<BigDecimal> total = cb.sum(item.get("produto").get("preco"));
		query.select(total);
		
	}
	
	private void query13(Writer out) throws IOException {
		out.write("<h4>13)	Obtenha a contagem de clientes agrupadas por bairro");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = cb.createQuery(Object[].class); // Tambem pode usar java.persistence.Tuple
		Root<Cliente> root = query.from(Cliente.class);
		query.multiselect(root.get("bairro"), cb.count(root));
		query.groupBy(root.get("bairro"));
		
		TypedQuery<Object[]> q = em.createQuery(query);
	}
	
	private void query14(Writer out) throws IOException {
		out.write("<h4>14)	Obtenha o valor médio dos pedidos, agrupados por pontos, para os clientes que têm entre 1000 e 2000 pontos</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = cb.createQuery(Object[].class); // Tambem pode usar java.persistence.Tuple
		Root<Pedido> root = query.from(Pedido.class);
		query.multiselect(root.get("pontos"), cb.avg(root.get("total")));
		Join<Pedido, Cliente> cliente  = root.join("cliente");
		query.groupBy(cliente.get("pontos"));
		query.having(cb.between(cliente.get("pontos"), 1000.0, 2000.0));
		
		TypedQuery<Object[]> q = em.createQuery(query);
		
	}
	
	private void query15(Writer out) throws IOException {
		out.write("<h4>15)	Obtenha os empregados que são casados com outros empregados</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Empregado> query = cb.createQuery(Empregado.class);
		Root<Empregado> root = query.from(Empregado.class);
		
		Subquery<Empregado> subquery = query.subquery(Empregado.class);
		Root<Empregado> conjuge  = subquery.from(Empregado.class); 
		subquery.where(cb.equal(conjuge.get("conjuge "), root.get("conjuge ")));
		subquery.select(conjuge);
		
		query.where(cb.exists(subquery));
		query.select(root).distinct(true);
		
	}
	
	private void query16(Writer out) throws IOException {
		out.write("<h4>16)	Retorne apenas os produtos cujo preço seja maior que o valor incluído em todos os orçamentos</h4>");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = cb.createQuery(Produto.class);
		Root<Produto> root = query.from(Produto.class);
		
		Subquery<Empregado> subquery = query.subquery(Empregado.class);
		Root<Orcamento> orcamento  = subquery.from(Orcamento.class); 
		subquery.where(cb.equal(orcamento.get("item ").get("codigo"), root.get("codigo")));
		subquery.select(orcamento.get("item").get("preco"));
		
		query.where(cb.greaterThan(root.get("preco"), cb.all(subquery)));
		query.select(root);
	}

}
