package br.com.gostoudaaula.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
public class Respostas implements Parcelable {

	private Long id;
	private Integer resposta;
	private LocalDate data;
	private Questoes questoes;
	private Avaliacao avaliacao;

	public Respostas() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Min(0)
	@Max(10)
	public Integer getResposta() {
		return resposta;
	}

	public void setResposta(Integer resposta) {
		this.resposta = resposta;
	}

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Convert(converter = DateConverter.class)
	@Column(name = "data_resposta")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@NotNull
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_questoes")
	public Questoes getQuestoes() {
		return questoes;
	}

	public void setQuestoes(Questoes questoes) {
		this.questoes = questoes;
	}

	@NotNull
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_avaliacao")
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public static final Parcelable.Creator<Respostas> CREATOR = new Parcelable.Creator<Respostas>() {
		public Respostas createFromParcel(Parcel in) {
			return new Respostas(in);
		}

		public Respostas[] newArray(int size) {
			return new Respostas[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeInt(resposta);
		dest.writeSerializable(data);
		dest.writeParcelable(questoes, flags);
		dest.writeParcelable(avaliacao, flags);
	}

	private Respostas(Parcel parcel) {
		id = parcel.readLong();
		resposta = parcel.readInt();
		data = (LocalDate) parcel.readSerializable();
		parcel.readParcelable(Questoes.class.getClassLoader());
		parcel.readParcelable(Avaliacao.class.getClassLoader());
	}

}
