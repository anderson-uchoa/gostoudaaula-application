package br.com.gostoudaaula.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.gostoudaaula.example.AlunoExample;
import br.com.gostoudaaula.example.AulaExample;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;

public class AlunoRepositoryTest extends RepositoryTest {

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
		alunoRepo.save(aluno);
	}

	@Test
	public void deveCadastrarUmAluno() {
		alunoRepo.save(aluno1);
		clearCache();
		List<Aluno> alunos = devolveTodosAlunos();
		Aluno aluno = alunos.get(0);
		Aluno alunoDevolvido = alunoRepo.findOne(aluno.getId());
		assertThat(alunoDevolvido.getId(), equalTo(aluno.getId()));
	}

	@Test
	public void deveCadastrarMaisDeUmAluno() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(aluno1);
		alunos.add(aluno2);
		alunoRepo.save(alunos);
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
		alunoRepo.save(aluno1);
		Aluno aluno = devolveTodosAlunos().get(0);
		assertThat(aluno.getAulas().get(0).getData(), equalTo(LocalDate.now()));
	}

	@Test
	public void deveCadastrarUmAlunoEmUmaAvaliacao() {
		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
		avaliacoes.add(new AvaliacaoExample().getExample1());
		aluno1.setAvaliacoes(avaliacoes);
		alunoRepo.save(aluno1);
		Aluno aluno = devolveTodosAlunos().get(0);
		assertThat(aluno.getAvaliacoes().get(0).getData(), equalTo(LocalDate.now()));
	}

	@Test
	public void deveAutenticarAluno() {
		alunoRepo.save(aluno1);
		assertThat(alunoRepo.autentica(aluno1), equalTo(true));
	}

	@Test
	public void naoDeveAutenticaAluno() {
		alunoRepo.save(aluno1);
		assertThat(alunoRepo.autentica(aluno2), equalTo(false));
	}

	@Test
	public void deveAlterarNomeDoAluno() {
		alunoRepo.save(aluno1);
		clearCache();

		Aluno retornado = devolveTodosAlunos().get(0);

		retornado.setNome("João da Silva");
		alunoRepo.save(retornado);

		clearCache();

		Aluno aluno = devolveTodosAlunos().get(0);

		assertThat(aluno.getNome(), equalTo("João da Silva"));
	}

	@Test
	public void deveAutenticarComToken() {
		alunoRepo.save(aluno1);

		clearCache();

		Aluno aluno = new Aluno();
		aluno.setToken(aluno1.getNome());

		assertThat(alunoRepo.autenticaToken(aluno.getToken()), equalTo(true));
	}

	@Test
	public void deveRetornarUmAlunoPeloToken() {
		alunoRepo.save(aluno1);

		clearCache();

		Aluno aluno = alunoRepo.findByToken(aluno1.getToken());

		assertThat(aluno.getNome(), equalTo("Alex"));
	}

	private List<Aluno> devolveTodosAlunos() {
		return (List<Aluno>) alunoRepo.findAll();
	}
}
