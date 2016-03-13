package br.com.gostoudaaula.example;

import br.com.gostoudaaula.model.Disciplina;

public class DisciplinaExample {

	public Disciplina getExample1() {
		Disciplina disciplina = new Disciplina();
		disciplina.setDescricao("Programação");
		return disciplina;
	}

	public Disciplina getExample2() {
		Disciplina disciplina = new Disciplina();
		disciplina.setDescricao("Calculo");
		return disciplina;
	}

	public Disciplina getExample3() {
		Disciplina disciplina = new Disciplina();
		disciplina.setDescricao("Física");
		return disciplina;
	}
}
