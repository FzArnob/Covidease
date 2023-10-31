package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

public abstract class JsonSerializer<T> implements JsonFormatVisitable {
    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException;

    public JsonSerializer() {
    }

    public JsonSerializer<T> unwrappingSerializer(NameTransformer nameTransformer) {
        NameTransformer nameTransformer2 = nameTransformer;
        return this;
    }

    public JsonSerializer<T> replaceDelegatee(JsonSerializer<?> jsonSerializer) {
        Throwable th;
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        T t2 = t;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        TypeSerializer typeSerializer2 = typeSerializer;
        Class<?> handledType = handledType();
        if (handledType == null) {
            handledType = t2.getClass();
        }
        Throwable th2 = th;
        new StringBuilder();
        new UnsupportedOperationException(sb.append("Type id handling not implemented for type ").append(handledType.getName()).toString());
        throw th2;
    }

    public Class<T> handledType() {
        return null;
    }

    public boolean isEmpty(T t) {
        return t == null;
    }

    public boolean usesObjectId() {
        return false;
    }

    public boolean isUnwrappingSerializer() {
        return false;
    }

    public JsonSerializer<?> getDelegatee() {
        return null;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        JavaType javaType2 = javaType;
        if (jsonFormatVisitorWrapper2 != null) {
            JsonAnyFormatVisitor expectAnyFormat = jsonFormatVisitorWrapper2.expectAnyFormat(javaType2);
        }
    }

    public static abstract class None extends JsonSerializer<Object> {
        public None() {
        }
    }
}
