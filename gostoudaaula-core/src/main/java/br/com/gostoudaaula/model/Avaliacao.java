package br.com.gostoudaaula.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.gostoudaaula.converter.DateConverter;
import br.com.gostoudaaula.json.LocalDateDeserializer;
import br.com.gostoudaaula.json.LocalDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Avaliacao {

	private Long id;
	private Projeto projeto;
	private Aula aula;
	private List<Aluno> alunos;
	private List<Respostas> respostas;
	private LocalDate data;

	@OneToOne
	@JoinColumn(name = "id_projeto")
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

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

	@OneToOne
	@JoinColumn(name = "id_aula")
	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	@ManyToMany(mappedBy = "avaliacoes")
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@ManyToMany(mappedBy = "avaliacoes")
	public List<Respostas> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Respostas> respostas) {
		this.respostas = respostas;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Avaliacao [id=" + id + ", projeto=" + projeto + ", aula="
				+ aula + ", alunos=" + alunos + ", respostas=" + respostas
				+ ", data=" + data + "]";
	}

}
