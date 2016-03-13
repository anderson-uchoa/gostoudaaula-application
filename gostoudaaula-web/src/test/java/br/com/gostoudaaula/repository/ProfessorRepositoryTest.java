package br.com.gostoudaaula.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import br.com.gostoudaaula.example.AulaExample;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.example.PeriodoLetivoExample;
import br.com.gostoudaaula.example.ProfessorExample;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Disciplina;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.model.Turma;

public class ProfessorRepositoryTest extends RepositoryTest {

	private Professor professor1;

	@Before
	public void setup() {
		professor1 = new ProfessorExample().getExample1();
	}

	@Test
	public void deveCadastrarUmProfessor() {
		professorRepo.save(professor1);
		Professor professor = devolveTodosProfessores().get(0);
		assertThat(professor.getNome(), equalTo("Rodrigo"));
	}

	@Test
	public void deveCadastrarUmProfessorComUmaAula() {
		List<Aula> aulas = new ArrayList<Aula>();
		AulaExample exemplo = new AulaExample();
		aulas.add(exemplo.getExample1());
		professor1.setAulas(aulas);
		professorRepo.save(professor1);
		Professor recuperado = devolveTodosProfessores().get(0);
		assertThat(recuperado.getAulas().get(0).getData(), equalTo(LocalDate.now()));

	}

	@Test
	public void deveAutenticarProfessor() {
		professorRepo.save(professor1);
		assertTrue(professorRepo.autentica(professor1));
	}

	@Test
	public void deveAutenticarPorToken() {
		professorRepo.save(professor1);

		assertTrue(professorRepo.autenticaToken(professor1.getToken()));
	}

	@Test
	public void deveDevolverTodasAsTurmasDoProfessor() {

		professorRepo.save(professor1);

		clearCache();

		Professor retornado = professorRepo.findByToken(professor1.getToken());

		Aula aula1 = new AulaExample().getExample1();
		aula1.setPeriodoLetivo(new PeriodoLetivoExample().getExample1());
		aula1.setProfessor(retornado);
		aulaRepo.save(aula1);

		clearCache();

		retornado = professorRepo.findByToken(professor1.getToken());

		Aula aula2 = new AulaExample().getExample2();
		aula2.setPeriodoLetivo(new PeriodoLetivoExample().getExample2());
		aula2.setProfessor(retornado);
		aulaRepo.save(aula2);

		clearCache();

		Aula aula3 = new AulaExample().getExample3();
		aula3.setPeriodoLetivo(new PeriodoLetivoExample().getExample3());
		aulaRepo.save(aula3);

		clearCache();

		List<Turma> turmas = professorRepo.todasAsTurmas(retornado);

		assertThat(turmas.size(), equalTo(2));
		assertThat(turmas.get(0).getDescricao(), equalTo("CCONA6"));
		assertThat(turmas.get(1).getDescricao(), equalTo("CCONA4"));

	}

	@Test
	public void deveDevolverTodasAsDisciplinas() {
		professorRepo.save(professor1);

		clearCache();

		Professor retornado = professorRepo.findByToken(professor1.getToken());

		Aula aula1 = new AulaExample().getExample1();
		aula1.setPeriodoLetivo(new PeriodoLetivoExample().getExample1());
		aula1.setProfessor(retornado);
		aulaRepo.save(aula1);

		clearCache();

		retornado = professorRepo.findByToken(professor1.getToken());

		Aula aula2 = new AulaExample().getExample2();
		aula2.setPeriodoLetivo(new PeriodoLetivoExample().getExample2());
		aula2.setProfessor(retornado);
		aulaRepo.save(aula2);

		clearCache();

		Aula aula3 = new AulaExample().getExample3();
		aula3.setPeriodoLetivo(new PeriodoLetivoExample().getExample3());
		aulaRepo.save(aula3);

		clearCache();

		Turma turma1 = turmaRepo.findByDescricao(aula1.getPeriodoLetivo().getTurma().getDescricao());

		List<Disciplina> disciplinas1 = professorRepo.todasAsDisciplinas(retornado, turma1);

		assertThat(disciplinas1.size(), equalTo(1));
		assertThat(disciplinas1.get(0).getDescricao(), equalTo("Programação"));

		Turma turma2 = turmaRepo.findByDescricao(aula2.getPeriodoLetivo().getTurma().getDescricao());

		List<Disciplina> disciplinas2 = professorRepo.todasAsDisciplinas(retornado, turma2);

		assertThat(disciplinas2.size(), equalTo(1));
		assertThat(disciplinas2.get(0).getDescricao(), equalTo("Calculo"));

	}

	@Test
	public void deveDevolveTodasAsAvaliacoesPorUmIntervalo() {

		AvaliacaoExample avaliacaoEx = new AvaliacaoExample();
		AulaExample aulaEx = new AulaExample();

		professorRepo.save(professor1);

		clearCache();

		Professor retornado = professorRepo.findByToken(professor1.getToken());

		Avaliacao avaliacao1 = avaliacaoEx.getExample1();
		Aula aula1 = aulaEx.getExample1();
		aula1.setProfessor(retornado);
		avaliacao1.setAula(aula1);

		avaliacaoRepo.save(avaliacao1);

		clearCache();

		retornado = professorRepo.findByToken(professor1.getToken());

		Avaliacao avaliacao2 = avaliacaoEx.getExample2();
		Aula aula2 = aulaEx.getExample2();
		aula2.setProfessor(retornado);
		avaliacao2.setAula(aula2);

		avaliacaoRepo.save(avaliacao2);

		clearCache();

		retornado = professorRepo.findByToken(professor1.getToken());

		Avaliacao avaliacao3 = avaliacaoEx.getExample3();
		Aula aula3 = aulaEx.getExample3();
		aula3.setProfessor(retornado);
		avaliacao3.setAula(aula3);

		avaliacaoRepo.save(avaliacao3);

		clearCache();

		LocalDate inicio = avaliacao1.getData();
		LocalDate fim = avaliacao1.getData();

		List<Avaliacao> avaliacoes = professorRepo.devolveAvaliacoesPorPeriodo(retornado, inicio, fim);

		assertThat(avaliacoes.size(), equalTo(1));
		assertThat(avaliacoes.get(0).getData(), equalTo(avaliacao1.getData()));

		inicio = avaliacao1.getData();
		fim = avaliacao2.getData();

		List<Avaliacao> avaliacoes2 = professorRepo.devolveAvaliacoesPorPeriodo(retornado, inicio, fim);

		assertThat(avaliacoes2.size(), equalTo(2));
		assertThat(avaliacoes2.get(0).getData(), equalTo(avaliacao1.getData()));
		assertThat(avaliacoes2.get(1).getData(), equalTo(avaliacao2.getData()));

		inicio = avaliacao1.getData();
		fim = avaliacao3.getData();

		List<Avaliacao> avaliacoes3 = professorRepo.devolveAvaliacoesPorPeriodo(retornado, inicio, fim);

		assertThat(avaliacoes3.size(), equalTo(3));
		assertThat(avaliacoes3.get(0).getData(), equalTo(avaliacao1.getData()));
		assertThat(avaliacoes3.get(1).getData(), equalTo(avaliacao2.getData()));
		assertThat(avaliacoes3.get(2).getData(), equalTo(avaliacao3.getData()));

	}

	public List<Professor> devolveTodosProfessores() {
		return (List<Professor>) professorRepo.findAll();
	}

}
