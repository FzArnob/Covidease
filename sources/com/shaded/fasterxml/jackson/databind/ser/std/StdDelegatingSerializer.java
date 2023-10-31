package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.lang.reflect.Type;

public class StdDelegatingSerializer extends StdSerializer<Object> implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable, SchemaAware {
    protected final Converter<Object, ?> _converter;
    protected final JsonSerializer<Object> _delegateSerializer;
    protected final JavaType _delegateType;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StdDelegatingSerializer(Converter<?, ?> converter) {
        super(Object.class);
        this._converter = converter;
        this._delegateType = null;
        this._delegateSerializer = null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public <T> StdDelegatingSerializer(Class<T> cls, Converter<T, ?> converter) {
        super(cls, false);
        this._converter = converter;
        this._delegateType = null;
        this._delegateSerializer = null;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StdDelegatingSerializer(com.shaded.fasterxml.jackson.databind.util.Converter<java.lang.Object, ?> r7, com.shaded.fasterxml.jackson.databind.JavaType r8, com.shaded.fasterxml.jackson.databind.JsonSerializer<?> r9) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r0
            r5 = r2
            r4.<init>((com.shaded.fasterxml.jackson.databind.JavaType) r5)
            r4 = r0
            r5 = r1
            r4._converter = r5
            r4 = r0
            r5 = r2
            r4._delegateType = r5
            r4 = r0
            r5 = r3
            r4._delegateSerializer = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer.<init>(com.shaded.fasterxml.jackson.databind.util.Converter, com.shaded.fasterxml.jackson.databind.JavaType, com.shaded.fasterxml.jackson.databind.JsonSerializer):void");
    }

    /* access modifiers changed from: protected */
    public StdDelegatingSerializer withDelegate(Converter<Object, ?> converter, JavaType javaType, JsonSerializer<?> jsonSerializer) {
        StdDelegatingSerializer stdDelegatingSerializer;
        Throwable th;
        StringBuilder sb;
        Converter<Object, ?> converter2 = converter;
        JavaType javaType2 = javaType;
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        if (getClass() != StdDelegatingSerializer.class) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Sub-class ").append(getClass().getName()).append(" must override 'withDelegate'").toString());
            throw th2;
        }
        new StdDelegatingSerializer(converter2, javaType2, jsonSerializer2);
        return stdDelegatingSerializer;
    }

    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._delegateSerializer != null && (this._delegateSerializer instanceof ResolvableSerializer)) {
            ((ResolvableSerializer) this._delegateSerializer).resolve(serializerProvider2);
        }
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        if (this._delegateSerializer == null) {
            JavaType javaType = this._delegateType;
            if (javaType == null) {
                javaType = this._converter.getOutputType(serializerProvider2.getTypeFactory());
            }
            return withDelegate(this._converter, javaType, serializerProvider2.findValueSerializer(javaType, beanProperty2));
        } else if (this._delegateSerializer instanceof ContextualSerializer) {
            JsonSerializer<?> createContextual = ((ContextualSerializer) this._delegateSerializer).createContextual(serializerProvider2, beanProperty2);
            if (createContextual != this._delegateSerializer) {
                return withDelegate(this._converter, this._delegateType, createContextual);
            }
            return this;
        } else {
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public Converter<Object, ?> getConverter() {
        return this._converter;
    }

    public JsonSerializer<?> getDelegatee() {
        return this._delegateSerializer;
    }

    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        Object convertValue = convertValue(obj);
        if (convertValue == null) {
            serializerProvider2.defaultSerializeNull(jsonGenerator2);
        } else {
            this._delegateSerializer.serialize(convertValue, jsonGenerator2, serializerProvider2);
        }
    }

    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        this._delegateSerializer.serializeWithType(convertValue(obj), jsonGenerator, serializerProvider, typeSerializer);
    }

    public boolean isEmpty(Object obj) {
        return this._delegateSerializer.isEmpty(convertValue(obj));
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        if (this._delegateSerializer instanceof SchemaAware) {
            return ((SchemaAware) this._delegateSerializer).getSchema(serializerProvider2, type2);
        }
        return super.getSchema(serializerProvider2, type2);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type, boolean z) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        boolean z2 = z;
        if (this._delegateSerializer instanceof SchemaAware) {
            return ((SchemaAware) this._delegateSerializer).getSchema(serializerProvider2, type2, z2);
        }
        return super.getSchema(serializerProvider2, type2);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        this._delegateSerializer.acceptJsonFormatVisitor(jsonFormatVisitorWrapper, javaType);
    }

    /* access modifiers changed from: protected */
    public Object convertValue(Object obj) {
        return this._converter.convert(obj);
    }
}
