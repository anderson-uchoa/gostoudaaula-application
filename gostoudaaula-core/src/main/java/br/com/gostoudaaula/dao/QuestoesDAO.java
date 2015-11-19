package br.com.gostoudaaula.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Questoes;

@Repository
public class QuestoesDAO extends DAO<Questoes> {

	@Override
	public Questoes devolve(Questoes questoes) {
		TypedQuery<Questoes> query = manager.createQuery(
				"from Questoes q where q.descricao = :descricao",
				Questoes.class);
		query.setParameter("descricao", questoes.getDescricao());
		return query.getSingleResult();
	}

}
