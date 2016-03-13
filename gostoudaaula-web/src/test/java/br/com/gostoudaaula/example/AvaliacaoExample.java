package br.com.gostoudaaula.example;

import org.joda.time.LocalDate;

import br.com.gostoudaaula.model.Avaliacao;

public class AvaliacaoExample {

	public Avaliacao getExample1() {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setData(LocalDate.now());
		
		return avaliacao;
	}

	public Avaliacao getExample2() {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setData(LocalDate.now().plusDays(1));
		return avaliacao;
	}

	public Avaliacao getExample3() {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setData(LocalDate.now().plusDays(2));
		return avaliacao;
	}

	public Avaliacao getExample4() {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setData(LocalDate.now().plusDays(15));
		return avaliacao;
	}

	public Avaliacao getExample5() {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setData(LocalDate.now().plusDays(50));
		return avaliacao;
	}

}
