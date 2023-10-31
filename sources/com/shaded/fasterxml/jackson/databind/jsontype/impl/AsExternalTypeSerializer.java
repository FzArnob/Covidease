package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsExternalTypeSerializer extends TypeSerializerBase {
    protected final String _typePropertyName;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsExternalTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty, String str) {
        super(typeIdResolver, beanProperty);
        this._typePropertyName = str;
    }

    public AsExternalTypeSerializer forProperty(BeanProperty beanProperty) {
        AsExternalTypeSerializer asExternalTypeSerializer;
        BeanProperty beanProperty2 = beanProperty;
        if (this._property == beanProperty2) {
            return this;
        }
        new AsExternalTypeSerializer(this._idResolver, beanProperty2, this._typePropertyName);
        return asExternalTypeSerializer;
    }

    public String getPropertyName() {
        return this._typePropertyName;
    }

    public JsonTypeInfo.C1433As getTypeInclusion() {
        return JsonTypeInfo.C1433As.EXTERNAL_PROPERTY;
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writeObjectPrefix(obj, jsonGenerator);
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        Class<?> cls2 = cls;
        _writeObjectPrefix(obj, jsonGenerator);
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writeArrayPrefix(obj, jsonGenerator);
    }

    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        Class<?> cls2 = cls;
        _writeArrayPrefix(obj, jsonGenerator);
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writeScalarPrefix(obj, jsonGenerator);
    }

    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        Class<?> cls2 = cls;
        _writeScalarPrefix(obj, jsonGenerator);
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        _writeObjectSuffix(obj2, jsonGenerator, idFromValue(obj2));
    }

    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        _writeArraySuffix(obj2, jsonGenerator, idFromValue(obj2));
    }

    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        _writeScalarSuffix(obj2, jsonGenerator, idFromValue(obj2));
    }

    public void writeCustomTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        String str2 = str;
        _writeScalarPrefix(obj, jsonGenerator);
    }

    public void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        String str2 = str;
        _writeObjectPrefix(obj, jsonGenerator);
    }

    public void writeCustomTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        String str2 = str;
        _writeArrayPrefix(obj, jsonGenerator);
    }

    public void writeCustomTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        _writeScalarSuffix(obj, jsonGenerator, str);
    }

    public void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        _writeObjectSuffix(obj, jsonGenerator, str);
    }

    public void writeCustomTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        _writeArraySuffix(obj, jsonGenerator, str);
    }

    /* access modifiers changed from: protected */
    public final void _writeScalarPrefix(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
    }

    /* access modifiers changed from: protected */
    public final void _writeObjectPrefix(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        jsonGenerator.writeStartObject();
    }

    /* access modifiers changed from: protected */
    public final void _writeArrayPrefix(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        jsonGenerator.writeStartArray();
    }

    /* access modifiers changed from: protected */
    public final void _writeScalarSuffix(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        jsonGenerator.writeStringField(this._typePropertyName, str);
    }

    /* access modifiers changed from: protected */
    public final void _writeObjectSuffix(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeEndObject();
        jsonGenerator2.writeStringField(this._typePropertyName, str);
    }

    /* access modifiers changed from: protected */
    public final void _writeArraySuffix(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeEndArray();
        jsonGenerator2.writeStringField(this._typePropertyName, str);
    }
}
