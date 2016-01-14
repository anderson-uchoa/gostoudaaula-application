package br.com.gostoudaaula.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.gostoudaaula.converter.DateConverter;
import br.com.gostoudaaula.json.LocalDateDeserializer;
import br.com.gostoudaaula.json.LocalDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Respostas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer resposta;
	private LocalDate data;
	private Questoes questoes;
	private Avaliacao avaliacao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getResposta() {
		return resposta;
	}

	public void setResposta(Integer resposta) {
		this.resposta = resposta;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Convert(converter = DateConverter.class)
	@Column(name = "data_resposta")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_questoes")
	public Questoes getQuestoes() {
		return questoes;
	}

	public void setQuestoes(Questoes questoes) {
		this.questoes = questoes;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_avaliacao")
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
