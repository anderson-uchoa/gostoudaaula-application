package br.com.gostoudaaula.example;

import br.com.gostoudaaula.model.Professor;

public class ProfessorExample {

	public Professor getExample1(){
		Professor professor = new Professor(100);
		professor.setNome("Rodrigo");
		professor.setSobrenome("Bossini");
		return professor;
	}
}
