package br.com.gostoudaaula.example;

import br.com.gostoudaaula.model.Professor;

public class ProfessorExample {

	public Professor getExample1() {
		Professor professor = new Professor();
		professor.setNome("Rodrigo");
		professor.setSobrenome("Bossini");
		professor.setSenha("qwerty");
		professor.setToken(professor.getNome());
		return professor;
	}

	public Professor getExample2() {
		Professor professor = new Professor();
		professor.setNome("Antônio");
		professor.setSobrenome("Guardado");
		professor.setSenha("bdnosql");
		professor.setToken(professor.getNome());
		return null;
	}
}
