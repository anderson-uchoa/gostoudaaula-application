package br.com.gostoudaaula.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Respostas;

@Repository
public class RespostasDAO extends DAO<Respostas> {

	@Override
	public Respostas devolve(Respostas respostas) {
		TypedQuery<Respostas> query = manager.createQuery(
				"from Respostas r where r.data = :data", Respostas.class);
		query.setParameter("data", respostas.getData());
		return query.getSingleResult();
	}

}
