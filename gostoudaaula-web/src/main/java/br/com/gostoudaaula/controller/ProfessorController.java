package br.com.gostoudaaula.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gostoudaaula.service.PeriodoLetivoService;

@Controller
@RequestMapping("professor/")
public class ProfessorController {

	private PeriodoLetivoService periodoService;

	@Inject
	public ProfessorController(PeriodoLetivoService periodoService) {
		this.periodoService = periodoService;
	}

}
