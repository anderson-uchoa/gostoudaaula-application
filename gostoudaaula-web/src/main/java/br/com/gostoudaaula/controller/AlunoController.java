package br.com.gostoudaaula.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.service.AlunoService;

@Controller
@RequestMapping("aluno")
public class AlunoController {

	private AlunoService service;

	@Inject
	public AlunoController(AlunoService service) {
		this.service = service;
	}

	@RequestMapping("/")
	public String cadastra() {
		return "index";
	}

	@RequestMapping("cadastro")
	public String cadastro(Aluno aluno, RedirectAttributes redirectAttributes) {
		service.salva(aluno);
		redirectAttributes.addFlashAttribute("message",
				"aluno: " + aluno.getNome() + " cadastrado com sucesso!");
		return "redirect:/alunos";
	}

	@RequestMapping("alunos")
	public ModelAndView listaAlunos() {
		ModelAndView mav = new ModelAndView("alunos");
		List<Aluno> alunos = service.getLista();
		mav.addObject("alunos", alunos);
		return mav;
	}

	@RequestMapping("alunosjson")
	public @ResponseBody List<Aluno> alunosjson() {
		return service.getLista();
	}
}
