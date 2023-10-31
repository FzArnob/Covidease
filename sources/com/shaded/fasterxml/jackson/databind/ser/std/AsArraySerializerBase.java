package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.shaded.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AsArraySerializerBase<T> extends ContainerSerializer<T> implements ContextualSerializer {
    protected PropertySerializerMap _dynamicSerializers;
    protected final JsonSerializer<Object> _elementSerializer;
    protected final JavaType _elementType;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected final TypeSerializer _valueTypeSerializer;

    /* access modifiers changed from: protected */
    public abstract void serializeContents(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException;

    public abstract AsArraySerializerBase<T> withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AsArraySerializerBase(Class<?> cls, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        super(cls, false);
        JavaType javaType2 = javaType;
        TypeSerializer typeSerializer2 = typeSerializer;
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        this._elementType = javaType2;
        this._staticTyping = z || (javaType2 != null && javaType2.isFinal());
        this._valueTypeSerializer = typeSerializer2;
        this._property = beanProperty2;
        this._elementSerializer = jsonSerializer2;
        this._dynamicSerializers = PropertySerializerMap.emptyMap();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected AsArraySerializerBase(com.shaded.fasterxml.jackson.databind.ser.std.AsArraySerializerBase<?> r8, com.shaded.fasterxml.jackson.databind.BeanProperty r9, com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer r10, com.shaded.fasterxml.jackson.databind.JsonSerializer<?> r11) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r0
            r6 = r1
            r5.<init>((com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer<?>) r6)
            r5 = r0
            r6 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r6 = r6._elementType
            r5._elementType = r6
            r5 = r0
            r6 = r1
            boolean r6 = r6._staticTyping
            r5._staticTyping = r6
            r5 = r0
            r6 = r3
            r5._valueTypeSerializer = r6
            r5 = r0
            r6 = r2
            r5._property = r6
            r5 = r0
            r6 = r4
            r5._elementSerializer = r6
            r5 = r0
            r6 = r1
            com.shaded.fasterxml.jackson.databind.ser.impl.PropertySerializerMap r6 = r6._dynamicSerializers
            r5._dynamicSerializers = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.AsArraySerializerBase.<init>(com.shaded.fasterxml.jackson.databind.ser.std.AsArraySerializerBase, com.shaded.fasterxml.jackson.databind.BeanProperty, com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer, com.shaded.fasterxml.jackson.databind.JsonSerializer):void");
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        AnnotatedMember member;
        Object findContentSerializer;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        if (typeSerializer != null) {
            typeSerializer = typeSerializer.forProperty(beanProperty2);
        }
        JsonSerializer<Object> jsonSerializer = null;
        if (!(beanProperty2 == null || (member = beanProperty2.getMember()) == null || (findContentSerializer = serializerProvider2.getAnnotationIntrospector().findContentSerializer(member)) == null)) {
            jsonSerializer = serializerProvider2.serializerInstance(member, findContentSerializer);
        }
        if (jsonSerializer == null) {
            jsonSerializer = this._elementSerializer;
        }
        JsonSerializer<Object> findConvertingContentSerializer = findConvertingContentSerializer(serializerProvider2, beanProperty2, jsonSerializer);
        if (findConvertingContentSerializer == null) {
            if (findConvertingContentSerializer == null && this._elementType != null && (this._staticTyping || hasContentTypeAnnotation(serializerProvider2, beanProperty2))) {
                findConvertingContentSerializer = serializerProvider2.findValueSerializer(this._elementType, beanProperty2);
            }
        } else if (findConvertingContentSerializer instanceof ContextualSerializer) {
            findConvertingContentSerializer = ((ContextualSerializer) findConvertingContentSerializer).createContextual(serializerProvider2, beanProperty2);
        }
        if (findConvertingContentSerializer != this._elementSerializer || beanProperty2 != this._property || this._valueTypeSerializer != typeSerializer) {
            return withResolved(beanProperty2, typeSerializer, findConvertingContentSerializer);
        }
        return this;
    }

    public JavaType getContentType() {
        return this._elementType;
    }

    public JsonSerializer<?> getContentSerializer() {
        return this._elementSerializer;
    }

    public final void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        T t2 = t;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (!serializerProvider2.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || !hasSingleElement(t2)) {
            jsonGenerator2.writeStartArray();
            serializeContents(t2, jsonGenerator2, serializerProvider2);
            jsonGenerator2.writeEndArray();
            return;
        }
        serializeContents(t2, jsonGenerator2, serializerProvider2);
    }

    public final void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        T t2 = t;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        TypeSerializer typeSerializer2 = typeSerializer;
        typeSerializer2.writeTypePrefixForArray(t2, jsonGenerator2);
        serializeContents(t2, jsonGenerator2, serializerProvider);
        typeSerializer2.writeTypeSuffixForArray(t2, jsonGenerator2);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        JavaType javaType = null;
        if (type2 != null) {
            javaType = serializerProvider2.constructType(type2).getContentType();
            if (javaType == null && (type2 instanceof ParameterizedType)) {
                Type[] actualTypeArguments = ((ParameterizedType) type2).getActualTypeArguments();
                if (actualTypeArguments.length == 1) {
                    javaType = serializerProvider2.constructType(actualTypeArguments[0]);
                }
            }
        }
        if (javaType == null && this._elementType != null) {
            javaType = this._elementType;
        }
        if (javaType != null) {
            JsonNode jsonNode = null;
            if (javaType.getRawClass() != Object.class) {
                JsonSerializer<Object> findValueSerializer = serializerProvider2.findValueSerializer(javaType, this._property);
                if (findValueSerializer instanceof SchemaAware) {
                    jsonNode = ((SchemaAware) findValueSerializer).getSchema(serializerProvider2, (Type) null);
                }
            }
            if (jsonNode == null) {
                jsonNode = JsonSchema.getDefaultSchemaNode();
            }
            JsonNode put = createSchemaNode.put("items", jsonNode);
        }
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        Throwable th;
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        JavaType javaType2 = javaType;
        JsonArrayFormatVisitor expectArrayFormat = jsonFormatVisitorWrapper2 == null ? null : jsonFormatVisitorWrapper2.expectArrayFormat(javaType2);
        if (expectArrayFormat != null) {
            JavaType moreSpecificType = jsonFormatVisitorWrapper2.getProvider().getTypeFactory().moreSpecificType(this._elementType, javaType2.getContentType());
            if (moreSpecificType == null) {
                Throwable th2 = th;
                new JsonMappingException("Could not resolve type");
                throw th2;
            }
            JsonSerializer<Object> jsonSerializer = this._elementSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = jsonFormatVisitorWrapper2.getProvider().findValueSerializer(moreSpecificType, this._property);
            }
            expectArrayFormat.itemsFormat(jsonSerializer, moreSpecificType);
        }
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap propertySerializerMap2 = propertySerializerMap;
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap2.findAndAddSerializer(cls, serializerProvider, this._property);
        if (propertySerializerMap2 != findAndAddSerializer.map) {
            this._dynamicSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap propertySerializerMap2 = propertySerializerMap;
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap2.findAndAddSerializer(javaType, serializerProvider, this._property);
        if (propertySerializerMap2 != findAndAddSerializer.map) {
            this._dynamicSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }
}
