package br.com.gostoudaaula.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Professor;

@Repository
public class ProfessorDAO implements DAO<Professor> {

	private EntityManager manager;

	@Override
	@PersistenceContext
	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void salva(Professor professor) {
		manager.persist(professor);
	}

	@Override
	public Professor devolve(Professor professor) {
		TypedQuery<Professor> query = manager.createQuery(
				"from Professor p where p.chapa = :pChapa", Professor.class);
		return query.setParameter("pChapa", professor.getChapa())
				.getSingleResult();
	}

}
