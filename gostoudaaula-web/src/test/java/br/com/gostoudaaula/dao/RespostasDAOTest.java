package br.com.gostoudaaula.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import br.com.gostoudaaula.dao.RespostasDAO;
import br.com.gostoudaaula.example.RespostasExample;
import br.com.gostoudaaula.model.Respostas;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class RespostasDAOTest {

	@Inject
	private RespostasDAO rDao;
	private Respostas respostas;

	@Before
	public void setup() {
		respostas = new RespostasExample().getExample1();
	}

	@Test
	public void deveCadastrarUmaResposta() {
		rDao.salva(respostas);
		assertThat(respostas.getData(), equalTo(rDao.devolve(respostas)
				.getData()));
	}
}
