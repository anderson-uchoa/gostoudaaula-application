package br.com.gostoudaaula.db.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Aula;

@Repository
public class AulaDAO extends DAO<Aula> {

	@Override
	public Aula devolve(Aula aula) {
		TypedQuery<Aula> query = manager.createQuery("from Aula a where a.data = :data", Aula.class);
		query.setParameter("data", aula.getData());
		return query.getSingleResult();
	}

	public List<Aula> lista() {
		TypedQuery<Aula> query = manager.createQuery("select a from Aula a", Aula.class);
		return query.getResultList();
	}

	public void salvaLista(List<Aula> aulas) {
		for (Aula aula : aulas) {
			manager.persist(aula);
		}
	}

	public List<Aula> listaDeAlunos(Integer prontuario) {
		TypedQuery<Aula> query = manager
				.createQuery("select a from Aula a join a.aluno al where al.prontuario = :prontuario", Aula.class);
		query.setParameter("prontario", prontuario);
		return query.getResultList();
	}

	public List<Aula> getAulasSemAvaliacao() {
		TypedQuery<Aula> query = manager.createQuery(
				"select a from Aula a where not exists(select av.aula from Avaliacao av where av.aula = a)",
				Aula.class);
		return query.getResultList();
	}

	public List<Aula> getAulasComAvaliacao() {
		TypedQuery<Aula> query = manager.createQuery(
				"select a from Aula a where exists(select av.aula from Avaliacao av where av.aula = a)", Aula.class);
		return query.getResultList();
	}

	//TODO evitar o máximo possível de join para não causar eager result
	public List<Aula> getAulasDeAlunosParaAvaliar(Integer prontuario) {
		TypedQuery<Aula> query = manager.createQuery(
				"select a from Aula a join a.alunos al where al.prontuario = :prontuario and not exists(select av.aula from Avaliacao av join av.alunos val where av.aula = a)",
				Aula.class);
		query.setParameter("prontuario", prontuario);
		return query.getResultList();
	}

}
