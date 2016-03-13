package br.com.gostoudaaula.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.gostoudaaula.example.ProjetoExample;
import br.com.gostoudaaula.example.QuestoesExample;
import br.com.gostoudaaula.model.Projeto;
import br.com.gostoudaaula.model.Questoes;

public class QuestoesRepositoryTest extends RepositoryTest {

	private Questoes questoes1;

	@Before
	public void setup() {
		questoes1 = new QuestoesExample().getExample1();
	}

	@Test
	public void deveCadastrarUmQuestao() {
		questoesRepo.save(questoes1);
		assertThat(questoes1.getDescricao(),
				equalTo(questoesRepo.findByDescricao(questoes1.getDescricao()).getDescricao()));
	}

	@Test
	public void deveCadastrarUmaQuestaoEmUmProjeto() {
		List<Projeto> projetos = new ArrayList<Projeto>();
		ProjetoExample exemplo = new ProjetoExample();
		projetos.add(exemplo.getExample1());
		projetos.add(exemplo.getExample2());

		questoes1.setProjetos(projetos);
		questoesRepo.save(questoes1);

		Questoes recuperado = questoesRepo.findByDescricao(questoes1.getDescricao());

		assertThat(recuperado.getProjetos().get(0).getDescricao(), equalTo("projeto teste"));
		assertThat(recuperado.getProjetos().get(1).getDescricao(), equalTo("projeto teste1"));
	}

}
