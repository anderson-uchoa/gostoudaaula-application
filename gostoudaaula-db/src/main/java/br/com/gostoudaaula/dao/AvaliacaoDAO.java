package br.com.gostoudaaula.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Avaliacao;

@Repository
public class AvaliacaoDAO extends DAO<Avaliacao> {

	@Override
	public Avaliacao devolve(Avaliacao avaliacao) {
		TypedQuery<Avaliacao> query = manager.createQuery(
				"from Avaliacao a where a.data = :data", Avaliacao.class);
		query.setParameter("data", avaliacao.getData());
		return query.getSingleResult();
	}

}
