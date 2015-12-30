package br.com.gostoudaaula.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import br.com.gostoudaaula.db.dao.QuestoesDAO;
import br.com.gostoudaaula.example.ProjetoExample;
import br.com.gostoudaaula.example.QuestoesExample;
import br.com.gostoudaaula.model.Projeto;
import br.com.gostoudaaula.model.Questoes;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class QuestoesDAOTest {

	@Inject
	private QuestoesDAO qDao;
	private Questoes questoes1;

	@Before
	public void setup() {
		questoes1 = new QuestoesExample().getExample1();
	}

	@Test
	public void deveCadastrarUmQuestao() {
		qDao.salva(questoes1);
		assertThat(questoes1.getDescricao(), equalTo(qDao.devolve(questoes1)
				.getDescricao()));
	}

	@Test
	public void deveCadastrarUmaQuestaoEmUmProjeto() {
		List<Projeto> projetos = new ArrayList<Projeto>();
		ProjetoExample exemplo = new ProjetoExample();
		projetos.add(exemplo.getExample1());
		projetos.add(exemplo.getExample2());

		questoes1.setProjetos(projetos);
		qDao.salva(questoes1);

		Questoes recuperado = qDao.devolve(questoes1
				);

		assertThat(recuperado.getProjetos().get(0).getDescricao(),
				equalTo("projeto teste"));
		assertThat(recuperado.getProjetos().get(1).getDescricao(),
				equalTo("projeto teste1"));
	}

}
