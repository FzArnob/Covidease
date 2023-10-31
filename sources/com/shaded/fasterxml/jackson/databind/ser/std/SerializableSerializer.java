package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializable;
import com.shaded.fasterxml.jackson.databind.ObjectMapper;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

@JacksonStdImpl
public class SerializableSerializer extends StdSerializer<JsonSerializable> {
    private static final AtomicReference<ObjectMapper> _mapperReference;
    public static final SerializableSerializer instance;

    static {
        SerializableSerializer serializableSerializer;
        AtomicReference<ObjectMapper> atomicReference;
        new SerializableSerializer();
        instance = serializableSerializer;
        new AtomicReference<>();
        _mapperReference = atomicReference;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected SerializableSerializer() {
        super(JsonSerializable.class);
    }

    public void serialize(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonSerializable.serialize(jsonGenerator, serializerProvider);
    }

    public final void serializeWithType(JsonSerializable jsonSerializable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        jsonSerializable.serializeWithType(jsonGenerator, serializerProvider, typeSerializer);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        Throwable th;
        Throwable th2;
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        ObjectNode createObjectNode = createObjectNode();
        String str = "any";
        String str2 = null;
        String str3 = null;
        if (type2 != null) {
            Class<?> rawClass = TypeFactory.rawClass(type2);
            if (rawClass.isAnnotationPresent(JsonSerializableSchema.class)) {
                JsonSerializableSchema jsonSerializableSchema = (JsonSerializableSchema) rawClass.getAnnotation(JsonSerializableSchema.class);
                str = jsonSerializableSchema.schemaType();
                if (!JsonSerializableSchema.NO_VALUE.equals(jsonSerializableSchema.schemaObjectPropertiesDefinition())) {
                    str2 = jsonSerializableSchema.schemaObjectPropertiesDefinition();
                }
                if (!JsonSerializableSchema.NO_VALUE.equals(jsonSerializableSchema.schemaItemDefinition())) {
                    str3 = jsonSerializableSchema.schemaItemDefinition();
                }
            }
        }
        ObjectNode put = createObjectNode.put("type", str);
        if (str2 != null) {
            try {
                JsonNode put2 = createObjectNode.put("properties", _getObjectMapper().readTree(str2));
            } catch (IOException e) {
                IOException iOException = e;
                Throwable th3 = th2;
                new JsonMappingException("Failed to parse @JsonSerializableSchema.schemaObjectPropertiesDefinition value");
                throw th3;
            }
        }
        if (str3 != null) {
            try {
                JsonNode put3 = createObjectNode.put("items", _getObjectMapper().readTree(str3));
            } catch (IOException e2) {
                IOException iOException2 = e2;
                Throwable th4 = th;
                new JsonMappingException("Failed to parse @JsonSerializableSchema.schemaItemDefinition value");
                throw th4;
            }
        }
        return createObjectNode;
    }

    private static final synchronized ObjectMapper _getObjectMapper() {
        ObjectMapper objectMapper;
        ObjectMapper objectMapper2;
        synchronized (SerializableSerializer.class) {
            ObjectMapper objectMapper3 = _mapperReference.get();
            if (objectMapper3 == null) {
                new ObjectMapper();
                objectMapper3 = objectMapper2;
                _mapperReference.set(objectMapper3);
            }
            objectMapper = objectMapper3;
        }
        return objectMapper;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonAnyFormatVisitor expectAnyFormat = jsonFormatVisitorWrapper.expectAnyFormat(javaType);
    }
}
