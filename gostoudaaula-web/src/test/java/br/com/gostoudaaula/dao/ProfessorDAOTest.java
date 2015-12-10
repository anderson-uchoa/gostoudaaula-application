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
import br.com.gostoudaaula.example.ProfessorExample;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Professor;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class ProfessorDAOTest {

	@Inject
	private ProfessorDAO pDao;
	private Professor professor1;

	@Before
	public void setup() {
		professor1 = new ProfessorExample().getExample1();
	}

	@Test
	public void deveCadastrarUmProfessor() {
		pDao.salva(professor1);
		assertThat(professor1.getChapa(), equalTo(pDao.devolve(professor1)
				.getChapa()));
	}

	@Test
	public void deveCadastrarUmProfessorComUmaAula() {
		List<Aula> aulas = new ArrayList<Aula>();
		AulaExample exemplo = new AulaExample();
		aulas.add(exemplo.getExample1());
		professor1.setAulas(aulas);
		pDao.salva(professor1);
		Professor recuperado = pDao.devolve(professor1);
		assertThat(recuperado.getAulas().get(0).getData(),
				equalTo(LocalDate.now()));

	}

}
