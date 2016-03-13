package br.com.gostoudaaula.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.example.ProjetoExample;
import br.com.gostoudaaula.example.QuestoesExample;
import br.com.gostoudaaula.model.Projeto;
import br.com.gostoudaaula.model.Questoes;

public class ProjetoRepositoryTest extends RepositoryTest {

	private Projeto projeto1;

	@Before
	public void setup() {
		projeto1 = new ProjetoExample().getExample1();
	}

	@Test
	public void deveCadastrarUmProjeto() {
		projetoRepo.save(projeto1);
		assertThat(projetoRepo.findByDescricao(projeto1.getDescricao()).getDescricao(),
				equalTo(projeto1.getDescricao()));
	}

	@Test
	public void deveCadastrarUmProjetoComQuestoes() {
		List<Questoes> questoes = new ArrayList<Questoes>();
		QuestoesExample exemplo = new QuestoesExample();
		questoes.add(exemplo.getExample1());
		questoes.add(exemplo.getExample2());
		projeto1.setQuestoes(questoes);

		projetoRepo.save(projeto1);

		Projeto recuperado = projetoRepo.findByDescricao(projeto1.getDescricao());

		assertThat(recuperado.getQuestoes().get(0).getDescricao(), equalTo("Questão 1"));
		assertThat(recuperado.getQuestoes().get(1).getDescricao(), equalTo("Questão 2"));
	}

	@Test
	public void deveCadastrarUmProjetoComUmaAvaliacao() {
		projeto1.setAvaliacao(new AvaliacaoExample().getExample1());
		projetoRepo.save(projeto1);
		Projeto recuperado = projetoRepo.findByDescricao(projeto1.getDescricao());
		assertThat(recuperado.getAvaliacao().getData(), equalTo(LocalDate.now()));
	}

}
