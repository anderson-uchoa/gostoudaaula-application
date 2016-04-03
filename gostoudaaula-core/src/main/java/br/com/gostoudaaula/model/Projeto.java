package br.com.gostoudaaula.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Projeto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;
	private Avaliacao avaliacao;
	private List<Questoes> questoes;

	public Projeto() {
	}

	@NotEmpty
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToOne(mappedBy = "projeto", cascade = CascadeType.PERSIST)
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "questoes_projeto", joinColumns = { @JoinColumn(name = "id_projeto") }, inverseJoinColumns = {
			@JoinColumn(name = "id_questoes") })
	public List<Questoes> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questoes> questoes) {
		this.questoes = questoes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
