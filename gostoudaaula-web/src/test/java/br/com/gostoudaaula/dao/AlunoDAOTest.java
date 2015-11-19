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

import br.com.gostoudaaula.example.AlunoExample;
import br.com.gostoudaaula.example.AulaExample;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class AlunoDAOTest {

	@Inject
	private AlunoDAO alunoDao;
	@Inject
	private AvaliacaoDAO avaliacaoDAO;
	private Aluno aluno1;
	private Aluno aluno2;

	@Before
	public void setup() {
		aluno1 = new AlunoExample().getExample1();
		aluno2 = new AlunoExample().getExample2();
	}

	@Test
	public void deveCadastrarUmAluno() {
		alunoDao.salva(aluno1);
		Aluno alunoDevolvido = alunoDao.devolve(aluno1);
		assertThat(alunoDevolvido.getProntuario(), equalTo(13100082));
	}

	@Test
	public void deveCadastrarMaisDeUmAluno() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(aluno1);
		alunos.add(aluno2);
		alunoDao.salvaAlunos(alunos);
		assertThat(alunoDao.lista().size(), equalTo(2));
	}

	@Test
	public void deveCadastrarUmAlunoEmUmaAula() {
		List<Aula> aulas = new ArrayList<Aula>();
		Aula aula = new AulaExample().getExample1();
		aulas.add(aula);
		aluno1.setAulas(aulas);

		alunoDao.salva(aluno1);

		assertThat(LocalDate.now(), equalTo(alunoDao.devolve(aluno1).getAulas()
				.get(0).getData()));
	}

	@Test
	public void deveCadastrarUmAlunoEmUmaAvaliacao() {
		Avaliacao avaliacao = new AvaliacaoExample().getExample1();
		avaliacaoDAO.salva(avaliacao);
		Avaliacao recuperada = avaliacaoDAO.devolve(avaliacao);

		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(aluno1);
		recuperada.setAlunos(alunos);

		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
		avaliacoes.add(recuperada);
		aluno1.setAvaliacoes(avaliacoes);

		alunoDao.salva(aluno1);

		assertThat(1, equalTo(alunoDao.devolve(aluno1).getAvaliacoes().size()));
	}

}
