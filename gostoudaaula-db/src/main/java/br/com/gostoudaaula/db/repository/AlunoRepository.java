package br.com.gostoudaaula.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	public Aluno findByProntuario(Aluno aluno);

	@Query("SELECT a FROM Aluno a")
	public List<Aluno> findAll();

	@Query("SELECT a FROM Aluno a WHERE a.prontuario = "
			+ ":#{#aluno.prontuario} and a.senha = :#{#aluno.nome}")
	public boolean autentica(@Param("aluno") Aluno aluno);

}
