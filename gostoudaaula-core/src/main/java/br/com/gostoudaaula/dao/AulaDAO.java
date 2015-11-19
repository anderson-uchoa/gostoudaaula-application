package br.com.gostoudaaula.dao;

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

}
