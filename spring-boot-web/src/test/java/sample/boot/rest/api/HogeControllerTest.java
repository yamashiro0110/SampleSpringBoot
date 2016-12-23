package sample.boot.rest.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Test;

import java.io.IOException;

import static sample.boot.rest.api.HogeController.*;

public class HogeControllerTest {

    @Test
    public void hogefuga() throws IOException {
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(HogeRoot.class, new HogeRootDeserializer());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        final String json = mapper.writeValueAsString(new HogeRoot());
        mapper.readValue(json, HogeRoot.class);
    }

    private static class HogeRootDeserializer extends JsonDeserializer<HogeController.HogeRoot> {

        private SimpleModule module() {
            final SimpleModule module = new SimpleModule();
            module.addDeserializer(HogeFuga.class, new HogeFugaDeserializer());
            return module;
        }

        private ObjectMapper mapper() {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(module());
            return mapper;
        }

        @Override
        public HogeRoot deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
            final ObjectMapper mapper = mapper();
            final JsonNode node = parser.getCodec().readTree(parser);
            return mapper.readValue(node.toString(), HogeRoot.class);
        }
    }

    private static class HogeFugaDeserializer extends JsonDeserializer<HogeFuga> {

        private ObjectMapper mapper() {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(module());
            return mapper;
        }

        private SimpleModule module() {
            final SimpleModule module = new SimpleModule();
            module.addDeserializer(Hoge.class, new HogeDeserializer());
            module.addDeserializer(Fuga.class, new FugaDeserializer());
            return module;
        }

        @Override
        public HogeFuga deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
            final ObjectMapper mapper = mapper();
            final JsonNode node = parser.getCodec().readTree(parser);
            return mapper.readValue(node.toString(), HogeFuga.class);
        }
    }

    private static class HogeDeserializer extends JsonDeserializer<HogeController.Hoge> {
        private ObjectMapper mapper = new ObjectMapper();

        @Override
        public Hoge deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
            final JsonNode node = parser.getCodec().readTree(parser);

            if (node.isArray() && !node.elements().hasNext()) {
                return new Hoge();
            }

            return this.mapper.readValue(node.toString(), Hoge.class);
        }
    }

    private static class FugaDeserializer extends JsonDeserializer<HogeController.Fuga> {
        private ObjectMapper mapper = new ObjectMapper();

        @Override
        public Fuga deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
            final JsonNode node = parser.getCodec().readTree(parser);

            if (node.isArray() && !node.elements().hasNext()) {
                return new Fuga();
            }

            return this.mapper.readValue(node.toString(), Fuga.class);
        }
    }

}
