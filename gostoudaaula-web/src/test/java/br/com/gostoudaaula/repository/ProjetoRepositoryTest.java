package br.com.gostoudaaula.repository;

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

import br.com.gostoudaaula.db.repository.ProjetoRepository;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.example.ProjetoExample;
import br.com.gostoudaaula.example.QuestoesExample;
import br.com.gostoudaaula.model.Projeto;
import br.com.gostoudaaula.model.Questoes;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class ProjetoRepositoryTest {

	@Inject
	private ProjetoRepository projetoRepo;
	private Projeto projeto1;

	@Before
	public void setup() {
		projeto1 = new ProjetoExample().getExample1();
	}

	@Test
	public void deveCadastrarUmProjeto() {
		projetoRepo.save(projeto1);
		assertThat(projetoRepo.findByDescricao(projeto1.getDescricao()).getDescricao(),
				equalTo(projeto1.getDescricao()));
	}

	@Test
	public void deveCadastrarUmProjetoComQuestoes() {
		List<Questoes> questoes = new ArrayList<Questoes>();
		QuestoesExample exemplo = new QuestoesExample();
		questoes.add(exemplo.getExample1());
		questoes.add(exemplo.getExample2());
		projeto1.setQuestoes(questoes);

		projetoRepo.save(projeto1);

		Projeto recuperado = projetoRepo.findByDescricao(projeto1.getDescricao());

		assertThat(recuperado.getQuestoes().get(0).getDescricao(),
				equalTo("Questão 1"));
		assertThat(recuperado.getQuestoes().get(1).getDescricao(),
				equalTo("Questão 2"));
	}

	@Test
	public void deveCadastrarUmProjetoComUmaAvaliacao() {
		projeto1.setAvaliacao(new AvaliacaoExample().getExample1());
		projetoRepo.save(projeto1);
		Projeto recuperado = projetoRepo.findByDescricao(projeto1.getDescricao());
		assertThat(recuperado.getAvaliacao().getData(),
				equalTo(LocalDate.now()));
	}

}
