package br.com.gostoudaaula.db.repository;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;

public interface AulaRepository extends CrudRepository<Aula, Long> {

	public Aula findByData(LocalDate data);

	@Query("from Aula a where exists(select av.aula from Avaliacao av where av.aula = a)")
	public List<Aula> findWithAvaliacao();

	@Query("select a from Aula a join a.alunos al where al.prontuario = :#{#aluno.prontuario} and "
			+ "not exists(select av from Avaliacao av join av.alunos als where av.aula = a and als.prontuario = :#{#aluno.prontuario})")
	public List<Aula> findWithNotEvaluated(@Param("aluno") Aluno aluno);

}
