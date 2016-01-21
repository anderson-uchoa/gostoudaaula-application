package br.com.gostoudaaula.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Turma implements Parcelable {

	private Long id;
	private String descricao;

	public Turma() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static final Parcelable.Creator<Turma> CREATOR = new Parcelable.Creator<Turma>() {
		public Turma createFromParcel(Parcel in) {
			return new Turma(in);
		}

		public Turma[] newArray(int size) {
			return new Turma[size];
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
	}

	private Turma(Parcel parcel) {
		id = parcel.readLong();
		descricao = parcel.readString();
	}

}
