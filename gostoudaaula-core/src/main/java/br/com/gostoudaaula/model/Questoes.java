package br.com.gostoudaaula.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Questoes implements Parcelable {

	private Long id;
	private String descricao;
	private List<Projeto> projetos;

	public Questoes() {

	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@ManyToMany(mappedBy = "questoes", cascade = CascadeType.PERSIST)
	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static final Parcelable.Creator<Questoes> CREATOR = new Parcelable.Creator<Questoes>() {
		public Questoes createFromParcel(Parcel in) {
			return new Questoes(in);
		}

		public Questoes[] newArray(int size) {
			return new Questoes[size];
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
		dest.writeTypedList(projetos);
	}

	private Questoes(Parcel parcel) {
		id = parcel.readLong();
		descricao = parcel.readString();
		parcel.readTypedList(projetos, Projeto.CREATOR);
	}

}
