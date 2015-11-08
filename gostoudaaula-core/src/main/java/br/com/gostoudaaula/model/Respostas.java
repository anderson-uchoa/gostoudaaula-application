package br.com.gostoudaaula.model;

import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.gostoudaaula.converter.DateConverter;

public class Respostas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private LocalDate data;
	private Questoes questoes;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Convert(converter = DateConverter.class)
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@ManyToOne
	@JoinColumn(name = "id_questoes")
	public Questoes getQuestoes() {
		return questoes;
	}

	public void setQuestoes(Questoes questoes) {
		this.questoes = questoes;
	}

	public Long getId() {
		return id;
	}

}
