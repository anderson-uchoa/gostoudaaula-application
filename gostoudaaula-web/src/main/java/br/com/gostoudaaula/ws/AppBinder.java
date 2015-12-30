package br.com.gostoudaaula.ws;

import java.io.IOException;
import java.util.List;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.gostoudaaula.db.dao.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
public class AppBinder extends AbstractBinder {

	private ClassUtil classUtil;
	private static final ClassPathXmlApplicationContext ctx;

	static {
		ctx = beanManager();
	}

	public AppBinder(ClassUtil classUtil) {
		this.classUtil = classUtil;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void configure() {
		try {

			List<Class> classes = classUtil
					.fromPackage("br.com.gostoudaaula.dao");
			classes.addAll(classUtil
					.fromPackage("br.com.gostoudaaula.service"));

			for (Class clazz : classes) {
				if(clazz != DAO.class){
				bind(getBean(ctx, clazz)).to(clazz);
				}
			}

			bindFactory(ObjectMapperFactory.class)
					.to(ObjectMapper.class);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private <T> T getBean(ApplicationContext ctx, Class<T> clazz) {
		return ctx.getBean(clazz);
	}

	private static ClassPathXmlApplicationContext beanManager() {
		return new ClassPathXmlApplicationContext("spring/spring-ioc.xml");
	}

	public static ClassPathXmlApplicationContext getCtx() {
		return ctx;
	}
}