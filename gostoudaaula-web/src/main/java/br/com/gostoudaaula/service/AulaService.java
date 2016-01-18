package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.repository.AulaRepository;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;

@Service
public class AulaService {

	private AulaRepository repository;

	@Inject
	public AulaService(AulaRepository repository) {
		this.repository = repository;
	}

	public List<Aula> getLista() {
		return (List<Aula>) repository.findAll();
	}

	@Transactional
	public void salva(Aula aula) {
		repository.save(aula);
	}

	public List<Aula> getListaDeAulaParaAvaliar(Aluno aluno) {
		return repository.findWithNotEvaluated(aluno);
	}

}
