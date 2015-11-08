package br.com.gostoudaaula.model;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PeriodoLetivo {

	@EmbeddedId
	private PeriodoLetivoId id;
	@ManyToOne
	private List<Aula> aulas;

	public PeriodoLetivoId getId() {
		return id;
	}

	public void setId(PeriodoLetivoId id) {
		this.id = id;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

}
