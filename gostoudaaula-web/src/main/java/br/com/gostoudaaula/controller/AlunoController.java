package br.com.gostoudaaula.controller;

import static br.com.gostoudaaula.http.HTTPValues.JSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.json.mixin.AlunoMixIn;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.service.AlunoService;

@Controller
public class AlunoController {

	private AlunoService service;
	private ObjectMapper mapper;

	@Inject
	public AlunoController(AlunoService service, ObjectMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@RequestMapping(value = "alunos", method = GET, produces = JSON)
	public @ResponseBody ResponseEntity<String> getAlunos() throws JsonProcessingException {
		mapper.addMixIn(Aluno.class, AlunoMixIn.AssociationMixIn.class);
		String resposta = mapper.writeValueAsString(service.getLista());
		return new ResponseEntity<String>(resposta, HttpStatus.OK);
	}

	@RequestMapping(value = "alunos", method = POST, consumes = JSON)
	public @ResponseBody ResponseEntity<String> salvaAluno(@RequestBody Aluno aluno) {
		service.salva(aluno);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "aluno/autentica/{prontuario}")
	public @ResponseBody ResponseEntity<String> autentica(@RequestBody Aluno aluno) throws JsonProcessingException{
		String resposta = "Erro de autenticação";
		
		if(service.autentica(aluno)){
			mapper.addMixIn(Aluno.class, AlunoMixIn.AssociationMixIn.class);
			resposta = mapper.writeValueAsString(service.retorna(aluno));
			return new ResponseEntity<String>(resposta, HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<String>(resposta, HttpStatus.UNAUTHORIZED);
	}

}
