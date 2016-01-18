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

import br.com.gostoudaaula.db.repository.TurmaRepository;
import br.com.gostoudaaula.example.TurmaExample;
import br.com.gostoudaaula.model.Turma;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class TurmaRepositoryTest {

	@Inject
	private TurmaRepository turmaRepo;
	private Turma turma;

	@Before
	public void setup() {
		turma = new TurmaExample().getExample1();
	}

	@Test
	public void deveCadastrarUmaTurma() {
		turmaRepo.save(turma);
		assertThat(turma.getDescricao(), equalTo(turmaRepo.findByDescricao(turma.getDescricao()).getDescricao()));
	}
}