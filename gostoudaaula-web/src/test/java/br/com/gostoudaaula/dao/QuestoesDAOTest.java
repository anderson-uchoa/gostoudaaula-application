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

import br.com.gostoudaaula.dao.QuestoesDAO;
import br.com.gostoudaaula.example.QuestoesExample;
import br.com.gostoudaaula.model.Questoes;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class QuestoesDAOTest {

	@Inject
	private QuestoesDAO qDao;
	private Questoes questoes;

	@Before
	public void setup() {
		questoes = new QuestoesExample().getExample1();
	}

	@Test
	public void deveCadastrarUmQuestao() {
		qDao.salva(questoes);
		assertThat(questoes.getDescricao(), equalTo(qDao.devolve(questoes)
				.getDescricao()));
	}
	
	

}
