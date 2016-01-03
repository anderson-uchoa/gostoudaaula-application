package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.dao.PeriodoLetivoDAO;
import br.com.gostoudaaula.model.PeriodoLetivo;

@Service
public class PeriodoLetivoService {

	private PeriodoLetivoDAO pDao;

	@Inject
	public PeriodoLetivoService (PeriodoLetivoDAO pDao){
		this.pDao = pDao;
	}
	
	public List<PeriodoLetivo> getLista() {
		return this.pDao.lista();
	}

}
