package br.com.gostoudaaula.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public abstract class DAO<T> {

	protected EntityManager manager;

	@PersistenceContext
	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}

	public void salva(T t) {
		manager.persist(t);
	}

	public abstract T devolve(T t);
	
	public void clear(){
		this.manager.clear();
	}
	
	
}
