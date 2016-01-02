package br.com.gostoudaaula.db.dao;

import java.util.List;

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

	public List<Professor> lista() {
		TypedQuery<Professor> query = manager.createQuery("select p from Professor p",
				Professor.class);
		return query.getResultList();
	}

}
