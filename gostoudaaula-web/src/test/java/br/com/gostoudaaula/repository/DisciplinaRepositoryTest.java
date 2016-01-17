package br.com.gostoudaaula.repository;

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

import br.com.gostoudaaula.db.repository.DisciplinaRepository;
import br.com.gostoudaaula.example.DisciplinaExample;
import br.com.gostoudaaula.model.Disciplina;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class DisciplinaRepositoryTest {

	@Inject
	private DisciplinaRepository disciplinaRepo;
	private Disciplina disciplina;

	@Before
	public void setup() {
		disciplina = new DisciplinaExample().getExample1();
	}

	@Test
	public void deveCadastrarUmaDisciplina() {
		disciplinaRepo.save(disciplina);
		assertThat(disciplina.getDescricao(),
				equalTo(disciplinaRepo.findByDescricao(disciplina.getDescricao()).getDescricao()));
	}
}
