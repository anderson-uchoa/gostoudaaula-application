package br.com.gostoudaaula.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.repository.PeriodoLetivoRepository;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Turma;

@Service
public class PeriodoLetivoService {

	private PeriodoLetivoRepository repository;

	@Inject
	public PeriodoLetivoService(PeriodoLetivoRepository repository) {
		this.repository = repository;
	}

	public List<PeriodoLetivo> getLista() {
		return (List<PeriodoLetivo>) repository.findAll();
	}

	public List<Integer> todosOsAnos() {
		return repository.todosOsAnos();
	}

	public List<Integer> todosOsSemestre(Integer ano, Turma turma) {
		return repository.todosOsSemestres(ano, turma.getDescricao());
	}

	public List<Integer> todosOsSemestre() {
		List<Integer> semestres = new ArrayList<>();
		semestres.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		return semestres;
	}

	public List<Turma> todasAsTurmas() {
		return repository.todasAsTurmas();
	}

}
