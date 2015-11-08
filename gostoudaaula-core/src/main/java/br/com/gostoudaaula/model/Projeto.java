package br.com.gostoudaaula.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private Avaliacao avaliacao;
	private List<Questoes> questoes;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToOne
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	@ManyToMany
	@JoinTable(name = "questoes_projeto", joinColumns = { @JoinColumn(name = "id_projeto") }, inverseJoinColumns = { @JoinColumn(name = "is_questoes") })
	public List<Questoes> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questoes> questoes) {
		this.questoes = questoes;
	}

	public Long getId() {
		return id;
	}

}
