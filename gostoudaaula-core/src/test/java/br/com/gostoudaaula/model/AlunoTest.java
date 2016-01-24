package br.com.gostoudaaula.model;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AlunoTest {

	
	@Test
	public void naoDeveInstanciarUmAlunoSemProntuario(){
		
		Aluno aluno = new Aluno();
		aluno.setNome("Alex");
		aluno.setSenha("testes");
		aluno.setSobrenome("Felipe");
		aluno.setProntuario(123);
		
		assertThat(aluno.getProntuario(), equalTo(123));
		
	}
}
