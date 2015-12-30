package br.com.gostoudaaula.db.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Turma;

@Repository
public class TurmaDAO extends DAO<Turma> {

	@Override
	public Turma devolve(Turma turma) {
		TypedQuery<Turma> query = manager.createQuery(
				"from Turma t where t.descricao = :descricao", Turma.class);
		query.setParameter("descricao", turma.getDescricao());
		return query.getSingleResult();
	}

}
