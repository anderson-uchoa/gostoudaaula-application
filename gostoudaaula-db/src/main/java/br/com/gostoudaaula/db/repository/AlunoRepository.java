package br.com.gostoudaaula.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Token;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	public Aluno findByProntuario(Integer prontuario);

	@Query("SELECT COUNT(a) > 0 FROM Aluno a WHERE a.prontuario = :#{#aluno.prontuario} AND a.senha = :#{#aluno.senha}")
	public boolean autentica(@Param("aluno") Aluno aluno);

	@Query("SELECT COUNT(t) > 0 FROM Token t WHERE t.codigo = :#{#token.codigo}")
	public boolean autenticaToken(@Param("token") Token token);

	@Query("SELECT a FROM Aluno a WHERE a.token.codigo = :#{#token.codigo}")
	public Aluno retornaPorToken(@Param("token") Token token);

}
