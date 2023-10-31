package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {
    private static final long serialVersionUID = 1;

    /* access modifiers changed from: protected */
    public abstract T _deserialize(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FromStringDeserializer(Class<?> cls) {
        super(cls);
    }

    public final T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        String valueAsString = jsonParser2.getValueAsString();
        if (valueAsString != null) {
            if (valueAsString.length() != 0) {
                String trim = valueAsString.trim();
                String str = trim;
                if (trim.length() != 0) {
                    try {
                        T _deserialize = _deserialize(str, deserializationContext2);
                        if (_deserialize != null) {
                            return _deserialize;
                        }
                    } catch (IllegalArgumentException e) {
                        IllegalArgumentException illegalArgumentException = e;
                    }
                    throw deserializationContext2.weirdStringException(str, this._valueClass, "not a valid textual representation");
                }
            }
            return null;
        } else if (jsonParser2.getCurrentToken() == JsonToken.VALUE_EMBEDDED_OBJECT) {
            T embeddedObject = jsonParser2.getEmbeddedObject();
            if (embeddedObject == null) {
                return null;
            }
            if (this._valueClass.isAssignableFrom(embeddedObject.getClass())) {
                return embeddedObject;
            }
            return _deserializeEmbedded(embeddedObject, deserializationContext2);
        } else {
            throw deserializationContext2.mappingException((Class<?>) this._valueClass);
        }
    }

    /* access modifiers changed from: protected */
    public T _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StringBuilder sb;
        new StringBuilder();
        throw deserializationContext.mappingException(sb.append("Don't know how to convert embedded Object of type ").append(obj.getClass().getName()).append(" into ").append(this._valueClass.getName()).toString());
    }
}
