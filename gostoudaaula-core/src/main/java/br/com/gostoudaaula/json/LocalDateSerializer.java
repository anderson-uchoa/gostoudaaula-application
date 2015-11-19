package br.com.gostoudaaula.json;

import java.io.IOException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

    public LocalDateSerializer() {
        this(LocalDate.class);
    }

    protected LocalDateSerializer(Class<LocalDate> t) {
        super(t);
    }

    private static final long serialVersionUID = 1L;

    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        gen.writeStringField("data", value.toString(fmt));

        gen.writeEndObject();
    }
}