package br.com.gostoudaaula.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

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

import br.com.gostoudaaula.db.repository.AlunoRepository;
import br.com.gostoudaaula.db.repository.AulaRepository;
import br.com.gostoudaaula.db.repository.AvaliacaoRepository;
import br.com.gostoudaaula.example.AlunoExample;
import br.com.gostoudaaula.example.AulaExample;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.example.PeriodoLetivoExample;
import br.com.gostoudaaula.example.ProfessorExample;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class AulaRepositoryTest {

	@Inject
	private AulaRepository aulaRepo;
	@Inject
	private AvaliacaoRepository avaliacaoRepo;
	@Inject
	private AlunoRepository alunoRepo;
	@Inject
	private EntityManager manager;
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

		assertThat(recuperada.getAlunos().get(0).getProntuario(), equalTo(13100082));
		assertThat(recuperada.getAlunos().get(1).getProntuario(), equalTo(13100083));
	}

	@Test
	public void deveCadastrarUmaAulaComProfessor() {
		aula1.setProfessor(new ProfessorExample().getExample1());
		aulaRepo.save(aula1);
		Aula recuperada = aulaRepo.findByData(aula1.getData());
		assertThat(recuperada.getProfessor().getChapa(), equalTo(100));
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
		Avaliacao avaliacao = new AvaliacaoExample().getExample1();
		List<Aula> aulas = new ArrayList<>();
		List<Avaliacao> ava = new ArrayList<>();
		ava.add(avaliacao);

		aulas.add(aula1);
		aulas.add(aula2);
		avaliacao.setAula(aula2);
		aluno.setAvaliacoes(ava);
		aluno.setAulas(aulas);

		alunoRepo.save(aluno);

		List<Aula> aulasDoAluno = aulaRepo.findWithNotEvaluated(aluno);

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

	private void clearCache() {
		manager.flush();
		manager.clear();
	}

}
