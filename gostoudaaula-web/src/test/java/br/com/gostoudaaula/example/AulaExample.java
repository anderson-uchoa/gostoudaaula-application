package br.com.gostoudaaula.example;

import org.joda.time.LocalDate;

import br.com.gostoudaaula.model.Aula;

public class AulaExample {

	public Aula getExample1() {
		Aula aula = new Aula();
		aula.setData(LocalDate.now());
		aula.setPeriodoLetivo(new PeriodoLetivoExample().getExample1());
		return aula;
	}

	public Aula getExample2() {
		Aula aula = new Aula();
		aula.setPeriodoLetivo(new PeriodoLetivoExample().getExample2());
		aula.setData(new LocalDate("2015-11-30"));
		return aula;
	}

}
