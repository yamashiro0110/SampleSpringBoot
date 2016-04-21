package sample.boot.api.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import sample.boot.api.HogeController;

import java.io.IOException;

public class HogeFugaDeserializer extends JsonDeserializer<HogeController.HogeFuga> {

    @Override
    public HogeController.HogeFuga deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        return null;
    }
}
