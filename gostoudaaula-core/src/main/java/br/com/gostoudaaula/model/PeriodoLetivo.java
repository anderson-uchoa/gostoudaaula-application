package br.com.gostoudaaula.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "periodo_letivo", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"ano", "semestre", "id_turma", "id_disciplina" }) })
public class PeriodoLetivo {

	private Long id;
	private Integer ano;
	private Integer semestre;
	private List<Aula> aulas;
	private Turma turma;
	private Disciplina disciplina;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	@OneToMany(mappedBy = "periodoLetivo", cascade = CascadeType.PERSIST)
	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_turma")
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_disciplina")
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PeriodoLetivo [id=" + id + ", ano=" + ano + ", semestre="
				+ semestre + ", aulas=" + aulas + ", turma=" + turma
				+ ", disciplina=" + disciplina + "]";
	}

}
