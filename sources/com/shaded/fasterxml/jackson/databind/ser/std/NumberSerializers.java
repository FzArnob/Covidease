package com.shaded.fasterxml.jackson.databind.ser.std;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import com.shaded.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public class NumberSerializers {
    protected NumberSerializers() {
    }

    public static void addAll(Map<String, JsonSerializer<?>> map) {
        Object obj;
        Map<String, JsonSerializer<?>> map2 = map;
        new IntegerSerializer();
        Object obj2 = obj;
        JsonSerializer<?> put = map2.put(Integer.class.getName(), obj2);
        JsonSerializer<?> put2 = map2.put(Integer.TYPE.getName(), obj2);
        JsonSerializer<?> put3 = map2.put(Long.class.getName(), LongSerializer.instance);
        JsonSerializer<?> put4 = map2.put(Long.TYPE.getName(), LongSerializer.instance);
        JsonSerializer<?> put5 = map2.put(Byte.class.getName(), IntLikeSerializer.instance);
        JsonSerializer<?> put6 = map2.put(Byte.TYPE.getName(), IntLikeSerializer.instance);
        JsonSerializer<?> put7 = map2.put(Short.class.getName(), ShortSerializer.instance);
        JsonSerializer<?> put8 = map2.put(Short.TYPE.getName(), ShortSerializer.instance);
        JsonSerializer<?> put9 = map2.put(Float.class.getName(), FloatSerializer.instance);
        JsonSerializer<?> put10 = map2.put(Float.TYPE.getName(), FloatSerializer.instance);
        JsonSerializer<?> put11 = map2.put(Double.class.getName(), DoubleSerializer.instance);
        JsonSerializer<?> put12 = map2.put(Double.TYPE.getName(), DoubleSerializer.instance);
    }

    @JacksonStdImpl
    public static final class ShortSerializer extends StdScalarSerializer<Short> {
        static final ShortSerializer instance;

        static {
            ShortSerializer shortSerializer;
            new ShortSerializer();
            instance = shortSerializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ShortSerializer() {
            super(Short.class);
        }

        public void serialize(Short sh, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            SerializerProvider serializerProvider2 = serializerProvider;
            jsonGenerator.writeNumber(sh.shortValue());
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            return createSchemaNode("number", true);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonIntegerFormatVisitor expectIntegerFormat = jsonFormatVisitorWrapper.expectIntegerFormat(javaType);
            if (expectIntegerFormat != null) {
                expectIntegerFormat.numberType(JsonParser.NumberType.INT);
            }
        }
    }

    @JacksonStdImpl
    public static final class IntegerSerializer extends NonTypedScalarSerializerBase<Integer> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IntegerSerializer() {
            super(Integer.class);
        }

        public void serialize(Integer num, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            SerializerProvider serializerProvider2 = serializerProvider;
            jsonGenerator.writeNumber(num.intValue());
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

    @JacksonStdImpl
    public static final class IntLikeSerializer extends StdScalarSerializer<Number> {
        static final IntLikeSerializer instance;

        static {
            IntLikeSerializer intLikeSerializer;
            new IntLikeSerializer();
            instance = intLikeSerializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IntLikeSerializer() {
            super(Number.class);
        }

        public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            SerializerProvider serializerProvider2 = serializerProvider;
            jsonGenerator.writeNumber(number.intValue());
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

    @JacksonStdImpl
    public static final class LongSerializer extends StdScalarSerializer<Long> {
        static final LongSerializer instance;

        static {
            LongSerializer longSerializer;
            new LongSerializer();
            instance = longSerializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LongSerializer() {
            super(Long.class);
        }

        public void serialize(Long l, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            SerializerProvider serializerProvider2 = serializerProvider;
            jsonGenerator.writeNumber(l.longValue());
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            return createSchemaNode("number", true);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonIntegerFormatVisitor expectIntegerFormat = jsonFormatVisitorWrapper.expectIntegerFormat(javaType);
            if (expectIntegerFormat != null) {
                expectIntegerFormat.numberType(JsonParser.NumberType.LONG);
            }
        }
    }

    @JacksonStdImpl
    public static final class FloatSerializer extends StdScalarSerializer<Float> {
        static final FloatSerializer instance;

        static {
            FloatSerializer floatSerializer;
            new FloatSerializer();
            instance = floatSerializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FloatSerializer() {
            super(Float.class);
        }

        public void serialize(Float f, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            SerializerProvider serializerProvider2 = serializerProvider;
            jsonGenerator.writeNumber(f.floatValue());
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            return createSchemaNode("number", true);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonNumberFormatVisitor expectNumberFormat = jsonFormatVisitorWrapper.expectNumberFormat(javaType);
            if (expectNumberFormat != null) {
                expectNumberFormat.numberType(JsonParser.NumberType.FLOAT);
            }
        }
    }

    @JacksonStdImpl
    public static final class DoubleSerializer extends NonTypedScalarSerializerBase<Double> {
        static final DoubleSerializer instance;

        static {
            DoubleSerializer doubleSerializer;
            new DoubleSerializer();
            instance = doubleSerializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DoubleSerializer() {
            super(Double.class);
        }

        public void serialize(Double d, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            SerializerProvider serializerProvider2 = serializerProvider;
            jsonGenerator.writeNumber(d.doubleValue());
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            return createSchemaNode("number", true);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonNumberFormatVisitor expectNumberFormat = jsonFormatVisitorWrapper.expectNumberFormat(javaType);
            if (expectNumberFormat != null) {
                expectNumberFormat.numberType(JsonParser.NumberType.DOUBLE);
            }
        }
    }

    @JacksonStdImpl
    public static final class NumberSerializer extends StdScalarSerializer<Number> {
        public static final NumberSerializer instance;

        static {
            NumberSerializer numberSerializer;
            new NumberSerializer();
            instance = numberSerializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public NumberSerializer() {
            super(Number.class);
        }

        public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            Number number2 = number;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            SerializerProvider serializerProvider2 = serializerProvider;
            if (number2 instanceof BigDecimal) {
                if (!serializerProvider2.isEnabled(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN) || (jsonGenerator2 instanceof TokenBuffer)) {
                    jsonGenerator2.writeNumber((BigDecimal) number2);
                } else {
                    jsonGenerator2.writeNumber(((BigDecimal) number2).toPlainString());
                }
            } else if (number2 instanceof BigInteger) {
                jsonGenerator2.writeNumber((BigInteger) number2);
            } else if (number2 instanceof Integer) {
                jsonGenerator2.writeNumber(number2.intValue());
            } else if (number2 instanceof Long) {
                jsonGenerator2.writeNumber(number2.longValue());
            } else if (number2 instanceof Double) {
                jsonGenerator2.writeNumber(number2.doubleValue());
            } else if (number2 instanceof Float) {
                jsonGenerator2.writeNumber(number2.floatValue());
            } else if ((number2 instanceof Byte) || (number2 instanceof Short)) {
                jsonGenerator2.writeNumber(number2.intValue());
            } else {
                jsonGenerator2.writeNumber(number2.toString());
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            return createSchemaNode("number", true);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonNumberFormatVisitor expectNumberFormat = jsonFormatVisitorWrapper.expectNumberFormat(javaType);
            if (expectNumberFormat != null) {
                expectNumberFormat.numberType(JsonParser.NumberType.BIG_DECIMAL);
            }
        }
    }
}
