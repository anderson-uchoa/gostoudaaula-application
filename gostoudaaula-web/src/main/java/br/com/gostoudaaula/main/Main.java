package br.com.gostoudaaula.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		// ApplicationContext ctx = new ClassPathXmlApplicationContext(
		// "/spring/spring-mvc.xml");
		// EntityManagerFactory emf = ctx.getBean(EntityManagerFactory.class);
		// EntityManager manager = emf.createEntityManager();
		// manager.getTransaction().begin();
		// manager.close();

		Pattern p = Pattern.compile("\\((10|[1-9]{2})\\) [2-9][0-9]{3,4}-[0-9]{4}");
		Matcher matcher = p.matcher("(31) 3111-11111");
		if (matcher.matches())
			System.out.println("ok");

	}
}
