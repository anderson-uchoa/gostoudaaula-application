package br.com.gostoudaaula.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Aluno;

@Repository
public class AlunoDAO implements DAO<Aluno> {

	private EntityManager manager;

	@Override
	@PersistenceContext
	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void salva(Aluno aluno) {
		manager.persist(aluno);
	}

	@Override
	public Aluno devolve(Aluno aluno) {
		TypedQuery<Aluno> query = manager.createQuery(
				"from Aluno a where a.prontuario = :pProntuario", Aluno.class);
		return query.setParameter("pProntuario", aluno.getProntuario())
				.getSingleResult();
	}

	public List<Aluno> lista() {
		TypedQuery<Aluno> query = manager
				.createQuery("from Aluno", Aluno.class);
		return query.getResultList();
	}

}
