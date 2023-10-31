package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsArrayTypeSerializer extends TypeSerializerBase {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsArrayTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        super(typeIdResolver, beanProperty);
    }

    public AsArrayTypeSerializer forProperty(BeanProperty beanProperty) {
        AsArrayTypeSerializer asArrayTypeSerializer;
        BeanProperty beanProperty2 = beanProperty;
        if (this._property == beanProperty2) {
            return this;
        }
        new AsArrayTypeSerializer(this._idResolver, beanProperty2);
        return asArrayTypeSerializer;
    }

    public JsonTypeInfo.C1433As getTypeInclusion() {
        return JsonTypeInfo.C1433As.WRAPPER_ARRAY;
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartArray();
        jsonGenerator2.writeString(idFromValue(obj));
        jsonGenerator2.writeStartObject();
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartArray();
        jsonGenerator2.writeString(idFromValueAndType(obj, cls));
        jsonGenerator2.writeStartObject();
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartArray();
        jsonGenerator2.writeString(idFromValue(obj));
        jsonGenerator2.writeStartArray();
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartArray();
        jsonGenerator2.writeString(idFromValueAndType(obj, cls));
        jsonGenerator2.writeStartArray();
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartArray();
        jsonGenerator2.writeString(idFromValue(obj));
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartArray();
        jsonGenerator2.writeString(idFromValueAndType(obj, cls));
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeEndObject();
        jsonGenerator2.writeEndArray();
    }

    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeEndArray();
        jsonGenerator2.writeEndArray();
    }

    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        jsonGenerator.writeEndArray();
    }

    public void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartArray();
        jsonGenerator2.writeString(str);
        jsonGenerator2.writeStartObject();
    }

    public void writeCustomTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartArray();
        jsonGenerator2.writeString(str);
        jsonGenerator2.writeStartArray();
    }

    public void writeCustomTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartArray();
        jsonGenerator2.writeString(str);
    }

    public void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        String str2 = str;
        writeTypeSuffixForObject(obj, jsonGenerator);
    }

    public void writeCustomTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        String str2 = str;
        writeTypeSuffixForArray(obj, jsonGenerator);
    }

    public void writeCustomTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        String str2 = str;
        writeTypeSuffixForScalar(obj, jsonGenerator);
    }
}
