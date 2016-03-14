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
@PrimaryKeyJoinColumn(name = "id_professor")
public class Professor extends Usuario implements Parcelable {

	private List<Aula> aulas;

	public Professor() {
	}

	@OneToMany(mappedBy = "professor", cascade = CascadeType.PERSIST)
	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
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
		dest.writeLong(id);
		dest.writeString(nome);
		dest.writeString(sobrenome);
		dest.writeString(senha);
		dest.writeString(token);
		dest.writeTypedList(aulas);
	}

	private Professor(Parcel parcel) {
		id = parcel.readLong();
		nome = parcel.readString();
		sobrenome = parcel.readString();
		senha = parcel.readString();
		token = parcel.readString();
		aulas = new ArrayList<>();
		parcel.readTypedList(aulas, Aula.CREATOR);
	}

}
