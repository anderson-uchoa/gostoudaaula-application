package br.com.gostoudaaula.example;

import br.com.gostoudaaula.model.Aluno;

public class AlunoExample {

	public Aluno getExample1() {
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Alex");
		aluno1.setSobrenome("Felipe");
		aluno1.setSenha("qwerty");
		aluno1.setToken(aluno1.getNome());
		return aluno1;
	}

	public Aluno getExample2() {
		Aluno aluno2 = new Aluno();
		aluno2.setNome("João");
		aluno2.setSobrenome("Victor");
		aluno2.setSenha("qwerty");
		aluno2.setToken(aluno2.getNome());
		return aluno2;
	}
}
