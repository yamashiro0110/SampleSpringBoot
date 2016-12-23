package sample.boot.rest.api.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by YamashiroRyota on 15/09/14.
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
        final ObjectCodec objectCodec = jp.getCodec();
        final TextNode node = objectCodec.readTree(jp);
        final String date = node.textValue();
        return LocalDate.parse(date);
    }
}
