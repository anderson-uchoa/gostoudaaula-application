package br.com.gostoudaaula.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"/spring/spring-mvc.xml");
		EntityManagerFactory emf = ctx.getBean(EntityManagerFactory.class);
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.close();
	}
}
