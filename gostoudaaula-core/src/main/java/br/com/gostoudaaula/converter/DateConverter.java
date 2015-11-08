package br.com.gostoudaaula.converter;

import java.sql.Date;
import java.util.TimeZone;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDate, Date> {

	public Date convertToDatabaseColumn(LocalDate attribute) {
		return new Date(attribute.toDateTimeAtStartOfDay().getMillis());
	}

	public LocalDate convertToEntityAttribute(Date dbData) {
		return new LocalDate(dbData.getTime(),
				DateTimeZone.forTimeZone(TimeZone.getDefault()));
	}

}
