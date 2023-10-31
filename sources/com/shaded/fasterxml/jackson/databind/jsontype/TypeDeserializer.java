package com.shaded.fasterxml.jackson.databind.jsontype;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import java.io.IOException;

public abstract class TypeDeserializer {
    public abstract Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    public abstract Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    public abstract Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    public abstract Object deserializeTypedFromScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    public abstract TypeDeserializer forProperty(BeanProperty beanProperty);

    public abstract Class<?> getDefaultImpl();

    public abstract String getPropertyName();

    public abstract TypeIdResolver getTypeIdResolver();

    public abstract JsonTypeInfo.C1433As getTypeInclusion();

    public TypeDeserializer() {
    }

    public static Object deserializeIfNatural(JsonParser jsonParser, DeserializationContext deserializationContext, JavaType javaType) throws IOException, JsonProcessingException {
        return deserializeIfNatural(jsonParser, deserializationContext, javaType.getRawClass());
    }

    public static Object deserializeIfNatural(JsonParser jsonParser, DeserializationContext deserializationContext, Class<?> cls) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Class<?> cls2 = cls;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == null) {
            return null;
        }
        switch (currentToken) {
            case VALUE_STRING:
                if (cls2.isAssignableFrom(String.class)) {
                    return jsonParser2.getText();
                }
                break;
            case VALUE_NUMBER_INT:
                if (cls2.isAssignableFrom(Integer.class)) {
                    return Integer.valueOf(jsonParser2.getIntValue());
                }
                break;
            case VALUE_NUMBER_FLOAT:
                if (cls2.isAssignableFrom(Double.class)) {
                    return Double.valueOf(jsonParser2.getDoubleValue());
                }
                break;
            case VALUE_TRUE:
                if (cls2.isAssignableFrom(Boolean.class)) {
                    return Boolean.TRUE;
                }
                break;
            case VALUE_FALSE:
                if (cls2.isAssignableFrom(Boolean.class)) {
                    return Boolean.FALSE;
                }
                break;
        }
        return null;
    }
}
