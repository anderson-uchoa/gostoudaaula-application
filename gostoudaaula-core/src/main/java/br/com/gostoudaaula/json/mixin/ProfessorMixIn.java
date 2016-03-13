package br.com.gostoudaaula.json.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.gostoudaaula.model.Aula;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE)
public class ProfessorMixIn {

	public class MainMixIn extends ProfessorMixIn {

		@JsonProperty
		private Long id;
		@JsonProperty
		private String nome;
		@JsonProperty
		private String sobrenome;
		@JsonProperty
		private Integer chapa;
		@JsonProperty
		private List<Aula> aulas;

	}

	public class AssociationMixIn extends ProfessorMixIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private String nome;
		@JsonProperty
		private String sobrenome;
	}

	public class AssociationWithTokenMixIn extends ProfessorMixIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private String nome;
		@JsonProperty
		private String sobrenome;
		@JsonProperty
		private String token;
	}

}
