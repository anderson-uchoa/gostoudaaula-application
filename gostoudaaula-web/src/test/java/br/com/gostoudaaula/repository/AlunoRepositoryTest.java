package br.com.gostoudaaula.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import br.com.gostoudaaula.db.repository.AlunoRepository;
import br.com.gostoudaaula.example.AlunoExample;
import br.com.gostoudaaula.example.AulaExample;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class AlunoRepositoryTest {

	@Inject
	private AlunoRepository repository;
	@PersistenceContext
	private EntityManager manager;
	private Aluno aluno1;
	private Aluno aluno2;

	@Before
	public void setup() {
		aluno1 = new AlunoExample().getExample1();
		aluno2 = new AlunoExample().getExample2();
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void naoDeveCadastrarAluno() {
		Aluno aluno = new Aluno();
		repository.save(aluno);
	}

	@Test
	public void deveCadastrarUmAluno() {
		repository.save(aluno1);
		clearCache();
		List<Aluno> alunos = devolveTodosAlunos();
		Aluno aluno = alunos.get(0);
		Aluno alunoDevolvido = repository.findOne(aluno.getId());
		assertThat(alunoDevolvido.getId(), equalTo(aluno.getId()));
	}

	@Test
	public void deveCadastrarMaisDeUmAluno() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(aluno1);
		alunos.add(aluno2);
		repository.save(alunos);
		List<Aluno> alunosSalvos = devolveTodosAlunos();

		assertThat(alunosSalvos.size(), equalTo(2));
		assertThat(alunosSalvos.get(0).getNome(), equalTo(aluno1.getNome()));
		assertThat(alunosSalvos.get(1).getNome(), equalTo(aluno2.getNome()));
	}

	@Test
	public void deveCadastrarUmAlunoEmUmaAula() {
		List<Aula> aulas = new ArrayList<Aula>();
		aulas.add(new AulaExample().getExample1());
		aluno1.setAulas(aulas);
		repository.save(aluno1);
		Aluno aluno = devolveTodosAlunos().get(0);
		assertThat(aluno.getAulas().get(0).getData(), equalTo(LocalDate.now()));
	}

	@Test
	public void deveCadastrarUmAlunoEmUmaAvaliacao() {
		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
		avaliacoes.add(new AvaliacaoExample().getExample1());
		aluno1.setAvaliacoes(avaliacoes);
		repository.save(aluno1);
		Aluno aluno = devolveTodosAlunos().get(0);
		assertThat(aluno.getAvaliacoes().get(0).getData(), equalTo(LocalDate.now()));
	}

	@Test
	public void deveAutenticarAluno() {
		repository.save(aluno1);
		assertThat(repository.autentica(aluno1), equalTo(true));
	}

	@Test
	public void naoDeveAutenticaAluno() {
		repository.save(aluno1);
		assertThat(repository.autentica(aluno2), equalTo(false));
	}

	@Test
	public void deveAlterarNomeDoAluno() {
		repository.save(aluno1);
		clearCache();

		Aluno retornado = devolveTodosAlunos().get(0);

		retornado.setNome("João da Silva");
		repository.save(retornado);

		clearCache();

		Aluno aluno = devolveTodosAlunos().get(0);

		assertThat(aluno.getNome(), equalTo("João da Silva"));
	}

	@Test
	public void deveAutenticarComToken() {
		repository.save(aluno1);

		clearCache();

		Aluno aluno = new Aluno();
		aluno.setToken(aluno1.getNome());

		assertThat(repository.autenticaToken(aluno.getToken()), equalTo(true));
	}

	@Test
	public void deveRetornarUmAlunoPeloToken() {
		repository.save(aluno1); 

		clearCache();

		Aluno aluno = repository.findByToken(aluno1.getToken());

		assertThat(aluno.getNome(), equalTo("Alex"));
	}

	private void clearCache() {
		manager.flush();
		manager.clear();
	}

	private List<Aluno> devolveTodosAlunos() {
		return (List<Aluno>) repository.findAll();
	}
}
