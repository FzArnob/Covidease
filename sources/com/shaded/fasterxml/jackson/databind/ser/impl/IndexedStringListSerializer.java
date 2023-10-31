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
import java.util.List;

@JacksonStdImpl
public final class IndexedStringListSerializer extends StaticListSerializerBase<List<String>> implements ContextualSerializer {
    public static final IndexedStringListSerializer instance;
    protected final JsonSerializer<String> _serializer;

    static {
        IndexedStringListSerializer indexedStringListSerializer;
        new IndexedStringListSerializer();
        instance = indexedStringListSerializer;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected IndexedStringListSerializer() {
        this((JsonSerializer<?>) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IndexedStringListSerializer(JsonSerializer<?> jsonSerializer) {
        super(List.class);
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
        new IndexedStringListSerializer(findConvertingContentSerializer);
        return jsonSerializer;
    }

    public void serialize(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        List<String> list2 = list;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        int size = list2.size();
        if (size != 1 || !serializerProvider2.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
            jsonGenerator2.writeStartArray();
            if (this._serializer == null) {
                serializeContents(list2, jsonGenerator2, serializerProvider2, size);
            } else {
                serializeUsingCustom(list2, jsonGenerator2, serializerProvider2, size);
            }
            jsonGenerator2.writeEndArray();
            return;
        }
        _serializeUnwrapped(list2, jsonGenerator2, serializerProvider2);
    }

    private final void _serializeUnwrapped(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        List<String> list2 = list;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._serializer == null) {
            serializeContents(list2, jsonGenerator2, serializerProvider2, 1);
        } else {
            serializeUsingCustom(list2, jsonGenerator2, serializerProvider2, 1);
        }
    }

    public void serializeWithType(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        List<String> list2 = list;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        TypeSerializer typeSerializer2 = typeSerializer;
        int size = list2.size();
        typeSerializer2.writeTypePrefixForArray(list2, jsonGenerator2);
        if (this._serializer == null) {
            serializeContents(list2, jsonGenerator2, serializerProvider2, size);
        } else {
            serializeUsingCustom(list2, jsonGenerator2, serializerProvider2, size);
        }
        typeSerializer2.writeTypeSuffixForArray(list2, jsonGenerator2);
    }

    private final void serializeContents(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, int i) throws IOException, JsonGenerationException {
        List<String> list2 = list;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        int i2 = i;
        int i3 = 0;
        while (i3 < i2) {
            try {
                String str = list2.get(i3);
                if (str == null) {
                    serializerProvider2.defaultSerializeNull(jsonGenerator2);
                } else {
                    jsonGenerator2.writeString(str);
                }
                i3++;
            } catch (Exception e) {
                wrapAndThrow(serializerProvider2, (Throwable) e, (Object) list2, i3);
                return;
            }
        }
    }

    private final void serializeUsingCustom(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, int i) throws IOException, JsonGenerationException {
        List<String> list2 = list;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        int i2 = i;
        try {
            JsonSerializer<String> jsonSerializer = this._serializer;
            for (int i3 = 0; i3 < i2; i3++) {
                String str = list2.get(i3);
                if (str == null) {
                    serializerProvider2.defaultSerializeNull(jsonGenerator2);
                } else {
                    jsonSerializer.serialize(str, jsonGenerator2, serializerProvider2);
                }
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider2, (Throwable) e, (Object) list2, 0);
        }
    }
}
