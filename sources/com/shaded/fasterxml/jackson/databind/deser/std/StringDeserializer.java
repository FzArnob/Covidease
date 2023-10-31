package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.Base64Variants;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

@JacksonStdImpl
public final class StringDeserializer extends StdScalarDeserializer<String> {
    public static final StringDeserializer instance;
    private static final long serialVersionUID = 1;

    static {
        StringDeserializer stringDeserializer;
        new StringDeserializer();
        instance = stringDeserializer;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StringDeserializer() {
        super((Class<?>) String.class);
    }

    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        String valueAsString = jsonParser2.getValueAsString();
        if (valueAsString != null) {
            return valueAsString;
        }
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            Object embeddedObject = jsonParser2.getEmbeddedObject();
            if (embeddedObject == null) {
                return null;
            }
            if (embeddedObject instanceof byte[]) {
                return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false);
            }
            return embeddedObject.toString();
        }
        throw deserializationContext2.mappingException(this._valueClass, currentToken);
    }

    public String deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        return deserialize(jsonParser, deserializationContext);
    }
}
