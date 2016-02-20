package br.com.gostoudaaula.example;

import br.com.gostoudaaula.model.Aluno;

public class AlunoExample {

	public Aluno getExample1() {
		Aluno aluno1 = new Aluno(13100082);
		aluno1.setNome("Alex");
		aluno1.setSobrenome("Felipe");
		aluno1.setSenha("qwerty");
		aluno1.alteraToken(aluno1);
		return aluno1;
	}

	public Aluno getExample2() {
		Aluno aluno2 = new Aluno(13100083);
		aluno2.setNome("João");
		aluno2.setSobrenome("Victor");
		aluno2.setSenha("qwerty");
		aluno2.alteraToken(aluno2);
		return aluno2;
	}
}
