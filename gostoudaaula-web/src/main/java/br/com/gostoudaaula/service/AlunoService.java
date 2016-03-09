package br.com.gostoudaaula.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.gostoudaaula.db.repository.AlunoRepository;
import br.com.gostoudaaula.model.Aluno;

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
		return repository.findOne(aluno.getId());
	}

	public List<Aluno> getLista() {
		return (List<Aluno>) repository.findAll();
	}

	public boolean autentica(Aluno aluno) {
		return repository.autentica(aluno);
	}

	public boolean tokenValido(String token) {
		return repository.autenticaToken(token);
	}

	public Aluno retorna(String token) {
		return repository.findByToken(token);
	}

	public void geraNovoToken(Aluno aluno) {
		Aluno retornado = retorna(aluno.getToken());
		geraToken(retornado);
	}

	public void atualizaToken(Aluno aluno) {
		geraToken(aluno);
		aluno.setToken(aluno.criptografa());
	}

	private void geraToken(Aluno retornado) {
		retornado.novoToken();
		salva(retornado);
	}
}
