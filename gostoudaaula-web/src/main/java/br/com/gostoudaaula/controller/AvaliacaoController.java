package br.com.gostoudaaula.controller;

import static br.com.gostoudaaula.http.HTTPValues.JSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.json.mixin.QuestoesMixIn;
import br.com.gostoudaaula.model.Avaliacao;
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
	public @ResponseBody ResponseEntity<String> devolveTodasAsQuestoes(@PathVariable("id") Long id)
			throws JsonProcessingException {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setId(id);
		System.out.println(avaliacao.getId());
		if (service.existe(avaliacao.getId())) {
			mapper.addMixIn(Questoes.class, QuestoesMixIn.AssociationMixIn.class);
			String json = mapper.writeValueAsString(service.todasQuestoesDeUmaAvaliacao(avaliacao));
			return new ResponseEntity<String>(json, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Avaliação inexistente", HttpStatus.NOT_FOUND);
	}
}
