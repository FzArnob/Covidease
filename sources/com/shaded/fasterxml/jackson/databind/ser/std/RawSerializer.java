package com.shaded.fasterxml.jackson.databind.ser.std;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class RawSerializer<T> extends StdSerializer<T> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RawSerializer(Class<?> cls) {
        super(cls, false);
    }

    public void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        SerializerProvider serializerProvider2 = serializerProvider;
        jsonGenerator.writeRawValue(t.toString());
    }

    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        T t2 = t;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        TypeSerializer typeSerializer2 = typeSerializer;
        typeSerializer2.writeTypePrefixForScalar(t2, jsonGenerator2);
        serialize(t2, jsonGenerator2, serializerProvider);
        typeSerializer2.writeTypeSuffixForScalar(t2, jsonGenerator2);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        return createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_STRING, true);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonStringFormatVisitor expectStringFormat = jsonFormatVisitorWrapper.expectStringFormat(javaType);
    }
}
