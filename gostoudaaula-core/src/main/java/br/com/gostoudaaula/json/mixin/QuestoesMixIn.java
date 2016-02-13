package br.com.gostoudaaula.json.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import br.com.gostoudaaula.model.Projeto;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE)
public class QuestoesMixIn {

	public class MainMixIn extends QuestoesMixIn {

		@JsonProperty
		private Long id;
		@JsonProperty
		private String descricao;
		@JsonProperty
		private List<Projeto> projetos;

	}

	public class AssociationMixIn extends QuestoesMixIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private String descricao;
	}
}
