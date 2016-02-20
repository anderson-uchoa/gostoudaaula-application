package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.repository.AlunoRepository;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Token;

@Service
public class AlunoService {

	private AlunoRepository repository;

	@Inject
	public AlunoService(AlunoRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public void salva(Aluno aluno) {
		repository.save(aluno);
	}

	public Aluno retorna(Aluno aluno) {
		return repository.findByProntuario(aluno.getProntuario());
	}

	public List<Aluno> getLista() {
		return (List<Aluno>) repository.findAll();
	}

	public boolean autentica(Aluno aluno) {
		return repository.autentica(aluno);
	}

	public boolean tokenValido(Token token) {
		return repository.autenticaToken(token);
	}

	public Aluno retorna(Token token) {
		return repository.retornaPorToken(token);
	}


}
