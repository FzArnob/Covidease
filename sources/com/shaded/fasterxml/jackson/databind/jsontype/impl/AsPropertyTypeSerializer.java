package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsPropertyTypeSerializer extends AsArrayTypeSerializer {
    protected final String _typePropertyName;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsPropertyTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty, String str) {
        super(typeIdResolver, beanProperty);
        this._typePropertyName = str;
    }

    public AsPropertyTypeSerializer forProperty(BeanProperty beanProperty) {
        AsPropertyTypeSerializer asPropertyTypeSerializer;
        BeanProperty beanProperty2 = beanProperty;
        if (this._property == beanProperty2) {
            return this;
        }
        new AsPropertyTypeSerializer(this._idResolver, beanProperty2, this._typePropertyName);
        return asPropertyTypeSerializer;
    }

    public String getPropertyName() {
        return this._typePropertyName;
    }

    public JsonTypeInfo.C1433As getTypeInclusion() {
        return JsonTypeInfo.C1433As.PROPERTY;
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartObject();
        jsonGenerator2.writeStringField(this._typePropertyName, idFromValue(obj));
    }

    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartObject();
        jsonGenerator2.writeStringField(this._typePropertyName, idFromValueAndType(obj, cls));
    }

    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        jsonGenerator.writeEndObject();
    }

    public void writeCustomTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeStartObject();
        jsonGenerator2.writeStringField(this._typePropertyName, str);
    }

    public void writeCustomTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator, String str) throws IOException, JsonProcessingException {
        Object obj2 = obj;
        String str2 = str;
        jsonGenerator.writeEndObject();
    }
}
