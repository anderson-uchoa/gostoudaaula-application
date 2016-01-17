package br.com.gostoudaaula.dao;

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
public class AlunoDAOTest {

	@Inject
	private AlunoRepository repository;
	private Aluno aluno1;
	private Aluno aluno2;

	@Before
	public void setup() {
		aluno1 = new AlunoExample().getExample1();
		aluno2 = new AlunoExample().getExample2();
	}

	@Test
	public void deveCadastrarUmAluno() {
		repository.save(aluno1);
		Aluno alunoDevolvido = repository.findByProntuario(aluno1.getProntuario());
		assertThat(alunoDevolvido.getProntuario(), equalTo(13100082));
	}

	@Test
	public void deveCadastrarMaisDeUmAluno() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(aluno1);
		alunos.add(aluno2);
		repository.save(alunos);
		List<Aluno> alunosSalvos = (List<Aluno>) repository.findAll();
		assertThat(alunosSalvos.get(0).getProntuario(), equalTo(13100082));
		assertThat(alunosSalvos.get(1).getProntuario(), equalTo(13100083));
	}

	@Test
	public void deveCadastrarUmAlunoEmUmaAula() {
		List<Aula> aulas = new ArrayList<Aula>();
		aulas.add(new AulaExample().getExample1());
		aluno1.setAulas(aulas);
		repository.save(aluno1);
		assertThat(repository.findByProntuario(aluno1.getProntuario()).getAulas().get(0).getData(),
				equalTo(LocalDate.now()));
	}

	@Test
	public void deveCadastrarUmAlunoEmUmaAvaliacao() {
		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
		avaliacoes.add(new AvaliacaoExample().getExample1());
		aluno1.setAvaliacoes(avaliacoes);
		repository.save(aluno1);
		assertThat(repository.findByProntuario(aluno1.getProntuario()).getAvaliacoes().get(0).getData(),
				equalTo(LocalDate.now()));
	}

	@Test
	public void deveAutenticarAluno() {
		repository.save(aluno1);
		assertThat(repository.autentica(aluno1).getProntuario(), equalTo(aluno1.getProntuario()));
	}

	@Test
	public void naoDeveAutenticaAluno(){
		repository.save(aluno1);
		assertThat(repository.autentica(aluno2), equalTo(null));
	}

}
