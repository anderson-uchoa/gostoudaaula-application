package br.com.gostoudaaula.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.gostoudaaula.example.TurmaExample;
import br.com.gostoudaaula.model.Turma;

public class TurmaRepositoryTest extends RepositoryTest {

	private Turma turma;

	@Before
	public void setup() {
		turma = new TurmaExample().getExample1();
	}

	@Test
	public void deveCadastrarUmaTurma() {
		turmaRepo.save(turma);
		assertThat(turma.getDescricao(), equalTo(turmaRepo.findByDescricao(turma.getDescricao()).getDescricao()));
	}
}
