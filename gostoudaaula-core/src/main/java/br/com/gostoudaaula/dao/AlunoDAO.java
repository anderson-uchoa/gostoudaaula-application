package br.com.gostoudaaula.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Aluno;

@Repository
public class AlunoDAO extends DAO<Aluno> {

	@Override
	public Aluno devolve(Aluno aluno) {
		TypedQuery<Aluno> query = manager.createQuery(
				"from Aluno a where a.prontuario = :pProntuario", Aluno.class);
		return query.setParameter("pProntuario", aluno.getProntuario())
				.getSingleResult();
	}

	public List<Aluno> lista() {
		TypedQuery<Aluno> query = manager.createQuery(
				"select a from Aluno a inner join fetch a.aulas left join a.avaliacoes",
				Aluno.class);
		System.out.println(query.getResultList());
		return query.getResultList();
	}

	public void salvaAlunos(List<Aluno> alunos) {
		for (Aluno aluno : alunos) {
			manager.persist(aluno);
		}
	}

}
