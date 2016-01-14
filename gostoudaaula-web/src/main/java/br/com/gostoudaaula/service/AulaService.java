package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.dao.AulaDAO;
import br.com.gostoudaaula.model.Aula;

@Service
public class AulaService {

	private AulaDAO aDao;

	@Inject
	public AulaService(AulaDAO aDao) {
		this.aDao = aDao;
	}

	public List<Aula> getLista() {
		return aDao.lista();
	}

	@Transactional
	public void salva(Aula aula) {
		aDao.salva(aula);
	}

	public List<Aula> getListaOfAlunos(Integer prontuario) {
		return aDao.listaDeAlunos(prontuario);
	}

	public List<Aula> getListaDeAulaParaAvaliar(Integer prontuario) {
		return aDao.getAulasDeAlunosParaAvaliar(prontuario);
	}

}
