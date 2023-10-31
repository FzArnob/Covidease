package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;

@JacksonStdImpl
public class ClassDeserializer extends StdScalarDeserializer<Class<?>> {
    public static final ClassDeserializer instance;
    private static final long serialVersionUID = 1;

    static {
        ClassDeserializer classDeserializer;
        new ClassDeserializer();
        instance = classDeserializer;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClassDeserializer() {
        super((Class<?>) Class.class);
    }

    public Class<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) {
            try {
                return deserializationContext2.findClass(jsonParser2.getText().trim());
            } catch (Exception e) {
                throw deserializationContext2.instantiationException((Class<?>) this._valueClass, ClassUtil.getRootCause(e));
            }
        } else {
            throw deserializationContext2.mappingException(this._valueClass, currentToken);
        }
    }
}
