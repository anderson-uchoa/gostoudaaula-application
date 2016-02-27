package br.com.gostoudaaula.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Turma;

public interface PeriodoLetivoRepository extends CrudRepository<PeriodoLetivo, Long> {

	@Query("SELECT DISTINCT p.ano FROM PeriodoLetivo p")
	public List<Integer> todosOsAnos();

	@Query("SELECT DISTINCT p.semestre FROM PeriodoLetivo p WHERE p.ano = :ano AND p.turma.descricao = :descricao")
	public List<Integer> todosOsSemestres(@Param("ano") Integer ano, @Param("descricao") String descricao);

	@Query("SELECT DISTINCT p.turma FROM PeriodoLetivo p")
	public List<Turma> todasAsTurmas();

	@Query("SELECT DISTINCT p.semestre FROM PeriodoLetivo p")
	public List<Integer> todosOsSemestres();

}
