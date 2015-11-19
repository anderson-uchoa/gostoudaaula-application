package br.com.gostoudaaula.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Professor extends Pessoa {

	private Integer chapa;
	private List<Aula> aulas;

	public Professor() {
	}

	public Professor(Integer chapa) {
		this.chapa = chapa;
	}
	
	@OneToMany(mappedBy="professor")
	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public Integer getChapa() {
		return chapa;
	}

	public void setChapa(Integer chapa) {
		this.chapa = chapa;
	}

	@Override
	public String toString() {
		return "Professor [chapa=" + chapa + ", aulas=" + aulas + ", id=" + id
				+ ", nome=" + nome + ", sobrenome=" + sobrenome + "]";
	}

	
	
}
