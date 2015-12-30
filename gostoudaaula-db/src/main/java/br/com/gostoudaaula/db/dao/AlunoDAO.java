package br.com.gostoudaaula.db.dao;

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
		TypedQuery<Aluno> query = manager.createQuery("select a from Aluno a",
				Aluno.class);
		return query.getResultList();
	}

	public void salvaAlunos(List<Aluno> alunos) {
		for (Aluno aluno : alunos) {
			manager.persist(aluno);
		}
	}

	public boolean autentica(Aluno aluno) {
		TypedQuery<Aluno> query = manager
				.createQuery(
						"Select a from Aluno a where a.prontuario = :prontuario and a.senha = :senha",
						Aluno.class);
		query.setParameter("prontuario", aluno.getProntuario());
		query.setParameter("senha", aluno.getSenha());

		System.out.println(query.getSingleResult());
		if (query.getSingleResult() != null) {
			return true;
		}
		return false;
	}
}
