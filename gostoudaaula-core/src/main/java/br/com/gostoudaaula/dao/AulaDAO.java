package br.com.gostoudaaula.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Aula;

@Repository
public class AulaDAO extends DAO<Aula> {

	@Override
	public Aula devolve(Aula aula) {
		TypedQuery<Aula> query = manager.createQuery(
				"from Aula a where a.data = :data", Aula.class);
		query.setParameter("data", aula.getData());
		return query.getSingleResult();
	}

	public List<Aula> lista() {
		TypedQuery<Aula> query = manager.createQuery("from Aula", Aula.class);
		return query.getResultList();

	}

}
