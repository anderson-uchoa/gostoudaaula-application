package br.com.gostoudaaula.db.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.gostoudaaula.model.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {

}
