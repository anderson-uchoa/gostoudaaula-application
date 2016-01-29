package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.repository.AvaliacaoRepository;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Questoes;

@Service
public class AvaliacaoService {

	private AvaliacaoRepository repository;

	@Inject
	public AvaliacaoService(AvaliacaoRepository repository) {
		this.repository = repository;
	}

	public List<Questoes> todasQuestoesDeUmaAvaliacao(Avaliacao avaliacao) {
		return repository.todasAsQuestoesDeUmaAvaliacao(avaliacao);
	}

	public boolean existe(Long id) {
		return repository.exists(id);
	}

	public Avaliacao retornaPorData(Avaliacao avaliacao) {
		return repository.findByData(avaliacao.getData());
	}

	public Avaliacao retorna(Avaliacao avaliacao) {
		return repository.retorna(avaliacao);
	}

}
