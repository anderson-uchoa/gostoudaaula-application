package br.com.gostoudaaula.db.service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.dao.ProfessorDAO;
import br.com.gostoudaaula.model.Professor;

@Service
public class ProfessorService {

	private ProfessorDAO pDao;

	@Inject
	public ProfessorService(ProfessorDAO pDao) {
		this.pDao = pDao;
	}

	@Transactional
	public void salva(Professor professor) {
		pDao.salva(professor);
	}

	public Professor retorna(Professor professor) {
		return pDao.devolve(professor);
	}
}
