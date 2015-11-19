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

import br.com.gostoudaaula.dao.AvaliacaoDAO;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.model.Avaliacao;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class AvaliacaoDAOTest {

	@Inject
	private AvaliacaoDAO aDao;
	private Avaliacao avaliacao;

	@Before
	public void setup() {
		avaliacao = new AvaliacaoExample().getExample1();
	}

	@Test
	public void deveCadastrarUmaAvaliacao() {
		aDao.salva(avaliacao);
		assertThat(avaliacao.getData(), equalTo(aDao.devolve(avaliacao)
				.getData()));
	}
}
