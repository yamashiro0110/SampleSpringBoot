package sample.boot.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Test;

import java.io.IOException;

import static sample.boot.api.HogeController.*;

public class HogeControllerTest {

    @Test
    public void hogefuga() throws IOException {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(HogeRoot.class, new HogeRootDeserializer());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        String json = mapper.writeValueAsString(new HogeRoot());
        mapper.readValue(json, HogeRoot.class);
    }

    private static class HogeRootDeserializer extends JsonDeserializer<HogeController.HogeRoot> {

        private SimpleModule module() {
            SimpleModule module = new SimpleModule();
            module.addDeserializer(HogeFuga.class, new HogeFugaDeserializer());
            return module;
        }

        private ObjectMapper mapper() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(module());
            return mapper;
        }

        @Override
        public HogeRoot deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
            ObjectMapper mapper = mapper();
            JsonNode node = parser.getCodec().readTree(parser);
            return mapper.readValue(node.toString(), HogeRoot.class);
        }
    }

    private static class HogeFugaDeserializer extends JsonDeserializer<HogeFuga> {

        private ObjectMapper mapper() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(module());
            return mapper;
        }

        private SimpleModule module() {
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Hoge.class, new HogeDeserializer());
            module.addDeserializer(Fuga.class, new FugaDeserializer());
            return module;
        }

        @Override
        public HogeFuga deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
            ObjectMapper mapper = mapper();
            JsonNode node = parser.getCodec().readTree(parser);
            return mapper.readValue(node.toString(), HogeFuga.class);
        }
    }

    private static class HogeDeserializer extends JsonDeserializer<HogeController.Hoge> {
        private ObjectMapper mapper = new ObjectMapper();

        @Override
        public Hoge deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
            JsonNode node = parser.getCodec().readTree(parser);

            if (node.isArray() && !node.elements().hasNext()) {
                return new Hoge();
            }

            return mapper.readValue(node.toString(), Hoge.class);
        }
    }

    private static class FugaDeserializer extends JsonDeserializer<HogeController.Fuga> {
        private ObjectMapper mapper = new ObjectMapper();

        @Override
        public Fuga deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
            JsonNode node = parser.getCodec().readTree(parser);

            if (node.isArray() && !node.elements().hasNext()) {
                return new Fuga();
            }

            return mapper.readValue(node.toString(), Fuga.class);
        }
    }

}
