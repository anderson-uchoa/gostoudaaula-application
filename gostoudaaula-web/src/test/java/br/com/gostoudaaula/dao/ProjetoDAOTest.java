package br.com.gostoudaaula.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

import br.com.gostoudaaula.dao.ProjetoDAO;
import br.com.gostoudaaula.example.ProjetoExample;
import br.com.gostoudaaula.model.Projeto;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {
		DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public class ProjetoDAOTest {
	
	@Inject
	private ProjetoDAO pDao;
	private Projeto projeto;

	@Before
	public void setup() {
		projeto = new ProjetoExample().getExample1();
	}

	@Test
	public void deveCadastrarUmProjeto() {
		pDao.salva(projeto);
		assertThat(projeto.getDescricao(), equalTo(pDao.devolve(projeto)
				.getDescricao()));
	}

}
