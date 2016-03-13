package br.com.gostoudaaula.controller;

import static br.com.gostoudaaula.http.HTTPValues.JSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.json.mixin.AulaMixIn;
import br.com.gostoudaaula.json.mixin.PeriodoLetivoMixIn;
import br.com.gostoudaaula.json.mixin.ProfessorMixIn;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.service.AulaService;

@Controller
@RequestMapping("aula")
public class AulaController {

	private ObjectMapper mapper;
	private AulaService service;

	@Inject
	public AulaController(AulaService service, ObjectMapper mapper) {
		this.mapper = mapper;
		this.service = service;
	}

	
	
	@RequestMapping(value = "{id}", produces = JSON, method = GET)
	public ResponseEntity<String> aulasSemAvaliacao(Aluno aluno) throws JsonProcessingException {

		List<Aula> aulas = service.getListaDeAulaParaAvaliar(aluno);

		if (aulas.isEmpty())
			return new ResponseEntity<String>("não existe aulas sem avaliações para esse aluno", HttpStatus.NOT_FOUND);

		mapper.addMixIn(Aula.class, AulaMixIn.AssociationMixIn.class)
				.addMixIn(Professor.class, ProfessorMixIn.AssociationMixIn.class)
				.addMixIn(PeriodoLetivo.class, PeriodoLetivoMixIn.AssociationMixIn.class);

		String json = mapper.writeValueAsString(aulas);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

}
