package br.com.gostoudaaula.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.jasypt.digest.StandardStringDigester;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.joda.time.LocalDateTime;

import br.com.gostoudaaula.utils.KeyUtils;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

	protected Long id;
	protected String nome;
	protected String sobrenome;
	protected String senha;
	protected String token;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	@Size(min = 2)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty
	@Size(min = 2)
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@NotEmpty
	@Length(min = 5)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void adicionaTokenDefault() {
		StandardStringDigester digester = new StandardStringDigester();
		digester.setAlgorithm("SHA-1");
		digester.setIterations(2000);
		this.token = digester.digest(this.nome + this.senha + LocalDateTime.now().toString());
	}

	public String criptografa() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(KeyUtils.PRIVADA.toString());
		return encryptor.encrypt(token);
	}

	public String decriptografa(String token) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(KeyUtils.PRIVADA.toString());

		return encryptor.decrypt(token);
	}

	public void novoToken() {
		StandardStringDigester digester = new StandardStringDigester();
		digester.setAlgorithm("SHA-1");
		digester.setIterations(2000);
		this.token = digester.digest(this.id + this.nome + this.senha);
	}

}
