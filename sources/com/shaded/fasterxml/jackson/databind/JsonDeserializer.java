package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Collection;

public abstract class JsonDeserializer<T> {
    public abstract T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    public JsonDeserializer() {
    }

    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, T t) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Throwable th2 = th;
        new StringBuilder();
        new UnsupportedOperationException(sb.append("Can not update object of type ").append(t.getClass().getName()).append(" (by deserializer of type ").append(getClass().getName()).append(")").toString());
        throw th2;
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    public JsonDeserializer<T> unwrappingDeserializer(NameTransformer nameTransformer) {
        NameTransformer nameTransformer2 = nameTransformer;
        return this;
    }

    public JsonDeserializer<?> replaceDelegatee(JsonDeserializer<?> jsonDeserializer) {
        Throwable th;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public T getNullValue() {
        return null;
    }

    public T getEmptyValue() {
        return getNullValue();
    }

    public Collection<Object> getKnownPropertyNames() {
        return null;
    }

    public boolean isCachable() {
        return false;
    }

    public ObjectIdReader getObjectIdReader() {
        return null;
    }

    public JsonDeserializer<?> getDelegatee() {
        return null;
    }

    public static abstract class None extends JsonDeserializer<Object> {
        private None() {
        }
    }
}
