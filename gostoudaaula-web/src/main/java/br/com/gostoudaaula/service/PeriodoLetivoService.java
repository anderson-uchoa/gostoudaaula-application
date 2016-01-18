package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.repository.PeriodoLetivoRepository;
import br.com.gostoudaaula.model.PeriodoLetivo;

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

}
