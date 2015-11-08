package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.dao.AlunoDAO;
import br.com.gostoudaaula.model.Aluno;

@Service
public class AlunoService {

	private AlunoDAO aDao;

	@Inject
	public AlunoService(AlunoDAO aDao) {
		this.aDao = aDao;
	}

	@Transactional
	public void salva(Aluno aluno) {
		aDao.salva(aluno);
	}

	public Aluno retorna(Aluno aluno) {
		return aDao.devolve(aluno);
	}

	public List<Aluno> getLista() {
		return aDao.lista();
	}

}
