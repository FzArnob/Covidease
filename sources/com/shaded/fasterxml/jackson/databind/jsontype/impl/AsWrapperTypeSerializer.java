package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsWrapperTypeSerializer extends TypeSerializerBase {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsWrapperTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        super(typeIdResolver, beanProperty);
    }

    public AsWrapperTypeSerializer forProperty(BeanProperty beanProperty) {
        AsWrapperTypeSerializer asWrapperTypeSerializer;
        BeanProperty beanProperty2 = beanProperty;
        if (this._property == beanProperty2) {
            return this;
        }
        new AsWrapperTypeSerializer(this._idResolver, beanProperty2);
        return asWrapperTypeSerializer;
    }

    public JsonTypeInfo.C1433As getTypeInclusion() {
        return JsonTypeInfo.C1433As.WRAPPER_OBJECT;
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartObject();
        jsonGenerator2.writeObjectFieldStart(idFromValue(obj));
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartObject();
        jsonGenerator2.writeObjectFieldStart(idFromValueAndType(obj, cls));
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartObject();
        jsonGenerator2.writeArrayFieldStart(idFromValue(obj));
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartObject();
        jsonGenerator2.writeArrayFieldStart(idFromValueAndType(obj, cls));
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartObject();
        jsonGenerator2.writeFieldName(idFromValue(obj));
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartObject();
        jsonGenerator2.writeFieldName(idFromValueAndType(obj, cls));
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeEndObject();
        jsonGenerator2.writeEndObject();
    }

    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeEndArray();
        jsonGenerator2.writeEndObject();
    }

    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        jsonGenerator.writeEndObject();
    }

    public void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartObject();
        jsonGenerator2.writeObjectFieldStart(str);
    }

    public void writeCustomTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartObject();
        jsonGenerator2.writeArrayFieldStart(str);
    }

    public void writeCustomTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartObject();
        jsonGenerator2.writeFieldName(str);
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
