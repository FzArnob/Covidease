package com.shaded.fasterxml.jackson.databind.ser.std;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonBooleanFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class StdJdkSerializers {
    public StdJdkSerializers() {
    }

    public static Collection<Map.Entry<Class<?>, Object>> all() {
        HashMap hashMap;
        new HashMap();
        HashMap hashMap2 = hashMap;
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        Object put = hashMap2.put(URL.class, toStringSerializer);
        Object put2 = hashMap2.put(URI.class, toStringSerializer);
        Object put3 = hashMap2.put(Currency.class, toStringSerializer);
        Object put4 = hashMap2.put(UUID.class, toStringSerializer);
        Object put5 = hashMap2.put(Pattern.class, toStringSerializer);
        Object put6 = hashMap2.put(Locale.class, toStringSerializer);
        Object put7 = hashMap2.put(Locale.class, toStringSerializer);
        Object put8 = hashMap2.put(AtomicReference.class, AtomicReferenceSerializer.class);
        Object put9 = hashMap2.put(AtomicBoolean.class, AtomicBooleanSerializer.class);
        Object put10 = hashMap2.put(AtomicInteger.class, AtomicIntegerSerializer.class);
        Object put11 = hashMap2.put(AtomicLong.class, AtomicLongSerializer.class);
        Object put12 = hashMap2.put(File.class, FileSerializer.class);
        Object put13 = hashMap2.put(Class.class, ClassSerializer.class);
        Object put14 = hashMap2.put(Void.TYPE, NullSerializer.class);
        return hashMap2.entrySet();
    }

    public static final class AtomicBooleanSerializer extends StdScalarSerializer<AtomicBoolean> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AtomicBooleanSerializer() {
            super(AtomicBoolean.class, false);
        }

        public void serialize(AtomicBoolean atomicBoolean, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            SerializerProvider serializerProvider2 = serializerProvider;
            jsonGenerator.writeBoolean(atomicBoolean.get());
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            return createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, true);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonBooleanFormatVisitor expectBooleanFormat = jsonFormatVisitorWrapper.expectBooleanFormat(javaType);
        }
    }

    public static final class AtomicIntegerSerializer extends StdScalarSerializer<AtomicInteger> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AtomicIntegerSerializer() {
            super(AtomicInteger.class, false);
        }

        public void serialize(AtomicInteger atomicInteger, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            SerializerProvider serializerProvider2 = serializerProvider;
            jsonGenerator.writeNumber(atomicInteger.get());
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            return createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_INTEGER, true);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonIntegerFormatVisitor expectIntegerFormat = jsonFormatVisitorWrapper.expectIntegerFormat(javaType);
            if (expectIntegerFormat != null) {
                expectIntegerFormat.numberType(JsonParser.NumberType.INT);
            }
        }
    }

    public static final class AtomicLongSerializer extends StdScalarSerializer<AtomicLong> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AtomicLongSerializer() {
            super(AtomicLong.class, false);
        }

        public void serialize(AtomicLong atomicLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            SerializerProvider serializerProvider2 = serializerProvider;
            jsonGenerator.writeNumber(atomicLong.get());
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            return createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_INTEGER, true);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonIntegerFormatVisitor expectIntegerFormat = jsonFormatVisitorWrapper.expectIntegerFormat(javaType);
            if (expectIntegerFormat != null) {
                expectIntegerFormat.numberType(JsonParser.NumberType.LONG);
            }
        }
    }

    public static final class AtomicReferenceSerializer extends StdSerializer<AtomicReference<?>> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AtomicReferenceSerializer() {
            super(AtomicReference.class, false);
        }

        public void serialize(AtomicReference<?> atomicReference, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            serializerProvider.defaultSerializeValue(atomicReference.get(), jsonGenerator);
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            return createSchemaNode("any", true);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonAnyFormatVisitor expectAnyFormat = jsonFormatVisitorWrapper.expectAnyFormat(javaType);
        }
    }

    public static final class FileSerializer extends StdScalarSerializer<File> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FileSerializer() {
            super(File.class);
        }

        public void serialize(File file, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            SerializerProvider serializerProvider2 = serializerProvider;
            jsonGenerator.writeString(file.getAbsolutePath());
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

    public static final class ClassSerializer extends StdScalarSerializer<Class<?>> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ClassSerializer() {
            super(Class.class, false);
        }

        public void serialize(Class<?> cls, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            SerializerProvider serializerProvider2 = serializerProvider;
            jsonGenerator.writeString(cls.getName());
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
}
