package br.com.gostoudaaula.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import android.os.Parcel;
import android.os.Parcelable;

@Entity
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Professor extends Pessoa implements Parcelable {

	private Integer chapa;
	private List<Aula> aulas;

	public Professor() {
	}

	public Professor(Integer chapa) {
		this.chapa = chapa;
	}

	@OneToMany(mappedBy = "professor", cascade = CascadeType.PERSIST)
	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public Integer getChapa() {
		return chapa;
	}

	public void setChapa(Integer chapa) {
		this.chapa = chapa;
	}

	public static final Parcelable.Creator<Professor> CREATOR = new Parcelable.Creator<Professor>() {
		public Professor createFromParcel(Parcel in) {
			return new Professor(in);
		}

		public Professor[] newArray(int size) {
			return new Professor[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(chapa);
		dest.writeTypedList(aulas);
	}

	private Professor(Parcel parcel) {
		chapa = parcel.readInt();
		aulas = new ArrayList<>();
		parcel.readTypedList(aulas, Aula.CREATOR);
	}

}
