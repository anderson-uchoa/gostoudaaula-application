package br.com.gostoudaaula.example;

import org.joda.time.LocalDate;

import br.com.gostoudaaula.model.Aula;

public class AulaExample {

	public Aula getExample1() {
		Aula aula = new Aula();
		aula.setData(LocalDate.now());
		return aula;
	}

}
