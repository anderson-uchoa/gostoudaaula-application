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

import br.com.gostoudaaula.db.dao.AlunoDAO;
import br.com.gostoudaaula.db.repository.AlunoRepository;
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

//	@Inject
	private AlunoDAO alunoDao;
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
		Aluno alunoDevolvido = repository.findByProntuario(aluno1);
		assertThat(alunoDevolvido.getProntuario(), equalTo(13100082));
	}

	@Test
	public void deveCadastrarMaisDeUmAluno() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(aluno1);
		alunos.add(aluno2);
		alunoDao.salvaAlunos(alunos);
		List<Aluno> alunosSalvos = alunoDao.lista();
		assertThat(alunosSalvos.get(0).getProntuario(), equalTo(13100082));
		assertThat(alunosSalvos.get(1).getProntuario(), equalTo(13100083));
	}

	@Test
	public void deveCadastrarUmAlunoEmUmaAula() {
		List<Aula> aulas = new ArrayList<Aula>();
		aulas.add(new AulaExample().getExample1());
		aluno1.setAulas(aulas);
		alunoDao.salva(aluno1);
		assertThat(alunoDao.devolve(aluno1).getAulas().get(0).getData(),
				equalTo(LocalDate.now()));
	}

	@Test
	public void deveCadastrarUmAlunoEmUmaAvaliacao() {
		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
		avaliacoes.add(new AvaliacaoExample().getExample1());
		aluno1.setAvaliacoes(avaliacoes);
		alunoDao.salva(aluno1);
		assertThat(alunoDao.devolve(aluno1).getAvaliacoes().get(0).getData(),
				equalTo(LocalDate.now()));
	}

	@Test
	public void deveAutenticarAluno() {
		alunoDao.salva(aluno1);
		
		assertThat(alunoDao.autentica(aluno1), equalTo(true));
	}
	
	
	

}
