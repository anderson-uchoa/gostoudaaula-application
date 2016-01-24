package br.com.gostoudaaula.example;

import br.com.gostoudaaula.model.PeriodoLetivo;

public class PeriodoLetivoExample {

	public PeriodoLetivo getExample1() {
		PeriodoLetivo periodo = new PeriodoLetivo();
		periodo.setAno(2015);
		periodo.setSemestre(6);
		periodo.setTurma(new TurmaExample().getExample1());
		periodo.setDisciplina(new DisciplinaExample().getExample1());
		return periodo;
	}
}
