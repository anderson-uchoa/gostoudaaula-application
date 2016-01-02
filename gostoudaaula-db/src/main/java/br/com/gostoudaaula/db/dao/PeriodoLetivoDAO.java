package br.com.gostoudaaula.db.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.PeriodoLetivo;

@Repository
public class PeriodoLetivoDAO extends DAO<PeriodoLetivo> {

	@Override
	public PeriodoLetivo devolve(PeriodoLetivo periodo) {
		TypedQuery<PeriodoLetivo> query = manager.createQuery(
				"from PeriodoLetivo p where p.ano = :ano "
						+ "and p.semestre = :semestre", PeriodoLetivo.class);
		query.setParameter("ano", periodo.getAno());
		query.setParameter("semestre", periodo.getSemestre());
		return query.getSingleResult();
	}

	public List<PeriodoLetivo> lista() {
		return null;
	}
}
