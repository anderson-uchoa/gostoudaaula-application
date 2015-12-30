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

import br.com.gostoudaaula.db.dao.RespostasDAO;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.example.QuestoesExample;
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
	private Respostas respostas1;

	@Before
	public void setup() {
		respostas1 = new RespostasExample().getExample1();
	}

	@Test
	public void deveCadastrarUmaResposta() {
		rDao.salva(respostas1);
		assertThat(respostas1.getData(), equalTo(rDao.devolve(respostas1)
				.getData()));
	}

	@Test
	public void deveCadastrarRespostasParaUmaQuestao() {
		respostas1.setQuestoes(new QuestoesExample().getExample1());
		rDao.salva(respostas1);
		Respostas recuperada = rDao.devolve(respostas1);
		assertThat(recuperada.getQuestoes().getDescricao(),
				equalTo("Questão 1"));
	}

	@Test
	public void deveCadastrarRespostasParaUmaAvaliacao() {
		respostas1.setAvaliacao(new AvaliacaoExample().getExample1());
		rDao.salva(respostas1);
		Respostas recuperada = rDao.devolve(respostas1);

		assertThat(recuperada.getAvaliacao().getData(),
				equalTo(LocalDate.now()));
	}
}
