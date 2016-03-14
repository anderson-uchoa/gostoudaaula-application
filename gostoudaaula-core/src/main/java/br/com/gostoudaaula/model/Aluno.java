package br.com.gostoudaaula.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import android.os.Parcel;
import android.os.Parcelable;

@Entity
@PrimaryKeyJoinColumn(name = "id_aluno")
public class Aluno extends Usuario implements Parcelable {

	private List<Aula> aulas;
	private List<Avaliacao> avaliacoes;

	public Aluno() {

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

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE })
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
		dest.writeLong(id);
		dest.writeString(nome);
		dest.writeString(sobrenome);
		dest.writeString(senha);
		dest.writeString(token);
		dest.writeTypedList(aulas);
		dest.writeTypedList(avaliacoes);
	}

	private Aluno(Parcel parcel) {
		id = parcel.readLong();
		nome = parcel.readString();
		sobrenome = parcel.readString();
		senha = parcel.readString();
		token = parcel.readString();
		aulas = new ArrayList<>();
		parcel.readTypedList(aulas, Aula.CREATOR);
		avaliacoes = new ArrayList<>();
		parcel.readTypedList(avaliacoes, Avaliacao.CREATOR);
	}

}
