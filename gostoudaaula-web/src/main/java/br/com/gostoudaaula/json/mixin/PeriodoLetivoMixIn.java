package br.com.gostoudaaula.json.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Disciplina;
import br.com.gostoudaaula.model.Turma;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE)
public class PeriodoLetivoMixIn {

	public class MainMixIn extends PeriodoLetivoMixIn {

		@JsonProperty
		private Long id;
		@JsonProperty
		private Integer ano;
		@JsonProperty
		private Integer semestre;
		@JsonProperty
		private List<Aula> aulas;
		@JsonProperty
		private Turma turma;
		@JsonProperty
		private Disciplina disciplina;

	}

	public class AssociationMixIn extends PeriodoLetivoMixIn {

		@JsonProperty
		private Long id;
		@JsonProperty
		private Integer ano;
		@JsonProperty
		private Integer semestre;
		@JsonProperty
		private Turma turma;
		@JsonProperty
		private Disciplina disciplina;
	}
}
