package br.com.gostoudaaula.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import br.com.gostoudaaula.example.AlunoExample;
import br.com.gostoudaaula.example.AulaExample;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.example.PeriodoLetivoExample;
import br.com.gostoudaaula.example.ProfessorExample;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;

public class AulaRepositoryTest extends RepositoryTest {

	private Aula aula1;
	private Aula aula2;

	@Before
	public void setup() {
		aula1 = new AulaExample().getExample1();
		aula2 = new AulaExample().getExample2();
	}

	@Test
	public void deveCadastrarUmaAula() {
		aulaRepo.save(aula1);
		Aula recuperado = aulaRepo.findByData(aula1.getData());
		assertThat(LocalDate.now(), equalTo(recuperado.getData()));
	}

	@Test
	public void deveCadastrarMaisDeUmaAula() {
		List<Aula> aulas = new ArrayList<Aula>();
		aulas.add(aula1);
		aulas.add(aula2);
		aulaRepo.save(aulas);
		List<Aula> recuperada = (List<Aula>) aulaRepo.findAll();

		assertThat(LocalDate.now(), equalTo(recuperada.get(0).getData()));
		assertThat(new LocalDate("2015-11-30"), equalTo(recuperada.get(1).getData()));
	}

	@Test
	public void deveCadastrarUmaAulaComAlunos() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		AlunoExample example = new AlunoExample();
		alunos.add(example.getExample1());
		alunos.add(example.getExample2());

		aula1.setAlunos(alunos);

		aulaRepo.save(aula1);
		Aula recuperada = (Aula) aulaRepo.findByData(aula1.getData());

		assertThat(recuperada.getAlunos().get(0).getNome(), equalTo("Alex"));
		assertThat(recuperada.getAlunos().get(1).getNome(), equalTo("João"));
	}

	@Test
	public void deveCadastrarUmaAulaComProfessor() {
		aula1.setProfessor(new ProfessorExample().getExample1());
		aulaRepo.save(aula1);
		Aula recuperada = aulaRepo.findByData(aula1.getData());
		assertThat(recuperada.getProfessor().getNome(), equalTo("Rodrigo"));
	}

	@Test
	public void deveCadastrarUmaAulaComPeriodoLetivo() {
		aula1.setPeriodoLetivo(new PeriodoLetivoExample().getExample1());
		aulaRepo.save(aula1);
		Aula recuperada = aulaRepo.findByData(aula1.getData());
		assertThat(recuperada.getPeriodoLetivo().getAno(), equalTo(2015));
		assertThat(recuperada.getPeriodoLetivo().getSemestre(), equalTo(6));
	}

	@Test
	public void deveDevolverTodasAsAulasAssistidasENaoAvaliadas() {
		aulaRepo.save(aula1);
		aulaRepo.save(aula2);
		List<Aula> aulas = (List<Aula>) aulaRepo.findAll();

		assertThat(aulas.get(0).getData(), equalTo(LocalDate.now()));
		assertThat(aulas.get(1).getData(), equalTo(new LocalDate("2015-11-30")));
	}

	@Test
	public void deveDevolverTodasAsAulasJaAvaliadas() {
		aulaRepo.save(aula2);

		Avaliacao avaliacao = new AvaliacaoExample().getExample1();
		avaliacao.setAula(aula1);
		avaliacaoRepo.save(avaliacao);

		List<Aula> aulas = aulaRepo.findWithAvaliacao();

		assertThat(aulas.size(), equalTo(1));
		assertThat(aulas.get(0).getData(), equalTo(LocalDate.now()));
	}

	@Test
	public void deveDevolverTodasAsAulasNaoAvaliadasDeUmAluno() {
		Aluno aluno = new AlunoExample().getExample1();
		alunoRepo.save(aluno);
		clearCache();

		Avaliacao avaliacao = new AvaliacaoExample().getExample1();
		avaliacaoRepo.save(avaliacao);
		clearCache();

		List<Aula> aulas = new ArrayList<>();
		aulas.add(aula1);
		aulas.add(aula2);
		aulaRepo.save(aulas);

		clearCache();

		List<Avaliacao> ava = new ArrayList<>();
		Avaliacao avaRetornada = avaliacaoRepo.findByData(avaliacao.getData());
		avaRetornada.setAula(aulaRepo.findByData(aula2.getData()));
		ava.add(avaRetornada);
		avaliacaoRepo.save(avaRetornada);

		clearCache();

		Aluno retornado = alunoRepo.findByToken(new AlunoExample().getExample1().getToken());
		retornado.setAvaliacoes(ava);
		retornado.setAulas(aulas);

		alunoRepo.save(retornado);

		clearCache();

		List<Aula> aulasDoAluno = aulaRepo.findWithNotEvaluated(retornado);

		assertThat(aulasDoAluno.size(), equalTo(1));
		assertThat(aulasDoAluno.get(0).getData(), equalTo(LocalDate.now()));

	}

	@Test
	public void deveAlterarADataDaAula() {
		aulaRepo.save(aula1);
		clearCache();
		Aula retornada = aulaRepo.findByData(aula1.getData());

		retornada.setData(LocalDate.now().plusDays(1));

		aulaRepo.save(retornada);

		clearCache();

		assertThat(aulaRepo.findByData(retornada.getData()).getData(), equalTo(retornada.getData()));
	}

}
