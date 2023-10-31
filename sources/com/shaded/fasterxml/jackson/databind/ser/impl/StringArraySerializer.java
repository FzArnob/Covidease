package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class StringArraySerializer extends ArraySerializerBase<String[]> implements ContextualSerializer {
    private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(String.class);
    public static final StringArraySerializer instance;
    protected final JsonSerializer<Object> _elementSerializer;

    static {
        StringArraySerializer stringArraySerializer;
        new StringArraySerializer();
        instance = stringArraySerializer;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected StringArraySerializer() {
        super(String[].class, (BeanProperty) null);
        this._elementSerializer = null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StringArraySerializer(StringArraySerializer stringArraySerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) {
        super((ArraySerializerBase<?>) stringArraySerializer, beanProperty);
        this._elementSerializer = jsonSerializer;
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        TypeSerializer typeSerializer2 = typeSerializer;
        return this;
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
            jsonSerializer2 = this._elementSerializer;
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
        if (findConvertingContentSerializer == this._elementSerializer) {
            return this;
        }
        new StringArraySerializer(this, beanProperty2, findConvertingContentSerializer);
        return jsonSerializer;
    }

    public JavaType getContentType() {
        return VALUE_TYPE;
    }

    public JsonSerializer<?> getContentSerializer() {
        return this._elementSerializer;
    }

    public boolean isEmpty(String[] strArr) {
        String[] strArr2 = strArr;
        return strArr2 == null || strArr2.length == 0;
    }

    public boolean hasSingleElement(String[] strArr) {
        return strArr.length == 1;
    }

    public void serializeContents(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        String[] strArr2 = strArr;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        int length = strArr2.length;
        if (length != 0) {
            if (this._elementSerializer != null) {
                serializeContentsSlow(strArr2, jsonGenerator2, serializerProvider2, this._elementSerializer);
                return;
            }
            for (int i = 0; i < length; i++) {
                if (strArr2[i] == null) {
                    jsonGenerator2.writeNull();
                } else {
                    jsonGenerator2.writeString(strArr2[i]);
                }
            }
        }
    }

    private void serializeContentsSlow(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        String[] strArr2 = strArr;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        int length = strArr2.length;
        for (int i = 0; i < length; i++) {
            if (strArr2[i] == null) {
                serializerProvider2.defaultSerializeNull(jsonGenerator2);
            } else {
                jsonSerializer2.serialize(strArr2[i], jsonGenerator2, serializerProvider2);
            }
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        JsonNode put = createSchemaNode.put("items", (JsonNode) createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_STRING));
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonArrayFormatVisitor expectArrayFormat;
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        JavaType javaType2 = javaType;
        if (jsonFormatVisitorWrapper2 != null && (expectArrayFormat = jsonFormatVisitorWrapper2.expectArrayFormat(javaType2)) != null) {
            expectArrayFormat.itemsFormat(JsonFormatTypes.STRING);
        }
    }
}
