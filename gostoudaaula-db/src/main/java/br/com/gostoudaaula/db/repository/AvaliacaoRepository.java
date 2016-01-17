package br.com.gostoudaaula.db.repository;

import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import br.com.gostoudaaula.model.Avaliacao;

public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Long> {

	public Avaliacao findByData(LocalDate data);

}
