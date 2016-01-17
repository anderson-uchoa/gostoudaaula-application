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

import br.com.gostoudaaula.db.repository.AvaliacaoRepository;
import br.com.gostoudaaula.example.AlunoExample;
import br.com.gostoudaaula.example.AulaExample;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.example.ProjetoExample;
import br.com.gostoudaaula.example.RespostasExample;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Respostas;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class AvaliacaoRepositoryTest {

	@Inject
	private AvaliacaoRepository avaliacaoRepo;
	private Avaliacao avaliacao1;

	@Before
	public void setup() {
		avaliacao1 = new AvaliacaoExample().getExample1();
	}

	@Test
	public void deveCadastrarUmaAvaliacao() {
		avaliacaoRepo.save(avaliacao1);
		assertThat(avaliacao1.getData(), equalTo(avaliacaoRepo.findByData(avaliacao1.getData()).getData()));
	}

	@Test
	public void deveCadastrarUmaAvaliacaComUmaAula() {
		avaliacao1.setAula(new AulaExample().getExample1());
		avaliacaoRepo.save(avaliacao1);
		Avaliacao recuperada = avaliacaoRepo.findByData(avaliacao1.getData());
		assertThat(recuperada.getAula().getData(), equalTo(LocalDate.now()));
	}

	@Test
	public void deveCadastrarUmaAvaliacaoComUmProjeto() {
		avaliacao1.setProjeto(new ProjetoExample().getExample1());
		avaliacaoRepo.save(avaliacao1);
		Avaliacao recuperada = avaliacaoRepo.findByData(avaliacao1.getData());
		assertThat(recuperada.getProjeto().getDescricao(), equalTo("projeto teste"));
	}

	@Test
	public void deveCadastrarUmaAvaliacaoComRespotas() {
		List<Respostas> respostas = new ArrayList<Respostas>();
		RespostasExample exemplo = new RespostasExample();
		respostas.add(exemplo.getExample1());
		respostas.add(exemplo.getExample2());
		avaliacao1.setRespostas(respostas);
		avaliacaoRepo.save(avaliacao1);

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

		Avaliacao recuperada = avaliacaoRepo.findByData(avaliacao1.getData());

		assertThat(recuperada.getAlunos().get(0).getProntuario(), equalTo(13100082));
		assertThat(recuperada.getAlunos().get(1).getProntuario(), equalTo(13100083));
	}
}
