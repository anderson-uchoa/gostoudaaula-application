package br.com.gostoudaaula.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Disciplina implements Parcelable {

	private Long id;
	private String descricao;

	public Disciplina() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static final Parcelable.Creator<Disciplina> CREATOR = new Parcelable.Creator<Disciplina>() {
		public Disciplina createFromParcel(Parcel in) {
			return new Disciplina(in);
		}

		public Disciplina[] newArray(int size) {
			return new Disciplina[size];
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

	private Disciplina(Parcel parcel) {
		id = parcel.readLong();
		descricao = parcel.readString();
	}

}
