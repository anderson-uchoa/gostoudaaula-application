package br.com.gostoudaaula.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Turma;
import br.com.gostoudaaula.service.PeriodoLetivoService;

@Controller
@RequestMapping("professor/")
public class ProfessorController {

	private PeriodoLetivoService periodoService;

	@Inject
	public ProfessorController(PeriodoLetivoService periodoService) {
		this.periodoService = periodoService;
	}

	@RequestMapping(value = "aula/nova", method = RequestMethod.GET)
	public ModelAndView form(PeriodoLetivo periodoLetivo) {
		ModelAndView mav = new ModelAndView("professor/aula/cadastro", "aula", new Aula());
		List<Integer> anos = periodoService.todosOsAnos();
		List<Turma> turmas = periodoService.todasAsTurmas();
		List<Integer> semestres = periodoService.todosOsSemestre();
		mav.addObject("anos", anos);
		mav.addObject("turmas", turmas);
		mav.addObject("semestres", semestres);

		return mav;
	}

	@RequestMapping(value = "aula/cadastro", method = RequestMethod.POST)
	public String cadastroDeAula(@ModelAttribute("aula") Aula aula) {

		System.out.println("ano: " + aula.getPeriodoLetivo().getAno());
		System.out.println("semestre: " + aula.getPeriodoLetivo().getSemestre());
		System.out.println("turma: " + aula.getPeriodoLetivo().getTurma().getDescricao());
		System.out.println("disciplina: " + aula.getPeriodoLetivo().getDisciplina().getDescricao());
		System.out.println("data: " + aula.getData());

		return "redirect:professor/aula";
	}

	@RequestMapping(value = "aulas", method = RequestMethod.GET)
	public ModelAndView aulas() {
		return new ModelAndView("professor/aula/lista");
	}
}
