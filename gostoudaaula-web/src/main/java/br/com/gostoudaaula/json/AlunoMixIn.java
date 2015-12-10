package br.com.gostoudaaula.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE)
public class AlunoMixIn {

	@JsonProperty
	private Long id;
	@JsonProperty
	private String nome;
	@JsonProperty
	private String sobrenome;
	@JsonProperty
	private Integer prontuario;
	
}
