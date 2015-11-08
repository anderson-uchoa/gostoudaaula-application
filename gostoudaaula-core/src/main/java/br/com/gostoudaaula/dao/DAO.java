package br.com.gostoudaaula.dao;

import javax.persistence.EntityManager;

public interface DAO<T> {

	public void setEntityManager(EntityManager manager);

	public void salva(T object);

	public T devolve(T object);
}
