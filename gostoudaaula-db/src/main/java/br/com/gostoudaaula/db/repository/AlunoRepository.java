package br.com.gostoudaaula.db.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gostoudaaula.model.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	@Query("SELECT COUNT(a) > 0 FROM Aluno a WHERE a.id = :#{#aluno.id} AND a.senha = :#{#aluno.senha}")
	public boolean autentica(@Param("aluno") Aluno aluno);

	@Query("SELECT COUNT(a) > 0 FROM Aluno a WHERE a.token = :token")
	public boolean autenticaToken(@Param("token") String token);

	public Aluno findByToken(String token);

}
