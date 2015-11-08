package br.com.gostoudaaula.model;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.gostoudaaula.converter.DateConverter;

public class Aula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Professor professor;
	private List<Avaliacao> avaliacoes;
	private PeriodoLetivo periodoLetivo;
	private LocalDate data;
	private List<Aluno> alunos;

	@ManyToOne
	@JoinColumn(name = "id_professor")
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@OneToMany
	@JoinColumn(name = "id_avaliacao")
	public List<Avaliacao> getAvalicao() {
		return avaliacoes;
	}

	public void setAvalicao(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	@OneToOne
	@JoinColumn(name = "id_periodo_letivo")
	public PeriodoLetivo getPeriodoLetivo() {
		return periodoLetivo;
	}

	public void setPeriodoLetivo(PeriodoLetivo periodoLetivo) {
		this.periodoLetivo = periodoLetivo;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Convert(converter = DateConverter.class)
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@ManyToMany
	@JoinTable(name = "alunos_aula", joinColumns = { @JoinColumn(name = "id_aluno") }, inverseJoinColumns = { @JoinColumn(name = "id_aula") })
	public List<Aluno> getAluno() {
		return alunos;
	}

	public void setAluno(List<Aluno> aluno) {
		this.alunos = aluno;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
