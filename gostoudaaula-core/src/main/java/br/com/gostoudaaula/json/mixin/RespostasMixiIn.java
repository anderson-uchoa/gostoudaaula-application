package br.com.gostoudaaula.json.mixin;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Questoes;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE)
public class RespostasMixiIn {

	public class MainMixIn extends RespostasMixiIn {

		@JsonProperty
		private Long id;
		@JsonProperty
		private Integer resposta;
		@JsonProperty
		private LocalDate data;
		@JsonProperty
		private Questoes questoes;
		@JsonProperty
		private Avaliacao avaliacao;

	}

	public class AssociationMixIn extends RespostasMixiIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private Integer resposta;
		@JsonProperty
		private LocalDate data;
	}

	public class AssociationWithQuestoesMixIn extends RespostasMixiIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private Integer resposta;
		@JsonProperty
		private LocalDate data;
		@JsonProperty
		private Questoes questoes;
	}
}
