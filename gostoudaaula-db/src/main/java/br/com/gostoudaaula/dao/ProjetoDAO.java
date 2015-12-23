package br.com.gostoudaaula.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Projeto;

@Repository
public class ProjetoDAO extends DAO<Projeto> {

	@Override
	public Projeto devolve(Projeto projeto) {
		TypedQuery<Projeto> query = manager.createQuery(
				"from Projeto p where p.descricao = :descricao", Projeto.class);
		query.setParameter("descricao", projeto.getDescricao());
		return query.getSingleResult();
	}

}
