package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.repository.ProfessorRepository;
import br.com.gostoudaaula.dto.PeriodoDTO;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Disciplina;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.model.Turma;

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
		return repository.findOne(professor.getId());
	}

	public List<Professor> getLista() {
		return (List<Professor>) repository.findAll();
	}

	public boolean existe(Professor professor) {
		return repository.exists(professor.getId());
	}

	public boolean autentica(Professor professor) {
		return repository.autentica(professor);
	}

	public boolean tokenValido(String token) {
		return repository.autenticaToken(token);
	}

	public Professor retorna(String token) {
		return repository.findByToken(token);
	}

	public void atualizaToken(Professor professor) {
		geraToken(professor);
		professor.setToken(professor.criptografa());
	}

	public void geraNovoToken(Professor professor) {
		Professor retornado = retorna(professor.getToken());
		geraToken(retornado);
	}

	private void geraToken(Professor professor) {
		professor.novoToken();
		salva(professor);
	}

	public List<Turma> getTurmas(Professor professor) {
		return repository.todasAsTurmas(professor);
	}

	public List<Disciplina> getDisciplinas(Professor professor, Turma turma) {
		return repository.todasAsDisciplinas(professor, turma);
	}

	public List<Avaliacao> getAvaliacoesPorPeriodo(Professor professor, PeriodoDTO periodoDTO) {
		return repository.devolveAvaliacoesPorPeriodo(professor, periodoDTO.getInicio(), periodoDTO.getFim(),
				periodoDTO.getPeriodoLetivo().getTurma(), periodoDTO.getPeriodoLetivo().getDisciplina());
	}
}
