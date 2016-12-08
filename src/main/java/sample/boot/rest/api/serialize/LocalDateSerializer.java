package sample.boot.rest.api.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by YamashiroRyota on 15/09/14.
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void serialize(final LocalDate value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException {
        jgen.writeString(value.format(this.formatter));
    }
}
