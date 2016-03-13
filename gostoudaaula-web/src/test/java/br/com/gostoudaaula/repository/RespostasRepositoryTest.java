package br.com.gostoudaaula.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.example.QuestoesExample;
import br.com.gostoudaaula.example.RespostasExample;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Questoes;
import br.com.gostoudaaula.model.Respostas;

public class RespostasRepositoryTest extends RepositoryTest {

	private Respostas respostas1;

	@Before
	public void setup() {
		respostas1 = new RespostasExample().getExample1();
	}

	@Test
	public void deveCadastrarUmaResposta() {
		respostasRepo.save(respostas1);
		assertThat(respostas1.getData(), equalTo(respostasRepo.findByData(respostas1.getData()).getData()));
	}

	@Test
	public void deveCadastrarRespostasParaUmaQuestao() {

		Questoes questoes = new QuestoesExample().getExample1();

		questoesRepo.save(questoes);

		clearCache();

		respostas1.setQuestoes(questoesRepo.findByDescricao(questoes.getDescricao()));
		respostasRepo.save(respostas1);
		Respostas recuperada = respostasRepo.findByData(respostas1.getData());
		assertThat(recuperada.getQuestoes().getDescricao(), equalTo("Questão 1"));
	}

	@Test
	public void deveCadastrarRespostasParaUmaAvaliacao() {

		Avaliacao avaliacao = new AvaliacaoExample().getExample1();

		avaliacaoRepo.save(avaliacao);
		clearCache();

		respostas1.setAvaliacao(avaliacaoRepo.findByData(avaliacao.getData()));
		respostasRepo.save(respostas1);

		clearCache();

		Respostas recuperada = respostasRepo.findByData(respostas1.getData());

		assertThat(recuperada.getAvaliacao().getData(), equalTo(LocalDate.now()));
	}

}
