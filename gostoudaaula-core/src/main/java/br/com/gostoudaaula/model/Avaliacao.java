package br.com.gostoudaaula.model;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.gostoudaaula.converter.DateConverter;

@Entity
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Projeto projeto;
	private List<Aula> aulas;
	private List<Aluno> alunos;
	private LocalDate data;

	@OneToOne
	@JoinColumn(name = "id_projeto")
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@OneToOne
	@JoinColumn(name = "id_aula")
	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Convert(converter = DateConverter.class)
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	@ManyToMany
	@JoinTable(name = "alunos_avaliacao", joinColumns = { @JoinColumn(name = "id_avaliacao") }, inverseJoinColumns = { @JoinColumn(name = "id_aluno") })
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
