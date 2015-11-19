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

import br.com.gostoudaaula.dao.ProfessorDAO;
import br.com.gostoudaaula.example.ProfessorExample;
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
	private Professor professor;

	@Before
	public void setup() {
		professor = new ProfessorExample().getExample1();
	}

	@Test
	public void deveCadastrarUmProfessor() {
		pDao.salva(professor);
		assertThat(professor.getChapa(), equalTo(pDao.devolve(professor)
				.getChapa()));
	}

}
