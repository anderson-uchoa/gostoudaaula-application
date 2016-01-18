package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.repository.ProfessorRepository;
import br.com.gostoudaaula.model.Professor;

@Service
public class ProfessorService {


	private ProfessorRepository repository;

	@Inject
	public ProfessorService(ProfessorRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public void salva(Professor professor) {
		repository.save(professor);
	}

	public Professor retorna(Professor professor) {
		return repository.findByChapa(professor.getChapa());
	}

	public List<Professor> getLista() {
		return (List<Professor>) repository.findAll();
	}
}
