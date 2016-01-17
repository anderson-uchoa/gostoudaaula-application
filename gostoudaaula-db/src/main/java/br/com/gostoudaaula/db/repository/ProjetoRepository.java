package br.com.gostoudaaula.db.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.gostoudaaula.model.Projeto;

public interface ProjetoRepository extends CrudRepository<Projeto, Long> {

	public Projeto findByDescricao(String descricao);

}
