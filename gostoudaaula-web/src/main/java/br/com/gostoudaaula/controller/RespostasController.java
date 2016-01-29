package br.com.gostoudaaula.controller;

import static br.com.gostoudaaula.http.HTTPValues.JSON;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gostoudaaula.model.Respostas;
import br.com.gostoudaaula.service.RespostasService;

@Controller
@RequestMapping("respostas")
public class RespostasController {

	private RespostasService service;

	@Inject
	public RespostasController(RespostasService service) {
		this.service = service;
	}

	@RequestMapping(method = POST, consumes = JSON)
	public ResponseEntity<String> cadastraRespostas(List<Respostas> respostas) {
		service.salva(respostas);
		return new ResponseEntity<String>("Respostas Cadastradas", HttpStatus.OK);
	}
}
