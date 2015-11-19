package br.com.gostoudaaula.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import br.com.gostoudaaula.dao.AulaDAO;
import br.com.gostoudaaula.example.AulaExample;
import br.com.gostoudaaula.model.Aula;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class AulaDAOTest {

	@Inject
	private AulaDAO aulaDao;
	private Aula aula1;

	@Before
	public void setup() {
		aula1 = new AulaExample().getExample1();
	}

	@Test
	public void deveCadastrarUmaAula() {
		aulaDao.salva(aula1);
		Aula recuperado = aulaDao.devolve(aula1);
		assertThat(LocalDate.now(), equalTo(recuperado.getData()));
	}
}
