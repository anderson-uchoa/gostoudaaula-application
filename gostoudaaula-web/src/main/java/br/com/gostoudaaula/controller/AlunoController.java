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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.json.mixin.AlunoMixIn;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.service.AlunoService;

@Controller
@RequestMapping("aluno/")
public class AlunoController {

	private AlunoService service;
	private ObjectMapper mapper;

	@Inject
	public AlunoController(AlunoService service, ObjectMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@RequestMapping(method = GET, produces = JSON)
	public ResponseEntity<String> getAlunos() throws JsonProcessingException {
		System.out.println(mapper);
		mapper.addMixIn(Aluno.class, AlunoMixIn.AssociationMixIn.class);
		String resposta = mapper.writeValueAsString(service.getLista());
		return new ResponseEntity<String>(resposta, HttpStatus.OK);
	}

	@RequestMapping(method = POST, consumes = JSON)
	public ResponseEntity<String> salvaAluno(@RequestBody Aluno aluno) {
		aluno.adicionaTokenDefault();
		service.salva(aluno);
		service.geraNovoToken(aluno);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "auth", method = POST, consumes = JSON, produces = JSON)
	public ResponseEntity<String> autenticaPorSenha(@RequestBody Aluno aluno) throws JsonProcessingException {
		String resposta = "Erro de autenticação";
		System.out.println(mapper);
		if (service.autentica(aluno)) {

			mapper.addMixIn(Aluno.class, AlunoMixIn.AssociationWithToken.class);
			Aluno retornado = service.retorna(aluno);
			service.atualizaToken(retornado);
			resposta = mapper.writeValueAsString(retornado);
			return new ResponseEntity<String>(resposta, HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<String>(resposta, HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "auth/token", method = POST, produces = JSON, consumes = JSON)
	public ResponseEntity<String> autenticaPorToken(@RequestBody Aluno aluno) throws JsonProcessingException {
		String resposta = "Erro de autenticação";

		String tokenCriptografado = aluno.getToken();

		String tokenDecriptografado = aluno.decriptografa(tokenCriptografado);

		if (service.tokenValido(tokenDecriptografado)) {
			Aluno retornado = service.retorna(tokenDecriptografado);
			service.atualizaToken(retornado);
			mapper.addMixIn(Aluno.class, AlunoMixIn.AssociationWithToken.class);
			resposta = mapper.writeValueAsString(retornado);
			return new ResponseEntity<String>(resposta, HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<String>(resposta, HttpStatus.UNAUTHORIZED);
	}

}
