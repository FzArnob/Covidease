package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

public class NullifyingDeserializer extends StdDeserializer<Object> {
    public static final NullifyingDeserializer instance;
    private static final long serialVersionUID = 1;

    static {
        NullifyingDeserializer nullifyingDeserializer;
        new NullifyingDeserializer();
        instance = nullifyingDeserializer;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NullifyingDeserializer() {
        super((Class<?>) Object.class);
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonParser skipChildren = jsonParser.skipChildren();
        return null;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        switch (jsonParser2.getCurrentToken()) {
            case START_ARRAY:
            case START_OBJECT:
            case FIELD_NAME:
                return typeDeserializer2.deserializeTypedFromAny(jsonParser2, deserializationContext2);
            default:
                return null;
        }
    }
}
