package br.com.gostoudaaula.db.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.gostoudaaula.model.Turma;

public interface TurmaRepository extends CrudRepository<Turma, Long>{

	Turma findByDescricao(String string);

}
