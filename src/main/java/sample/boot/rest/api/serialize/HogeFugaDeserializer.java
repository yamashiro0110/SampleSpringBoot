package sample.boot.rest.api.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import sample.boot.rest.api.HogeController;

import java.io.IOException;

public class HogeFugaDeserializer extends JsonDeserializer<HogeController.HogeFuga> {

    @Override
    public HogeController.HogeFuga deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
        final JsonNode node = parser.getCodec().readTree(parser);
        return null;
    }
}
