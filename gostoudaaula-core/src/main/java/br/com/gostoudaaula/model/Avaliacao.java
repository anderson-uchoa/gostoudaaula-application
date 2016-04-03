package br.com.gostoudaaula.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.gostoudaaula.converter.DateConverter;
import br.com.gostoudaaula.json.LocalDateDeserializer;
import br.com.gostoudaaula.json.LocalDateSerializer;

@Entity
@Table(name = "avaliacao", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_projeto", "id_aula" }) })
public class Avaliacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Projeto projeto;
	private Aula aula;
	private List<Aluno> alunos;
	private List<Respostas> respostas;
	private LocalDate data;

	public Avaliacao() {
	}

	public void adiciona(Aluno aluno) {
		this.alunos.add(aluno);
	}

	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_projeto")
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Convert(converter = DateConverter.class)
	@Column(name = "data_avaliacao")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	@NotNull
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_aula")
	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "alunos_avaliacao", joinColumns = { @JoinColumn(name = "id_avaliacao") }, inverseJoinColumns = {
			@JoinColumn(name = "id_aluno") })
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@OneToMany(mappedBy = "avaliacao", cascade = CascadeType.PERSIST)
	public List<Respostas> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Respostas> respostas) {
		this.respostas = respostas;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void adiciona(Respostas resposta) {
		respostas.add(resposta);
	}

}
