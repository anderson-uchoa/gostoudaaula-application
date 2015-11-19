package br.com.gostoudaaula.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Professor;

@Repository
public class ProfessorDAO extends DAO<Professor> {

	@Override
	public Professor devolve(Professor professor) {
		TypedQuery<Professor> query = manager.createQuery(
				"from Professor p where p.chapa = :chapa", Professor.class);
		query.setParameter("chapa", professor.getChapa());
		return query.getSingleResult();
	}

}
