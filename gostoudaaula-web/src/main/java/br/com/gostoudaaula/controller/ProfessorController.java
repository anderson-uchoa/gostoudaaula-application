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

import br.com.gostoudaaula.dto.PeriodoDTO;
import br.com.gostoudaaula.json.mixin.AulaMixIn;
import br.com.gostoudaaula.json.mixin.AvaliacaoMixIn;
import br.com.gostoudaaula.json.mixin.PeriodoLetivoMixIn;
import br.com.gostoudaaula.json.mixin.ProfessorMixIn;
import br.com.gostoudaaula.json.mixin.ProjetoMixIn;
import br.com.gostoudaaula.json.mixin.QuestoesMixIn;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Disciplina;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.model.Projeto;
import br.com.gostoudaaula.model.Questoes;
import br.com.gostoudaaula.model.Turma;
import br.com.gostoudaaula.service.ProfessorService;

@Controller
@RequestMapping("professor")
public class ProfessorController {

	private ProfessorService professorService;
	private ObjectMapper mapper;

	@Inject
	public ProfessorController(ProfessorService professorService, ObjectMapper mapper) {
		this.professorService = professorService;
		this.mapper = mapper;
	}

	@RequestMapping(method = GET, produces = JSON)
	public ResponseEntity<String> getProfessores() throws JsonProcessingException {
		mapper.addMixIn(Professor.class, ProfessorMixIn.AssociationMixIn.class);
		String json = mapper.writeValueAsString(professorService.getLista());
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@RequestMapping(method = POST, consumes = JSON)
	public ResponseEntity<String> salvaAluno(@RequestBody Professor professor) {

		professor.adicionaTokenDefault();
		professorService.salva(professor);
		professorService.geraNovoToken(professor);
		return new ResponseEntity<String>("Cadastrado com sucesso!", HttpStatus.OK);
	}

	@RequestMapping(value = "auth", method = POST, consumes = JSON, produces = JSON)
	public ResponseEntity<String> autenticaPorUsuarioESenha(@RequestBody Professor professor)
			throws JsonProcessingException {

		System.out.println(professor.getId());

		if (professorService.existe(professor)) {

			if (professorService.autentica(professor)) {
				mapper.addMixIn(Professor.class, ProfessorMixIn.AssociationWithTokenMixIn.class);
				Professor retornado = professorService.retorna(professor);
				professorService.atualizaToken(retornado);
				String json = mapper.writeValueAsString(retornado);
				return new ResponseEntity<String>(json, HttpStatus.ACCEPTED);
			}

			return new ResponseEntity<String>("Falha de autenticação", HttpStatus.UNAUTHORIZED);

		}

		return new ResponseEntity<String>("Não existe esse professor", HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "auth/token", method = POST, produces = JSON, consumes = JSON)
	public ResponseEntity<String> autenticaPorToken(@RequestBody Professor professor) throws JsonProcessingException {

		String tokenCriptografado = professor.getToken();

		String tokenDecriptografado = professor.decriptografa(tokenCriptografado);

		if (professorService.tokenValido(tokenDecriptografado)) {
			Professor retornado = professorService.retorna(tokenDecriptografado);
			professorService.atualizaToken(retornado);
			mapper.addMixIn(Professor.class, ProfessorMixIn.AssociationWithTokenMixIn.class);
			String json = mapper.writeValueAsString(retornado);
			return new ResponseEntity<String>(json, HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<String>("Erro de autentição", HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "turmas/{id}", method = GET, produces = JSON)
	public ResponseEntity<String> getTurmas(Professor professor) throws JsonProcessingException {
		String json = mapper.writeValueAsString(professorService.getTurmas(professor));
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@RequestMapping(value = "disciplinas/{id}", method = POST, consumes = JSON, produces = JSON)
	public ResponseEntity<String> getDisciplina(Professor professor, @RequestBody Turma turma)
			throws JsonProcessingException {
		List<Disciplina> disciplinas = professorService.getDisciplinas(professor, turma);
		String json = mapper.writeValueAsString(disciplinas);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@RequestMapping(value = "avaliacoes/{id}", method = POST, consumes = JSON, produces = JSON)
	public ResponseEntity<String> getAvaliacaoPorPeriodo(Professor professor, @RequestBody PeriodoDTO periodoDTO)
			throws JsonProcessingException {
		
		System.out.println(periodoDTO.getFim());
		System.out.println(periodoDTO.getInicio());
		System.out.println(periodoDTO.getPeriodoLetivo().getTurma());
		System.out.println(periodoDTO.getPeriodoLetivo().getDisciplina());

		List<Avaliacao> avaliacoes = professorService.getAvaliacoesPorPeriodo(professor, periodoDTO);
		mapper.addMixIn(Avaliacao.class, AvaliacaoMixIn.AssociationMixIn.class)
				.addMixIn(Aula.class, AulaMixIn.AssociationMixIn.class)
				.addMixIn(Professor.class, ProfessorMixIn.AssociationMixIn.class)
				.addMixIn(PeriodoLetivo.class, PeriodoLetivoMixIn.AssociationMixIn.class)
				.addMixIn(Projeto.class, ProjetoMixIn.WithQuestionsMixIn.class)
				.addMixIn(Questoes.class, QuestoesMixIn.AssociationMixIn.class);

		String json = mapper.writeValueAsString(avaliacoes);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

}
