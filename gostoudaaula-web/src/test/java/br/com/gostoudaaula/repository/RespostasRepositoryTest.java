package br.com.gostoudaaula.repository;

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

import br.com.gostoudaaula.db.repository.RespostasRepository;
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
public class RespostasRepositoryTest {

	@Inject
	private RespostasRepository respostasRepo;
	private Respostas respostas1;

	@Before
	public void setup() {
		respostas1 = new RespostasExample().getExample1();
	}

	@Test
	public void deveCadastrarUmaResposta() {
		respostasRepo.save(respostas1);
		assertThat(respostas1.getData(), equalTo(respostasRepo.findByData(respostas1.getData())
				.getData()));
	}

	@Test
	public void deveCadastrarRespostasParaUmaQuestao() {
		respostas1.setQuestoes(new QuestoesExample().getExample1());
		respostasRepo.save(respostas1);
		Respostas recuperada = respostasRepo.findByData(respostas1.getData());
		assertThat(recuperada.getQuestoes().getDescricao(),
				equalTo("Questão 1"));
	}

	@Test
	public void deveCadastrarRespostasParaUmaAvaliacao() {
		respostas1.setAvaliacao(new AvaliacaoExample().getExample1());
		respostasRepo.save(respostas1);
		Respostas recuperada = respostasRepo.findByData(respostas1.getData());

		assertThat(recuperada.getAvaliacao().getData(),
				equalTo(LocalDate.now()));
	}
}
