package br.com.gostoudaaula.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id_aluno")
public class Aluno extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Aula> aulas;
	private List<Avaliacao> avaliacoes;

	public Aluno() {

	}

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "alunos_aula", joinColumns = { @JoinColumn(name = "id_aluno") }, inverseJoinColumns = {
			@JoinColumn(name = "id_aula") })
	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE })
	@JoinTable(name = "alunos_avaliacao", joinColumns = { @JoinColumn(name = "id_aluno") }, inverseJoinColumns = {
			@JoinColumn(name = "id_avaliacao") })
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

}
