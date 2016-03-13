package br.com.gostoudaaula.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.gostoudaaula.example.DisciplinaExample;
import br.com.gostoudaaula.model.Disciplina;

public class DisciplinaRepositoryTest extends RepositoryTest {

	private Disciplina disciplina;

	@Before
	public void setup() {
		disciplina = new DisciplinaExample().getExample1();
	}

	@Test
	public void deveCadastrarUmaDisciplina() {
		disciplinaRepo.save(disciplina);
		assertThat(disciplina.getDescricao(),
				equalTo(disciplinaRepo.findByDescricao(disciplina.getDescricao()).getDescricao()));
	}
}
