package br.com.gostoudaaula.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PeriodoLetivoId implements Serializable {

	private Integer ano;
	private Integer semestre;
	private Turma turma;
	private Disciplina disciplina;

	public PeriodoLetivoId() {

	}

	public PeriodoLetivoId(Integer ano, Integer semestre, Turma turma,
			Disciplina disciplina) {
		this.ano = ano;
		this.semestre = semestre;
		this.turma = turma;
		this.disciplina = disciplina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result
				+ ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result
				+ ((semestre == null) ? 0 : semestre.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodoLetivoId other = (PeriodoLetivoId) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		if (semestre == null) {
			if (other.semestre != null)
				return false;
		} else if (!semestre.equals(other.semestre))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}

}
