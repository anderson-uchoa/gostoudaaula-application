package br.com.gostoudaaula.example;

import org.joda.time.LocalDate;

import br.com.gostoudaaula.model.Respostas;

public class RespostasExample {

	public Respostas getExample1(){
		Respostas respostas = new Respostas();
		respostas.setResposta(10);
		respostas.setData(LocalDate.now());
		return respostas;
	}
	
}
