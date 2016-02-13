package br.com.gostoudaaula.json.mixin;

import java.util.List;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Professor;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE)
public class AulaMixIn {

	public class MainMixIn extends AulaMixIn {

		@JsonProperty
		private Long id;
		@JsonProperty
		private Professor professor;
		@JsonProperty
		private PeriodoLetivo periodoLetivo;
		@JsonProperty
		private LocalDate data;
		@JsonProperty
		private List<Aluno> alunos;

	}

	public class AssociationMixIn extends AulaMixIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private Professor professor;
		@JsonProperty
		private PeriodoLetivo periodoLetivo;
		@JsonProperty
		private LocalDate data;
	}

	

}
