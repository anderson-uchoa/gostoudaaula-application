package br.com.gostoudaaula.controller;

import static br.com.gostoudaaula.http.HTTPValues.JSON;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.service.PeriodoLetivoService;

@Controller
@RequestMapping("professor/")
public class ProfessorController {

	private PeriodoLetivoService periodoService;

	@Inject
	public ProfessorController(PeriodoLetivoService periodoService) {
		this.periodoService = periodoService;
	}

	@RequestMapping(value = "auth", method = POST, consumes = JSON, produces = JSON)
	public ResponseEntity<String> autenticaPorUsuarioESenha(Professor professor) {

		return null;

	}

}
