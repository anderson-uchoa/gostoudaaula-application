package br.com.gostoudaaula.json.mixin;

import java.util.List;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Projeto;
import br.com.gostoudaaula.model.Respostas;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE)
public class AvaliacaoMixIn {

	public class MainMixIn extends AvaliacaoMixIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private Projeto projeto;
		@JsonProperty
		private Aula aula;
		@JsonProperty
		private List<Aluno> alunos;
		@JsonProperty
		private List<Respostas> respostas;
		@JsonProperty
		private LocalDate data;
	}

	public class AssociationMixIn extends AvaliacaoMixIn {
		@JsonProperty
		private Long id;
		@JsonProperty
		private Aula aula;
		@JsonProperty
		private Projeto projeto;
		@JsonProperty
		private LocalDate data;
	}
	
}
