package br.com.gostoudaaula.db.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.gostoudaaula.model.Disciplina;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {

	public Disciplina findByDescricao(String descricao);

}
