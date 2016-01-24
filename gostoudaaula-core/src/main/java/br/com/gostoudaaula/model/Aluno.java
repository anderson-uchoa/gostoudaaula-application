package br.com.gostoudaaula.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import android.os.Parcel;
import android.os.Parcelable;

@Entity
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Aluno extends Pessoa implements Parcelable {

	private int prontuario;
	private String senha;
	private List<Aula> aulas;
	private List<Avaliacao> avaliacoes;

	public Aluno() {

	}

	public Aluno(Integer prontuario) {
		this.prontuario = prontuario;
	}

	@Min(1)
	public int getProntuario() {
		return prontuario;
	}

	public void setProntuario(Integer prontuario) {
		this.prontuario = prontuario;
	}

	@NotEmpty
	@Length(min = 5)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "alunos_aula", joinColumns = { @JoinColumn(name = "id_aluno") }, inverseJoinColumns = {
			@JoinColumn(name = "id_aula") })
	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "alunos_avaliacao", joinColumns = { @JoinColumn(name = "id_aluno") }, inverseJoinColumns = {
			@JoinColumn(name = "id_avaliacao") })
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public static final Parcelable.Creator<Aluno> CREATOR = new Parcelable.Creator<Aluno>() {
		public Aluno createFromParcel(Parcel in) {
			return new Aluno(in);
		}

		public Aluno[] newArray(int size) {
			return new Aluno[size];
		}
	};

	@Override
	public int describeContents() {

		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(prontuario);
		dest.writeString(senha);
		dest.writeTypedList(aulas);
		dest.writeTypedList(avaliacoes);
	}

	private Aluno(Parcel parcel) {
		this.prontuario = parcel.readInt();
		this.senha = parcel.readString();
		aulas = new ArrayList<>();
		parcel.readTypedList(aulas, Aula.CREATOR);
		avaliacoes = new ArrayList<>();
		parcel.readTypedList(avaliacoes, Avaliacao.CREATOR);
	}

}
