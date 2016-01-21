package br.com.gostoudaaula.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Projeto implements Parcelable {

	private Long id;
	private String descricao;
	private Avaliacao avaliacao;
	private List<Questoes> questoes;

	public Projeto() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@OneToOne(mappedBy = "projeto", cascade = CascadeType.PERSIST)
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "questoes_projeto", joinColumns = { @JoinColumn(name = "id_projeto") }, inverseJoinColumns = {
			@JoinColumn(name = "id_questoes") })
	public List<Questoes> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questoes> questoes) {
		this.questoes = questoes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static final Parcelable.Creator<Projeto> CREATOR = new Parcelable.Creator<Projeto>() {
		public Projeto createFromParcel(Parcel in) {
			return new Projeto(in);
		}

		public Projeto[] newArray(int size) {
			return new Projeto[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(descricao);
		dest.writeParcelable(avaliacao, flags);
		dest.writeTypedList(questoes);
	}

	private Projeto(Parcel parcel) {
		id = parcel.readLong();
		descricao = parcel.readString();
		avaliacao = parcel.readParcelable(Avaliacao.class.getClassLoader());
		parcel.readTypedList(questoes, Questoes.CREATOR);
	}

}
