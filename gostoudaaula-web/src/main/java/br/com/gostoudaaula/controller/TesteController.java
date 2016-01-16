package br.com.gostoudaaula.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.json.mixin.AlunoMixIn;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.service.AlunoService;

@Controller
public class TesteController {

	private AlunoService service;

	@Inject
	public TesteController(AlunoService service) {
		this.service = service;
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/aluno")
	public @ResponseBody String teste() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Aluno.class, AlunoMixIn.AssociationMixIn.class);
		String json = mapper.writeValueAsString(service.getLista());
		return json;
	}
}
