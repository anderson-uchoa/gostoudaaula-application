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

	public PeriodoLetivo getExample2() {
		PeriodoLetivo periodo = new PeriodoLetivo();
		periodo.setAno(2016);
		periodo.setSemestre(1);
		periodo.setTurma(new TurmaExample().getExample2());
		periodo.setDisciplina(new DisciplinaExample().getExample2());
		return periodo;
	}

	public PeriodoLetivo getExample3() {
		PeriodoLetivo periodo = new PeriodoLetivo();
		periodo.setAno(2016);
		periodo.setSemestre(2);
		periodo.setTurma(new TurmaExample().getExample3());
		periodo.setDisciplina(new DisciplinaExample().getExample3());
		return periodo;
	}
}
