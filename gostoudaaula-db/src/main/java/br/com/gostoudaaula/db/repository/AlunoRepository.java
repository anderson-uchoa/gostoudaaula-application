package br.com.gostoudaaula.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	public Aluno findByProntuario(Integer prontuario);

	@Query("FROM Aluno a WHERE a.prontuario = :#{#aluno.prontuario} AND a.senha = :#{#aluno.senha}")
	public Aluno autentica(@Param("aluno") Aluno aluno);

}
