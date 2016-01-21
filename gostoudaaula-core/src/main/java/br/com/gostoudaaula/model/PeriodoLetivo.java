package br.com.gostoudaaula.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import android.os.Parcel;
import android.os.Parcelable;

@Entity
@Table(name = "periodo_letivo", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "ano", "semestre", "id_turma", "id_disciplina" }) })
public class PeriodoLetivo implements Parcelable {

	private Long id;
	private Integer ano;
	private Integer semestre;
	private List<Aula> aulas;
	private Turma turma;
	private Disciplina disciplina;

	public PeriodoLetivo() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	@OneToMany(mappedBy = "periodoLetivo", cascade = CascadeType.PERSIST)
	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_turma")
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_disciplina")
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static final Parcelable.Creator<PeriodoLetivo> CREATOR = new Parcelable.Creator<PeriodoLetivo>() {
		public PeriodoLetivo createFromParcel(Parcel in) {
			return new PeriodoLetivo(in);
		}

		public PeriodoLetivo[] newArray(int size) {
			return new PeriodoLetivo[size];
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeInt(ano);
		dest.writeInt(semestre);
		dest.writeTypedList(aulas);
		dest.writeParcelable(turma, flags);
		dest.writeParcelable(disciplina, flags);
	}

	private PeriodoLetivo(Parcel parcel) {
		id = parcel.readLong();
		ano = parcel.readInt();
		semestre = parcel.readInt();
		parcel.readTypedList(aulas, Aula.CREATOR);
		turma = parcel.readParcelable(Turma.class.getClassLoader());
		disciplina = parcel.readParcelable(Disciplina.class.getClassLoader());
	}
}
