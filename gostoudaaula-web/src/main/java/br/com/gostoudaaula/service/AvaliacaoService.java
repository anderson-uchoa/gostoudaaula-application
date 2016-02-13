package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.repository.AvaliacaoRepository;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Questoes;
import br.com.gostoudaaula.model.Respostas;

@Service
public class AvaliacaoService {

	private AvaliacaoRepository repository;

	@Inject
	public AvaliacaoService(AvaliacaoRepository repository) {
		this.repository = repository;
	}

	public List<Questoes> todasQuestoesDeUmaAvaliacao(Avaliacao avaliacao) {
		return repository.todasAsQuestoes(avaliacao);
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

	public Avaliacao retorna(Aula aula) {
		return repository.findByAula(aula);
	}

	public void salva(Avaliacao avaliacao) {
		repository.save(avaliacao);
	}

	public boolean jaAvaliou(Aluno aluno, Avaliacao avaliacao) {
		return repository.jaAvaliou(aluno, avaliacao);
	}

	public List<Respostas> todasRespostasDe(Avaliacao avaliacao) {
		return repository.todasAsRespostas(avaliacao);
	}

}
