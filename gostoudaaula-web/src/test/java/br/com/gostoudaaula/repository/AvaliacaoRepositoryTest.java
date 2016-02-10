package br.com.gostoudaaula.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import br.com.gostoudaaula.db.repository.AlunoRepository;
import br.com.gostoudaaula.db.repository.AvaliacaoRepository;
import br.com.gostoudaaula.example.AlunoExample;
import br.com.gostoudaaula.example.AulaExample;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.example.ProjetoExample;
import br.com.gostoudaaula.example.QuestoesExample;
import br.com.gostoudaaula.example.RespostasExample;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Projeto;
import br.com.gostoudaaula.model.Questoes;
import br.com.gostoudaaula.model.Respostas;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class AvaliacaoRepositoryTest {

	@PersistenceContext
	private EntityManager manager;
	@Inject
	private AvaliacaoRepository avaliacaoRepo;
	@Inject
	private AlunoRepository alunoRepo;
	private Avaliacao avaliacao1;

	@Before
	public void setup() {
		avaliacao1 = new AvaliacaoExample().getExample1();
	}

	@Test
	public void deveDevolveAvaliacaoPeloId() {
		avaliacaoRepo.save(avaliacao1);

		clearCache();

		Avaliacao recuperada = avaliacaoRepo.findByData(avaliacao1.getData());
		Avaliacao recuperadaPeloId = avaliacaoRepo.findOne(recuperada.getId());
		assertThat(recuperadaPeloId.getId(), equalTo(recuperada.getId()));
	}

	@Test
	public void deveCadastrarUmaAvaliacao() {
		avaliacaoRepo.save(avaliacao1);

		clearCache();

		assertThat(avaliacao1.getData(), equalTo(avaliacaoRepo.findByData(avaliacao1.getData()).getData()));
	}

	@Test
	public void deveCadastrarUmaAvaliacaComUmaAula() {
		avaliacao1.setAula(new AulaExample().getExample1());
		avaliacaoRepo.save(avaliacao1);

		clearCache();

		Avaliacao recuperada = avaliacaoRepo.findByData(avaliacao1.getData());
		assertThat(recuperada.getAula().getData(), equalTo(LocalDate.now()));
	}

	@Test
	public void deveCadastrarUmaAvaliacaoComUmProjeto() {
		avaliacao1.setProjeto(new ProjetoExample().getExample1());
		avaliacaoRepo.save(avaliacao1);

		clearCache();

		Avaliacao recuperada = avaliacaoRepo.findByData(avaliacao1.getData());
		assertThat(recuperada.getProjeto().getDescricao(), equalTo("projeto teste"));
	}

	@Test
	public void deveCadastrarUmaAvaliacaoComRespostas() {
		// FIXME parou de pegar depois de arrumar o cache, coloque tudo no
		// objeto antes de salvar ou antes de limpar o cache, se não vai dar
		// problema
		List<Respostas> respostas = new ArrayList<Respostas>();
		RespostasExample exemplo = new RespostasExample();

		respostas.add(exemplo.getExample1());
		respostas.add(exemplo.getExample2());

		avaliacao1.setRespostas(respostas);

		avaliacaoRepo.save(avaliacao1);

		clearCache();

		Avaliacao recuperada = avaliacaoRepo.findByData(avaliacao1.getData());

		assertThat(recuperada.getRespostas().get(0).getResposta(), equalTo(10));
		assertThat(recuperada.getRespostas().get(1).getResposta(), equalTo(9));

	}

	@Test
	public void deveCadastrarAvaliacaoComAlunos() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		AlunoExample exemplo = new AlunoExample();
		alunos.add(exemplo.getExample1());
		alunos.add(exemplo.getExample2());
		avaliacao1.setAlunos(alunos);
		avaliacaoRepo.save(avaliacao1);

		clearCache();

		Avaliacao recuperada = avaliacaoRepo.findByData(avaliacao1.getData());

		assertThat(recuperada.getAlunos().get(0).getProntuario(), equalTo(13100082));
		assertThat(recuperada.getAlunos().get(1).getProntuario(), equalTo(13100083));
	}

	public void deveDevolverTodasAsQuestoesDeUmaAvaliacao() {
		List<Questoes> questoes = new ArrayList<>();
		Questoes q1 = new QuestoesExample().getExample1();
		Questoes q2 = new QuestoesExample().getExample2();

		questoes.addAll(Arrays.asList(q1, q2));

		Projeto projeto = new Projeto();
		projeto.setDescricao("Projeto de teste");
		projeto.setQuestoes(questoes);

		avaliacao1.setProjeto(projeto);

		avaliacaoRepo.save(avaliacao1);

		clearCache();

		Avaliacao recuperada = avaliacaoRepo.findByData(avaliacao1.getData());

		List<Questoes> questoesRecuperadas = avaliacaoRepo.todasAsQuestoesDeUmaAvaliacao(recuperada);

		assertThat(questoesRecuperadas.size(), equalTo(2));
		assertThat(questoesRecuperadas.get(0).getDescricao(), equalTo(q1.getDescricao()));
		assertThat(questoesRecuperadas.get(1).getDescricao(), equalTo(q2.getDescricao()));

	}

	@Test
	public void deveDevolveAvaliacaoJaAvaliada() {
		Aluno aluno = new AlunoExample().getExample1();
		List<Aluno> alunos = new ArrayList<Aluno>();
		avaliacao1.setAlunos(alunos);
		avaliacao1.adiciona(aluno);

		avaliacaoRepo.save(avaliacao1);

		clearCache();

		Avaliacao retornadaAva = avaliacaoRepo.findByData(avaliacao1.getData());

		assertThat(avaliacaoRepo.jaAvaliou(aluno, retornadaAva), equalTo(true));
	}

	@Test
	public void deveInformarAvaliacaoNaoAvaliada() {
		Aluno aluno = new AlunoExample().getExample1();
		alunoRepo.save(aluno);

		clearCache();

		avaliacaoRepo.save(avaliacao1);

		clearCache();

		Avaliacao retornadaAva = avaliacaoRepo.findByData(avaliacao1.getData());

		assertThat(avaliacaoRepo.jaAvaliou(aluno, retornadaAva), equalTo(false));
	}

	@Test
	public void deveAtualizarUmaDataDaAvaliacao() {
		avaliacaoRepo.save(avaliacao1);

		clearCache();

		Avaliacao retornada = avaliacaoRepo.findByData(avaliacao1.getData());

		retornada.setData(LocalDate.now().plusDays(1));

		avaliacaoRepo.save(retornada);

		clearCache();

		assertThat(avaliacaoRepo.findByData(retornada.getData()).getData(), equalTo(retornada.getData()));
	}

	private void clearCache() {
		manager.flush();
		manager.clear();
	}

}
