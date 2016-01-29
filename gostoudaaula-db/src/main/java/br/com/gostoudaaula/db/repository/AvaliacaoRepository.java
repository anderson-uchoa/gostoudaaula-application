package br.com.gostoudaaula.db.repository;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Questoes;

public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Long> {

	public Avaliacao findByData(LocalDate data);

	@Query("SELECT q FROM Questoes q JOIN q.projetos p JOIN p.avaliacao a WHERE a.id = :#{#avaliacao.id}")
	public List<Questoes> todasAsQuestoesDeUmaAvaliacao(@Param("avaliacao") Avaliacao avaliacao);

	@Query("SELECT a FROM Avaliacao a WHERE a.id = :#{#avaliacao.id}")
	public Avaliacao retorna(@Param("avaliacao") Avaliacao avaliacao);
}
