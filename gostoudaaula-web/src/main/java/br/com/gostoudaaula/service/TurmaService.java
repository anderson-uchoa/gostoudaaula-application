package br.com.gostoudaaula.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.repository.TurmaRepository;
import br.com.gostoudaaula.model.Turma;

@Service
public class TurmaService {

	private TurmaRepository repository;

	@Inject
	public TurmaService(TurmaRepository repository) {
		this.repository = repository;
	}

	public void salva(Turma turma) {
		repository.save(turma);
	}

	public Turma retorna(Turma turma) {
		return repository.findOne(turma.getId());
	}

}
