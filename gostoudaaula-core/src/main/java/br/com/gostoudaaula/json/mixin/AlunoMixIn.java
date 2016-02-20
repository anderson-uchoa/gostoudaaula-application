package br.com.gostoudaaula.json.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Token;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE)
public class AlunoMixIn {

	public class MainMixIn extends AlunoMixIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private String nome;
		@JsonProperty
		private String sobrenome;
		@JsonProperty
		private Integer prontuario;
		@JsonProperty
		private String senha;
		@JsonProperty
		private List<Aula> aulas;
		@JsonProperty
		private List<Avaliacao> avaliacoes;
	}

	public class AssociationMixIn extends AlunoMixIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private String nome;
		@JsonProperty
		private String sobrenome;
		@JsonProperty
		private Integer prontuario;
	}

	public class AssociationWithToken extends AlunoMixIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private String nome;
		@JsonProperty
		private String sobrenome;
		@JsonProperty
		private Integer prontuario;
		@JsonProperty
		private Token token;
	}

}
