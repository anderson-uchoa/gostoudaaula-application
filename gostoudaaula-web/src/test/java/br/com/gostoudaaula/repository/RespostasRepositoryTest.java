package br.com.gostoudaaula.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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

import br.com.gostoudaaula.db.repository.AvaliacaoRepository;
import br.com.gostoudaaula.db.repository.QuestoesRepository;
import br.com.gostoudaaula.db.repository.RespostasRepository;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.example.QuestoesExample;
import br.com.gostoudaaula.example.RespostasExample;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Questoes;
import br.com.gostoudaaula.model.Respostas;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class RespostasRepositoryTest {

	@Inject
	private RespostasRepository respostasRepo;
	@Inject
	private QuestoesRepository questoesRepo;
	@Inject
	private AvaliacaoRepository avaliacaoRepo;
	@Inject
	private EntityManager manager;
	private Respostas respostas1;

	@Before
	public void setup() {
		respostas1 = new RespostasExample().getExample1();
	}

	@Test
	public void deveCadastrarUmaResposta() {
		respostasRepo.save(respostas1);
		assertThat(respostas1.getData(), equalTo(respostasRepo.findByData(respostas1.getData()).getData()));
	}

	@Test
	public void deveCadastrarRespostasParaUmaQuestao() {

		Questoes questoes = new QuestoesExample().getExample1();

		questoesRepo.save(questoes);

		clearCache();

		respostas1.setQuestoes(questoesRepo.findByDescricao(questoes.getDescricao()));
		respostasRepo.save(respostas1);
		Respostas recuperada = respostasRepo.findByData(respostas1.getData());
		assertThat(recuperada.getQuestoes().getDescricao(), equalTo("Questão 1"));
	}

	@Test
	public void deveCadastrarRespostasParaUmaAvaliacao() {

		Avaliacao avaliacao = new AvaliacaoExample().getExample1();

		avaliacaoRepo.save(avaliacao);
		clearCache();

		respostas1.setAvaliacao(avaliacaoRepo.findByData(avaliacao.getData()));
		respostasRepo.save(respostas1);

		clearCache();
		
		Respostas recuperada = respostasRepo.findByData(respostas1.getData());

		assertThat(recuperada.getAvaliacao().getData(), equalTo(LocalDate.now()));
	}

	private void clearCache() {
		manager.flush();
		manager.clear();
	}
}
