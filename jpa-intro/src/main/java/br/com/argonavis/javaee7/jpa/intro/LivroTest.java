package br.com.argonavis.javaee7.jpa.intro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class LivroTest {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tutorial-jpa");
	    EntityManager em = factory.createEntityManager();
		
		Livro livro = new Livro();
		livro.setTitulo("Meu Livro");
		livro.setPaginas(100);
		
		em.getTransaction().begin();
		em.persist(livro);
		em.getTransaction().commit();
		em.close();
	}

}
