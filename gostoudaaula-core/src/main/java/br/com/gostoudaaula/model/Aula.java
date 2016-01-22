package br.com.gostoudaaula.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import android.os.Parcel;
import android.os.Parcelable;
import br.com.gostoudaaula.converter.DateConverter;
import br.com.gostoudaaula.json.LocalDateDeserializer;
import br.com.gostoudaaula.json.LocalDateSerializer;

@Entity
public class Aula implements Parcelable {

	private Long id;
	private Professor professor;
	private PeriodoLetivo periodoLetivo;
	private LocalDate data;
	private List<Aluno> alunos;

	public Aula() {

	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_professor")
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_periodo_letivo")
	public PeriodoLetivo getPeriodoLetivo() {
		return periodoLetivo;
	}

	public void setPeriodoLetivo(PeriodoLetivo periodoLetivo) {
		this.periodoLetivo = periodoLetivo;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Convert(converter = DateConverter.class)
	@Column(name = "data_aula")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@ManyToMany(mappedBy = "aulas", cascade = CascadeType.PERSIST)
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static final Parcelable.Creator<Aula> CREATOR = new Parcelable.Creator<Aula>() {
		public Aula createFromParcel(Parcel in) {
			return new Aula(in);
		}

		public Aula[] newArray(int size) {
			return new Aula[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeParcelable(professor, flags);
		dest.writeParcelable(periodoLetivo, flags);
		dest.writeSerializable(data);
		dest.writeTypedList(alunos);
	}

	private Aula(Parcel parcel) {
		id = parcel.readLong();
		professor = parcel.readParcelable(Professor.class.getClassLoader());
		periodoLetivo = parcel.readParcelable(PeriodoLetivo.class.getClassLoader());
		data = (LocalDate) parcel.readSerializable();
		alunos = new ArrayList<>();
		parcel.readTypedList(alunos, Aluno.CREATOR);
	}

}
