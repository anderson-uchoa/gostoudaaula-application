package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;

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
		System.out.println(aDao.lista());
		return aDao.lista();
	}

}
