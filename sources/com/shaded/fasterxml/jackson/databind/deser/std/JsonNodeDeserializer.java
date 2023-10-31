package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.node.ArrayNode;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

public class JsonNodeDeserializer extends BaseNodeDeserializer {
    private static final JsonNodeDeserializer instance;

    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return super.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }

    public /* bridge */ /* synthetic */ JsonNode getNullValue() {
        return super.getNullValue();
    }

    static {
        JsonNodeDeserializer jsonNodeDeserializer;
        new JsonNodeDeserializer();
        instance = jsonNodeDeserializer;
    }

    protected JsonNodeDeserializer() {
    }

    public static JsonDeserializer<? extends JsonNode> getDeserializer(Class<?> cls) {
        Class<?> cls2 = cls;
        if (cls2 == ObjectNode.class) {
            return ObjectDeserializer.getInstance();
        }
        if (cls2 == ArrayNode.class) {
            return ArrayDeserializer.getInstance();
        }
        return instance;
    }

    public JsonNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        switch (jsonParser2.getCurrentToken()) {
            case START_OBJECT:
                return deserializeObject(jsonParser2, deserializationContext2, deserializationContext2.getNodeFactory());
            case START_ARRAY:
                return deserializeArray(jsonParser2, deserializationContext2, deserializationContext2.getNodeFactory());
            default:
                return deserializeAny(jsonParser2, deserializationContext2, deserializationContext2.getNodeFactory());
        }
    }

    static final class ObjectDeserializer extends BaseNodeDeserializer {
        protected static final ObjectDeserializer _instance;
        private static final long serialVersionUID = 1;

        static {
            ObjectDeserializer objectDeserializer;
            new ObjectDeserializer();
            _instance = objectDeserializer;
        }

        protected ObjectDeserializer() {
        }

        public static ObjectDeserializer getInstance() {
            return _instance;
        }

        public ObjectNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (jsonParser2.getCurrentToken() == JsonToken.START_OBJECT) {
                JsonToken nextToken = jsonParser2.nextToken();
                return deserializeObject(jsonParser2, deserializationContext2, deserializationContext2.getNodeFactory());
            } else if (jsonParser2.getCurrentToken() == JsonToken.FIELD_NAME) {
                return deserializeObject(jsonParser2, deserializationContext2, deserializationContext2.getNodeFactory());
            } else {
                throw deserializationContext2.mappingException((Class<?>) ObjectNode.class);
            }
        }
    }

    static final class ArrayDeserializer extends BaseNodeDeserializer {
        protected static final ArrayDeserializer _instance;
        private static final long serialVersionUID = 1;

        static {
            ArrayDeserializer arrayDeserializer;
            new ArrayDeserializer();
            _instance = arrayDeserializer;
        }

        protected ArrayDeserializer() {
        }

        public static ArrayDeserializer getInstance() {
            return _instance;
        }

        public ArrayNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (jsonParser2.isExpectedStartArrayToken()) {
                return deserializeArray(jsonParser2, deserializationContext2, deserializationContext2.getNodeFactory());
            }
            throw deserializationContext2.mappingException((Class<?>) ArrayNode.class);
        }
    }
}
