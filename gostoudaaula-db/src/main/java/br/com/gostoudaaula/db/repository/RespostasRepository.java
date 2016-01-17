package br.com.gostoudaaula.db.repository;

import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import br.com.gostoudaaula.model.Respostas;

public interface RespostasRepository extends CrudRepository<Respostas, Long>{

	Respostas findByData(LocalDate localDate);

}
