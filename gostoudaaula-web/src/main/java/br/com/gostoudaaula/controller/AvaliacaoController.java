package br.com.gostoudaaula.controller;

import static br.com.gostoudaaula.http.HTTPValues.JSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.json.mixin.AulaMixIn;
import br.com.gostoudaaula.json.mixin.AvaliacaoMixIn;
import br.com.gostoudaaula.json.mixin.PeriodoLetivoMixIn;
import br.com.gostoudaaula.json.mixin.ProfessorMixIn;
import br.com.gostoudaaula.json.mixin.ProjetoMixIn;
import br.com.gostoudaaula.json.mixin.QuestoesMixIn;
import br.com.gostoudaaula.json.mixin.RespostasMixiIn;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.model.Projeto;
import br.com.gostoudaaula.model.Questoes;
import br.com.gostoudaaula.model.Respostas;
import br.com.gostoudaaula.service.AvaliacaoService;
import br.com.gostoudaaula.service.RespostasService;

@Controller
@RequestMapping("avaliacao/")
public class AvaliacaoController {

	private AvaliacaoService avaliacaoService;
	private RespostasService respostasService;

	private ObjectMapper mapper;

	@Inject
	public AvaliacaoController(AvaliacaoService avaliacaoService, RespostasService respostasService,
			ObjectMapper mapper) {
		this.avaliacaoService = avaliacaoService;
		this.mapper = mapper;
		this.respostasService = respostasService;
	}

	@RequestMapping(value = "{id}", produces = JSON, method = GET)
	public ResponseEntity<String> devolveTodasAsQuestoes(Aula aula) throws JsonProcessingException {

		Avaliacao avaliacao = avaliacaoService.retorna(aula);

		if (avaliacao != null) {
			mapper.addMixIn(Avaliacao.class, AvaliacaoMixIn.AssociationMixIn.class)
					.addMixIn(Aula.class, AulaMixIn.AssociationMixIn.class)
					.addMixIn(Professor.class, ProfessorMixIn.AssociationMixIn.class)
					.addMixIn(PeriodoLetivo.class, PeriodoLetivoMixIn.AssociationMixIn.class)
					.addMixIn(Projeto.class, ProjetoMixIn.WithQuestionsMixIn.class)
					.addMixIn(Questoes.class, QuestoesMixIn.AssociationMixIn.class);

			String json = mapper.writeValueAsString(avaliacaoService.retorna(avaliacao));
			return new ResponseEntity<String>(json, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Avaliação inexistente", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "respondida", consumes = JSON, method = POST)
	public ResponseEntity<String> avaliacaoAula(@RequestBody Aula aula) {

		Avaliacao avaliacao = avaliacaoService.retorna(aula);

		if (avaliacao != null) {
			Aluno aluno = aula.getAlunos().get(0);

			if (!avaliacaoService.jaAvaliou(aluno, avaliacao)) {
				avaliacao.adiciona(aluno);
				avaliacaoService.salva(avaliacao);
				return new ResponseEntity<String>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Aula já avaliada", HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return new ResponseEntity<String>("Não existe avaliação para essa aula", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "respostas/{id}", method = GET, produces = JSON)
	public ResponseEntity<String> respostasDe(Avaliacao avaliacao) throws JsonProcessingException {

		List<Respostas> respostas = avaliacaoService.todasRespostasDe(avaliacao);
		mapper.addMixIn(Respostas.class, RespostasMixiIn.AssociationWithQuestoesMixIn.class).addMixIn(Questoes.class,
				QuestoesMixIn.AssociationMixIn.class);
		String json = mapper.writeValueAsString(respostas);

		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@RequestMapping(value = "respostas/{id}", method = POST, consumes = JSON)
	public ResponseEntity<String> cadastraRespostaPara(Avaliacao avaliacao, @RequestBody List<Respostas> respostas) {

		if (avaliacaoService.existe(avaliacao.getId())) {

			for (Respostas resposta : respostas) {
				resposta.setAvaliacao(avaliacao);
			}
			respostasService.salva(respostas);

			return new ResponseEntity<String>("Respostas cadastradas com sucesso", HttpStatus.OK);
		}

		return new ResponseEntity<String>("Avaliação inexistente", HttpStatus.BAD_REQUEST);
	}

}
