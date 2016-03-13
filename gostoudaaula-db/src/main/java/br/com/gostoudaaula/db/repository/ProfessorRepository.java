package br.com.gostoudaaula.db.repository;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Disciplina;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.model.Turma;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {

	@Query("SELECT COUNT(p) > 0 FROM Professor p WHERE p.id = :#{#professor.id} AND p.senha = :#{#professor.senha}")
	public boolean autentica(@Param("professor") Professor professor);

	@Query("SELECT COUNT(p) > 0 FROM Professor p WHERE p.token = :token")
	public boolean autenticaToken(@Param("token") String token);

	public Professor findByToken(String token);

	@Query("SELECT DISTINCT a.periodoLetivo.turma FROM Aula a WHERE a.professor = :professor")
	public List<Turma> todasAsTurmas(@Param("professor") Professor professor1);

	@Query("SELECT DISTINCT a.periodoLetivo.disciplina FROM Aula a WHERE a.professor = :professor AND "
			+ "a.periodoLetivo.turma = :turma")
	public List<Disciplina> todasAsDisciplinas(@Param("professor") Professor retornado, @Param("turma") Turma turma);

	@Query("SELECT a FROM Avaliacao a WHERE a.data BETWEEN :inicio AND :fim AND a.aula.professor = :professor")
	public List<Avaliacao> devolveAvaliacoesPorPeriodo(@Param("professor") Professor retornado, @Param("inicio") LocalDate inicio,
			@Param("fim") LocalDate fim);
}
