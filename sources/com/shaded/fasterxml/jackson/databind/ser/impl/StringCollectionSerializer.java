package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public class StringCollectionSerializer extends StaticListSerializerBase<Collection<String>> implements ContextualSerializer {
    public static final StringCollectionSerializer instance;
    protected final JsonSerializer<String> _serializer;

    static {
        StringCollectionSerializer stringCollectionSerializer;
        new StringCollectionSerializer();
        instance = stringCollectionSerializer;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected StringCollectionSerializer() {
        this((JsonSerializer<?>) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected StringCollectionSerializer(JsonSerializer<?> jsonSerializer) {
        super(Collection.class);
        this._serializer = jsonSerializer;
    }

    /* access modifiers changed from: protected */
    public JsonNode contentSchema() {
        return createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_STRING, true);
    }

    /* access modifiers changed from: protected */
    public void acceptContentVisitor(JsonArrayFormatVisitor jsonArrayFormatVisitor) throws JsonMappingException {
        jsonArrayFormatVisitor.itemsFormat(JsonFormatTypes.STRING);
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer;
        AnnotatedMember member;
        Object findContentSerializer;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<Object> jsonSerializer2 = null;
        if (!(beanProperty2 == null || (member = beanProperty2.getMember()) == null || (findContentSerializer = serializerProvider2.getAnnotationIntrospector().findContentSerializer(member)) == null)) {
            jsonSerializer2 = serializerProvider2.serializerInstance(member, findContentSerializer);
        }
        if (jsonSerializer2 == null) {
            jsonSerializer2 = this._serializer;
        }
        JsonSerializer<Object> findConvertingContentSerializer = findConvertingContentSerializer(serializerProvider2, beanProperty2, jsonSerializer2);
        if (findConvertingContentSerializer == null) {
            findConvertingContentSerializer = serializerProvider2.findValueSerializer((Class<?>) String.class, beanProperty2);
        } else if (findConvertingContentSerializer instanceof ContextualSerializer) {
            findConvertingContentSerializer = ((ContextualSerializer) findConvertingContentSerializer).createContextual(serializerProvider2, beanProperty2);
        }
        if (isDefaultSerializer(findConvertingContentSerializer)) {
            findConvertingContentSerializer = null;
        }
        if (findConvertingContentSerializer == this._serializer) {
            return this;
        }
        new StringCollectionSerializer(findConvertingContentSerializer);
        return jsonSerializer;
    }

    public void serialize(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Collection<String> collection2 = collection;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (collection2.size() != 1 || !serializerProvider2.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
            jsonGenerator2.writeStartArray();
            if (this._serializer == null) {
                serializeContents(collection2, jsonGenerator2, serializerProvider2);
            } else {
                serializeUsingCustom(collection2, jsonGenerator2, serializerProvider2);
            }
            jsonGenerator2.writeEndArray();
            return;
        }
        _serializeUnwrapped(collection2, jsonGenerator2, serializerProvider2);
    }

    private final void _serializeUnwrapped(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Collection<String> collection2 = collection;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._serializer == null) {
            serializeContents(collection2, jsonGenerator2, serializerProvider2);
        } else {
            serializeUsingCustom(collection2, jsonGenerator2, serializerProvider2);
        }
    }

    public void serializeWithType(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        Collection<String> collection2 = collection;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        TypeSerializer typeSerializer2 = typeSerializer;
        typeSerializer2.writeTypePrefixForArray(collection2, jsonGenerator2);
        if (this._serializer == null) {
            serializeContents(collection2, jsonGenerator2, serializerProvider2);
        } else {
            serializeUsingCustom(collection2, jsonGenerator2, serializerProvider2);
        }
        typeSerializer2.writeTypeSuffixForArray(collection2, jsonGenerator2);
    }

    private final void serializeContents(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Collection<String> collection2 = collection;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._serializer != null) {
            serializeUsingCustom(collection2, jsonGenerator2, serializerProvider2);
            return;
        }
        int i = 0;
        for (String next : collection2) {
            if (next == null) {
                try {
                    serializerProvider2.defaultSerializeNull(jsonGenerator2);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider2, (Throwable) e, (Object) collection2, i);
                }
            } else {
                jsonGenerator2.writeString(next);
            }
            i++;
        }
    }

    private void serializeUsingCustom(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Collection<String> collection2 = collection;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        JsonSerializer<String> jsonSerializer = this._serializer;
        for (String next : collection2) {
            if (next == null) {
                try {
                    serializerProvider2.defaultSerializeNull(jsonGenerator2);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider2, (Throwable) e, (Object) collection2, 0);
                }
            } else {
                jsonSerializer.serialize(next, jsonGenerator2, serializerProvider2);
            }
        }
    }
}
