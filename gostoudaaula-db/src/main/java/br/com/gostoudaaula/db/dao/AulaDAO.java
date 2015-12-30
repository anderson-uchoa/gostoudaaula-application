package br.com.gostoudaaula.db.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Aula;

@Repository
public class AulaDAO extends DAO<Aula> {

	@Override
	public Aula devolve(Aula aula) {
		TypedQuery<Aula> query = manager.createQuery(
				"from Aula a where a.data = :data", Aula.class);
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

}