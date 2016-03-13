package br.com.gostoudaaula.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import br.com.gostoudaaula.db.repository.AlunoRepository;
import br.com.gostoudaaula.db.repository.AulaRepository;
import br.com.gostoudaaula.db.repository.AvaliacaoRepository;
import br.com.gostoudaaula.db.repository.DisciplinaRepository;
import br.com.gostoudaaula.db.repository.ProfessorRepository;
import br.com.gostoudaaula.db.repository.ProjetoRepository;
import br.com.gostoudaaula.db.repository.QuestoesRepository;
import br.com.gostoudaaula.db.repository.RespostasRepository;
import br.com.gostoudaaula.db.repository.TurmaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "/spring/daoContext.xml")
@Transactional
public abstract class RepositoryTest {

	@PersistenceContext
	private EntityManager manager;
	@Inject
	protected AlunoRepository alunoRepo;
	@Inject
	protected AulaRepository aulaRepo;
	@Inject
	protected AvaliacaoRepository avaliacaoRepo;
	@Inject
	protected DisciplinaRepository disciplinaRepo;
	@Inject
	protected ProfessorRepository professorRepo;
	@Inject
	protected ProjetoRepository projetoRepo;
	@Inject
	protected QuestoesRepository questoesRepo;
	@Inject
	protected RespostasRepository respostasRepo;
	@Inject
	protected TurmaRepository turmaRepo;

	protected void clearCache() {
		manager.flush();
		manager.clear();
	}

}
