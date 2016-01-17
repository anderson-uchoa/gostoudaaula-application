package br.com.gostoudaaula.db.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.gostoudaaula.model.Questoes;

public interface QuestoesRepository extends CrudRepository<Questoes, Long>{

	Questoes findByDescricao(String descricao);

}
