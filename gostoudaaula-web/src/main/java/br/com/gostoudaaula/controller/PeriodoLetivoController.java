package br.com.gostoudaaula.controller;

import static br.com.gostoudaaula.http.HTTPValues.JSON;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.model.Turma;
import br.com.gostoudaaula.service.PeriodoLetivoService;

@Controller
@RequestMapping("periodo-letivo")
public class PeriodoLetivoController {

	private PeriodoLetivoService service;
	private ObjectMapper mapper;

	@Inject
	public PeriodoLetivoController(PeriodoLetivoService service, ObjectMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@RequestMapping(value = "anos", method = RequestMethod.GET, produces = JSON)
	public ResponseEntity<String> anosDePeriodoLetivo() throws JsonProcessingException {
		List<Integer> anos = service.todosOsAnos();
		String json = mapper.writeValueAsString(anos);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@RequestMapping(value = "semestre/{ano}/{descricao}", method = RequestMethod.GET, produces = JSON)
	public ResponseEntity<String> semestreDoPeriodoLetivo(@PathVariable("ano") Integer ano, Turma turma)
			throws JsonProcessingException {
		List<Integer> semestres = service.todosOsSemestre(ano, turma);
		System.out.println(semestres);
		String json = mapper.writeValueAsString(semestres);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

}
