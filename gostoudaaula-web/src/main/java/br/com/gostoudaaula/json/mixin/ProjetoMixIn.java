package br.com.gostoudaaula.json.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Questoes;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE)
public class ProjetoMixIn {

	
	public class MainMixIn extends ProjetoMixIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private String descricao;
		@JsonProperty
		private Avaliacao avaliacao;
		@JsonProperty
		private List<Questoes> questoes;
	}
	
	public class AssociationMixIn extends ProjetoMixIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private String descricao;
	}
}
