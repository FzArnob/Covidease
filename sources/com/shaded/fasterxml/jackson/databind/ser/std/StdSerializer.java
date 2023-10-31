package com.shaded.fasterxml.jackson.databind.ser.std;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public abstract class StdSerializer<T> extends JsonSerializer<T> implements JsonFormatVisitable, SchemaAware {
    protected final Class<T> _handledType;

    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException;

    protected StdSerializer(Class<T> cls) {
        this._handledType = cls;
    }

    protected StdSerializer(JavaType javaType) {
        this._handledType = javaType.getRawClass();
    }

    protected StdSerializer(Class<?> cls, boolean z) {
        boolean z2 = z;
        this._handledType = cls;
    }

    public Class<T> handledType() {
        return this._handledType;
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        return createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_STRING);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type, boolean z) throws JsonMappingException {
        boolean z2 = z;
        ObjectNode objectNode = (ObjectNode) getSchema(serializerProvider, type);
        if (!z2) {
            ObjectNode put = objectNode.put("required", !z2);
        }
        return objectNode;
    }

    /* access modifiers changed from: protected */
    public ObjectNode createObjectNode() {
        return JsonNodeFactory.instance.objectNode();
    }

    /* access modifiers changed from: protected */
    public ObjectNode createSchemaNode(String str) {
        ObjectNode createObjectNode = createObjectNode();
        ObjectNode put = createObjectNode.put("type", str);
        return createObjectNode;
    }

    /* access modifiers changed from: protected */
    public ObjectNode createSchemaNode(String str, boolean z) {
        boolean z2 = z;
        ObjectNode createSchemaNode = createSchemaNode(str);
        if (!z2) {
            ObjectNode put = createSchemaNode.put("required", !z2);
        }
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonAnyFormatVisitor expectAnyFormat = jsonFormatVisitorWrapper.expectAnyFormat(javaType);
    }

    public void wrapAndThrow(SerializerProvider serializerProvider, Throwable th, Object obj, String str) throws IOException {
        SerializerProvider serializerProvider2 = serializerProvider;
        Throwable th2 = th;
        Object obj2 = obj;
        String str2 = str;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        }
        boolean z = serializerProvider2 == null || serializerProvider2.isEnabled(SerializationFeature.WRAP_EXCEPTIONS);
        if (th2 instanceof IOException) {
            if (!z || !(th2 instanceof JsonMappingException)) {
                throw ((IOException) th2);
            }
        } else if (!z && (th2 instanceof RuntimeException)) {
            throw ((RuntimeException) th2);
        }
        throw JsonMappingException.wrapWithPath(th2, obj2, str2);
    }

    public void wrapAndThrow(SerializerProvider serializerProvider, Throwable th, Object obj, int i) throws IOException {
        SerializerProvider serializerProvider2 = serializerProvider;
        Throwable th2 = th;
        Object obj2 = obj;
        int i2 = i;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        }
        boolean z = serializerProvider2 == null || serializerProvider2.isEnabled(SerializationFeature.WRAP_EXCEPTIONS);
        if (th2 instanceof IOException) {
            if (!z || !(th2 instanceof JsonMappingException)) {
                throw ((IOException) th2);
            }
        } else if (!z && (th2 instanceof RuntimeException)) {
            throw ((RuntimeException) th2);
        }
        throw JsonMappingException.wrapWithPath(th2, obj2, i2);
    }

    /* access modifiers changed from: protected */
    public boolean isDefaultSerializer(JsonSerializer<?> jsonSerializer) {
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        return (jsonSerializer2 == null || jsonSerializer2.getClass().getAnnotation(JacksonStdImpl.class) == null) ? false : true;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> findConvertingContentSerializer(SerializerProvider serializerProvider, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) throws JsonMappingException {
        Object findSerializationContentConverter;
        JsonSerializer<?> jsonSerializer2;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        JsonSerializer<Object> jsonSerializer3 = jsonSerializer;
        AnnotationIntrospector annotationIntrospector = serializerProvider2.getAnnotationIntrospector();
        if (annotationIntrospector == null || beanProperty2 == null || (findSerializationContentConverter = annotationIntrospector.findSerializationContentConverter(beanProperty2.getMember())) == null) {
            return jsonSerializer3;
        }
        Converter<Object, Object> converterInstance = serializerProvider2.converterInstance(beanProperty2.getMember(), findSerializationContentConverter);
        JavaType outputType = converterInstance.getOutputType(serializerProvider2.getTypeFactory());
        if (jsonSerializer3 == null) {
            jsonSerializer3 = serializerProvider2.findValueSerializer(outputType, beanProperty2);
        }
        new StdDelegatingSerializer(converterInstance, outputType, jsonSerializer3);
        return jsonSerializer2;
    }
}
