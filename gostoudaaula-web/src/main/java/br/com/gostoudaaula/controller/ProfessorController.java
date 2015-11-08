package br.com.gostoudaaula.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.service.ProfessorService;

@Controller
@RequestMapping("professor")
public class ProfessorController {

	private ProfessorService service;

	@Inject
	public ProfessorController(ProfessorService service) {
		this.service = service;
	}

	@RequestMapping("/")
	public String index() {
		Professor professor = new Professor(100);
		professor.setNome("Rodrigo");
		professor.setSobrenome("Bossini");
		service.salva(professor);
		return "redirect:/resultado";
	}

	@RequestMapping("/resultado")
	public void retorno() {
		Professor professor = new Professor(100);
		service.retorna(professor);
		System.out.println(professor);
	}
}
