package br.com.gostoudaaula.example;

import org.joda.time.LocalDate;

import br.com.gostoudaaula.model.Avaliacao;

public class AvaliacaoExample {

	public Avaliacao getExample1(){
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setData(LocalDate.now());
		return avaliacao;
	}
}
