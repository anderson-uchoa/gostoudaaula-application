package br.com.gostoudaaula.controller;

import static br.com.gostoudaaula.http.HTTPValues.JSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.json.mixin.AulaMixIn;
import br.com.gostoudaaula.json.mixin.AvaliacaoMixIn;
import br.com.gostoudaaula.json.mixin.PeriodoLetivoMixIn;
import br.com.gostoudaaula.json.mixin.ProfessorMixIn;
import br.com.gostoudaaula.json.mixin.ProjetoMixIn;
import br.com.gostoudaaula.json.mixin.QuestoesMixIn;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.model.Projeto;
import br.com.gostoudaaula.model.Questoes;
import br.com.gostoudaaula.service.AvaliacaoService;

@Controller
public class AvaliacaoController {

	private AvaliacaoService service;
	private ObjectMapper mapper;

	@Inject
	public AvaliacaoController(AvaliacaoService service, ObjectMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@RequestMapping(value = "avaliacao/{id}", produces = JSON, method = GET)
	public @ResponseBody ResponseEntity<String> devolveTodasAsQuestoes(Aula aula) throws JsonProcessingException {

		Avaliacao avaliacao = service.avaliacaoDeUmaAula(aula);

		if (avaliacao != null) {
			mapper.addMixIn(Avaliacao.class, AvaliacaoMixIn.AssociationMixIn.class)
					.addMixIn(Aula.class, AulaMixIn.AssociationMixIn.class)
					.addMixIn(Professor.class, ProfessorMixIn.AssociationMixIn.class)
					.addMixIn(PeriodoLetivo.class, PeriodoLetivoMixIn.AssociationMixIn.class)
					.addMixIn(Projeto.class, ProjetoMixIn.WithQuestionsMixIn.class)
					.addMixIn(Questoes.class, QuestoesMixIn.AssociationMixIn.class);

			String json = mapper.writeValueAsString(service.retorna(avaliacao));
			return new ResponseEntity<String>(json, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Avaliação inexistente", HttpStatus.NOT_FOUND);
	}

}
