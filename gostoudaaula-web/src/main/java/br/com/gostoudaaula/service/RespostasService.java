package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.repository.RespostasRepository;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Respostas;

@Service
public class RespostasService {

	private RespostasRepository repository;

	@Inject
	public RespostasService(RespostasRepository repository) {
		this.repository = repository;
	}

	public void salva(List<Respostas> respostas) {
		repository.save(respostas);
	}

	public List<Respostas> todasRespostasDe(Avaliacao avaliacao) {
		return repository.findByAvaliacao(avaliacao);
	}
}
