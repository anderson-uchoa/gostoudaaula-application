package br.com.gostoudaaula.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

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
	private Aula aula2;

	@Before
	public void setup() {
		aula1 = new AulaExample().getExample1();
		aula2 = new AulaExample().getExample2();
	}

	@Test
	public void deveCadastrarUmaAula() {
		aulaDao.salva(aula1);
		Aula recuperado = aulaDao.devolve(aula1);
		assertThat(LocalDate.now(), equalTo(recuperado.getData()));
	}

	@Test
	public void deveCadastrarMainDeUmaAula() {
		List<Aula> aulas = new ArrayList<Aula>();
		aulas.add(aula1);
		aulas.add(aula2);
		aulaDao.salvaLista(aulas);
		List<Aula> recuperada = aulaDao.lista();

		assertThat(LocalDate.now(), equalTo(recuperada.get(0).getData()));
		assertThat(new LocalDate("2015-11-30"), equalTo(recuperada.get(1)
				.getData()));
	}
}
