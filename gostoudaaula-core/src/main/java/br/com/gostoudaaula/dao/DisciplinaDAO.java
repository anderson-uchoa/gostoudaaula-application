package br.com.gostoudaaula.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Disciplina;

@Repository
public class DisciplinaDAO extends DAO<Disciplina> {

	@Override
	public Disciplina devolve(Disciplina disciplina) {
		TypedQuery<Disciplina> query = manager.createQuery(
				"from Disciplina d where d.descricao = :descricao",
				Disciplina.class);
		query.setParameter("descricao", disciplina.getDescricao());
		return query.getSingleResult();
	}

}
