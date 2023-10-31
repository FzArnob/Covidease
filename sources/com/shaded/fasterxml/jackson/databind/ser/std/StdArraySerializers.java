package com.shaded.fasterxml.jackson.databind.ser.std;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class StdArraySerializers {
    protected static final HashMap<String, JsonSerializer<?>> _arraySerializers;

    static {
        HashMap<String, JsonSerializer<?>> hashMap;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        new HashMap<>();
        _arraySerializers = hashMap;
        new BooleanArraySerializer();
        JsonSerializer<?> put = _arraySerializers.put(boolean[].class.getName(), obj);
        new ByteArraySerializer();
        JsonSerializer<?> put2 = _arraySerializers.put(byte[].class.getName(), obj2);
        new CharArraySerializer();
        JsonSerializer<?> put3 = _arraySerializers.put(char[].class.getName(), obj3);
        new ShortArraySerializer();
        JsonSerializer<?> put4 = _arraySerializers.put(short[].class.getName(), obj4);
        new IntArraySerializer();
        JsonSerializer<?> put5 = _arraySerializers.put(int[].class.getName(), obj5);
        new LongArraySerializer();
        JsonSerializer<?> put6 = _arraySerializers.put(long[].class.getName(), obj6);
        new FloatArraySerializer();
        JsonSerializer<?> put7 = _arraySerializers.put(float[].class.getName(), obj7);
        new DoubleArraySerializer();
        JsonSerializer<?> put8 = _arraySerializers.put(double[].class.getName(), obj8);
    }

    protected StdArraySerializers() {
    }

    public static JsonSerializer<?> findStandardImpl(Class<?> cls) {
        return _arraySerializers.get(cls.getName());
    }

    protected static abstract class TypedPrimitiveArraySerializer<T> extends ArraySerializerBase<T> {
        protected final TypeSerializer _valueTypeSerializer;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected TypedPrimitiveArraySerializer(Class<T> cls) {
            super(cls);
            this._valueTypeSerializer = null;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected TypedPrimitiveArraySerializer(TypedPrimitiveArraySerializer<T> typedPrimitiveArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer) {
            super((ArraySerializerBase<?>) typedPrimitiveArraySerializer, beanProperty);
            this._valueTypeSerializer = typeSerializer;
        }
    }

    @JacksonStdImpl
    public static final class BooleanArraySerializer extends ArraySerializerBase<boolean[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Boolean.class);

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public BooleanArraySerializer() {
            super(boolean[].class, (BeanProperty) null);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            TypeSerializer typeSerializer2 = typeSerializer;
            return this;
        }

        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        public boolean isEmpty(boolean[] zArr) {
            boolean[] zArr2 = zArr;
            return zArr2 == null || zArr2.length == 0;
        }

        public boolean hasSingleElement(boolean[] zArr) {
            return zArr.length == 1;
        }

        public void serializeContents(boolean[] zArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            boolean[] zArr2 = zArr;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            SerializerProvider serializerProvider2 = serializerProvider;
            int length = zArr2.length;
            for (int i = 0; i < length; i++) {
                jsonGenerator2.writeBoolean(zArr2[i]);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            JsonNode put = createSchemaNode.put("items", (JsonNode) createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN));
            return createSchemaNode;
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonArrayFormatVisitor expectArrayFormat;
            JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
            JavaType javaType2 = javaType;
            if (jsonFormatVisitorWrapper2 != null && (expectArrayFormat = jsonFormatVisitorWrapper2.expectArrayFormat(javaType2)) != null) {
                expectArrayFormat.itemsFormat(JsonFormatTypes.BOOLEAN);
            }
        }
    }

    @JacksonStdImpl
    public static final class ByteArraySerializer extends StdSerializer<byte[]> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ByteArraySerializer() {
            super(byte[].class);
        }

        public boolean isEmpty(byte[] bArr) {
            byte[] bArr2 = bArr;
            return bArr2 == null || bArr2.length == 0;
        }

        public void serialize(byte[] bArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            byte[] bArr2 = bArr;
            jsonGenerator.writeBinary(serializerProvider.getConfig().getBase64Variant(), bArr2, 0, bArr2.length);
        }

        public void serializeWithType(byte[] bArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
            byte[] bArr2 = bArr;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            TypeSerializer typeSerializer2 = typeSerializer;
            typeSerializer2.writeTypePrefixForScalar(bArr2, jsonGenerator2);
            jsonGenerator2.writeBinary(serializerProvider.getConfig().getBase64Variant(), bArr2, 0, bArr2.length);
            typeSerializer2.writeTypeSuffixForScalar(bArr2, jsonGenerator2);
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

    @JacksonStdImpl
    public static final class ShortArraySerializer extends TypedPrimitiveArraySerializer<short[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Short.TYPE);

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ShortArraySerializer() {
            super(short[].class);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ShortArraySerializer(ShortArraySerializer shortArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer) {
            super(shortArraySerializer, beanProperty, typeSerializer);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            ContainerSerializer<?> containerSerializer;
            new ShortArraySerializer(this, this._property, typeSerializer);
            return containerSerializer;
        }

        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        public boolean isEmpty(short[] sArr) {
            short[] sArr2 = sArr;
            return sArr2 == null || sArr2.length == 0;
        }

        public boolean hasSingleElement(short[] sArr) {
            return sArr.length == 1;
        }

        public void serializeContents(short[] sArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            short[] sArr2 = sArr;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            SerializerProvider serializerProvider2 = serializerProvider;
            if (this._valueTypeSerializer != null) {
                int length = sArr2.length;
                for (int i = 0; i < length; i++) {
                    this._valueTypeSerializer.writeTypePrefixForScalar((Object) null, jsonGenerator2, Short.TYPE);
                    jsonGenerator2.writeNumber(sArr2[i]);
                    this._valueTypeSerializer.writeTypeSuffixForScalar((Object) null, jsonGenerator2);
                }
                return;
            }
            int length2 = sArr2.length;
            for (int i2 = 0; i2 < length2; i2++) {
                jsonGenerator2.writeNumber((int) sArr2[i2]);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            JsonNode put = createSchemaNode.put("items", (JsonNode) createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_INTEGER));
            return createSchemaNode;
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonArrayFormatVisitor expectArrayFormat;
            JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
            JavaType javaType2 = javaType;
            if (jsonFormatVisitorWrapper2 != null && (expectArrayFormat = jsonFormatVisitorWrapper2.expectArrayFormat(javaType2)) != null) {
                expectArrayFormat.itemsFormat(JsonFormatTypes.INTEGER);
            }
        }
    }

    @JacksonStdImpl
    public static final class CharArraySerializer extends StdSerializer<char[]> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CharArraySerializer() {
            super(char[].class);
        }

        public boolean isEmpty(char[] cArr) {
            char[] cArr2 = cArr;
            return cArr2 == null || cArr2.length == 0;
        }

        public void serialize(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            char[] cArr2 = cArr;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            if (serializerProvider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                jsonGenerator2.writeStartArray();
                _writeArrayContents(jsonGenerator2, cArr2);
                jsonGenerator2.writeEndArray();
                return;
            }
            jsonGenerator2.writeString(cArr2, 0, cArr2.length);
        }

        public void serializeWithType(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
            char[] cArr2 = cArr;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            TypeSerializer typeSerializer2 = typeSerializer;
            if (serializerProvider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                typeSerializer2.writeTypePrefixForArray(cArr2, jsonGenerator2);
                _writeArrayContents(jsonGenerator2, cArr2);
                typeSerializer2.writeTypeSuffixForArray(cArr2, jsonGenerator2);
                return;
            }
            typeSerializer2.writeTypePrefixForScalar(cArr2, jsonGenerator2);
            jsonGenerator2.writeString(cArr2, 0, cArr2.length);
            typeSerializer2.writeTypeSuffixForScalar(cArr2, jsonGenerator2);
        }

        private final void _writeArrayContents(JsonGenerator jsonGenerator, char[] cArr) throws IOException, JsonGenerationException {
            JsonGenerator jsonGenerator2 = jsonGenerator;
            char[] cArr2 = cArr;
            int length = cArr2.length;
            for (int i = 0; i < length; i++) {
                jsonGenerator2.writeString(cArr2, i, 1);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            ObjectNode createSchemaNode2 = createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_STRING);
            ObjectNode put = createSchemaNode2.put("type", PropertyTypeConstants.PROPERTY_TYPE_STRING);
            JsonNode put2 = createSchemaNode.put("items", (JsonNode) createSchemaNode2);
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

    @JacksonStdImpl
    public static final class IntArraySerializer extends ArraySerializerBase<int[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Integer.TYPE);

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IntArraySerializer() {
            super(int[].class, (BeanProperty) null);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            TypeSerializer typeSerializer2 = typeSerializer;
            return this;
        }

        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        public boolean isEmpty(int[] iArr) {
            int[] iArr2 = iArr;
            return iArr2 == null || iArr2.length == 0;
        }

        public boolean hasSingleElement(int[] iArr) {
            return iArr.length == 1;
        }

        public void serializeContents(int[] iArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            int[] iArr2 = iArr;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            SerializerProvider serializerProvider2 = serializerProvider;
            int length = iArr2.length;
            for (int i = 0; i < length; i++) {
                jsonGenerator2.writeNumber(iArr2[i]);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            JsonNode put = createSchemaNode.put("items", (JsonNode) createSchemaNode(PropertyTypeConstants.PROPERTY_TYPE_INTEGER));
            return createSchemaNode;
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonArrayFormatVisitor expectArrayFormat;
            JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
            JavaType javaType2 = javaType;
            if (jsonFormatVisitorWrapper2 != null && (expectArrayFormat = jsonFormatVisitorWrapper2.expectArrayFormat(javaType2)) != null) {
                expectArrayFormat.itemsFormat(JsonFormatTypes.INTEGER);
            }
        }
    }

    @JacksonStdImpl
    public static final class LongArraySerializer extends TypedPrimitiveArraySerializer<long[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Long.TYPE);

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LongArraySerializer() {
            super(long[].class);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LongArraySerializer(LongArraySerializer longArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer) {
            super(longArraySerializer, beanProperty, typeSerializer);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            ContainerSerializer<?> containerSerializer;
            new LongArraySerializer(this, this._property, typeSerializer);
            return containerSerializer;
        }

        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        public boolean isEmpty(long[] jArr) {
            long[] jArr2 = jArr;
            return jArr2 == null || jArr2.length == 0;
        }

        public boolean hasSingleElement(long[] jArr) {
            return jArr.length == 1;
        }

        public void serializeContents(long[] jArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            long[] jArr2 = jArr;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            SerializerProvider serializerProvider2 = serializerProvider;
            if (this._valueTypeSerializer != null) {
                int length = jArr2.length;
                for (int i = 0; i < length; i++) {
                    this._valueTypeSerializer.writeTypePrefixForScalar((Object) null, jsonGenerator2, Long.TYPE);
                    jsonGenerator2.writeNumber(jArr2[i]);
                    this._valueTypeSerializer.writeTypeSuffixForScalar((Object) null, jsonGenerator2);
                }
                return;
            }
            int length2 = jArr2.length;
            for (int i2 = 0; i2 < length2; i2++) {
                jsonGenerator2.writeNumber(jArr2[i2]);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            JsonNode put = createSchemaNode.put("items", (JsonNode) createSchemaNode("number", true));
            return createSchemaNode;
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonArrayFormatVisitor expectArrayFormat;
            JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
            JavaType javaType2 = javaType;
            if (jsonFormatVisitorWrapper2 != null && (expectArrayFormat = jsonFormatVisitorWrapper2.expectArrayFormat(javaType2)) != null) {
                expectArrayFormat.itemsFormat(JsonFormatTypes.NUMBER);
            }
        }
    }

    @JacksonStdImpl
    public static final class FloatArraySerializer extends TypedPrimitiveArraySerializer<float[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Float.TYPE);

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FloatArraySerializer() {
            super(float[].class);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FloatArraySerializer(FloatArraySerializer floatArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer) {
            super(floatArraySerializer, beanProperty, typeSerializer);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            ContainerSerializer<?> containerSerializer;
            new FloatArraySerializer(this, this._property, typeSerializer);
            return containerSerializer;
        }

        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        public boolean isEmpty(float[] fArr) {
            float[] fArr2 = fArr;
            return fArr2 == null || fArr2.length == 0;
        }

        public boolean hasSingleElement(float[] fArr) {
            return fArr.length == 1;
        }

        public void serializeContents(float[] fArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            float[] fArr2 = fArr;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            SerializerProvider serializerProvider2 = serializerProvider;
            if (this._valueTypeSerializer != null) {
                int length = fArr2.length;
                for (int i = 0; i < length; i++) {
                    this._valueTypeSerializer.writeTypePrefixForScalar((Object) null, jsonGenerator2, Float.TYPE);
                    jsonGenerator2.writeNumber(fArr2[i]);
                    this._valueTypeSerializer.writeTypeSuffixForScalar((Object) null, jsonGenerator2);
                }
                return;
            }
            int length2 = fArr2.length;
            for (int i2 = 0; i2 < length2; i2++) {
                jsonGenerator2.writeNumber(fArr2[i2]);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            JsonNode put = createSchemaNode.put("items", (JsonNode) createSchemaNode("number"));
            return createSchemaNode;
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonArrayFormatVisitor expectArrayFormat;
            JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
            JavaType javaType2 = javaType;
            if (jsonFormatVisitorWrapper2 != null && (expectArrayFormat = jsonFormatVisitorWrapper2.expectArrayFormat(javaType2)) != null) {
                expectArrayFormat.itemsFormat(JsonFormatTypes.NUMBER);
            }
        }
    }

    @JacksonStdImpl
    public static final class DoubleArraySerializer extends ArraySerializerBase<double[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Double.TYPE);

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DoubleArraySerializer() {
            super(double[].class, (BeanProperty) null);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            TypeSerializer typeSerializer2 = typeSerializer;
            return this;
        }

        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        public boolean isEmpty(double[] dArr) {
            double[] dArr2 = dArr;
            return dArr2 == null || dArr2.length == 0;
        }

        public boolean hasSingleElement(double[] dArr) {
            return dArr.length == 1;
        }

        public void serializeContents(double[] dArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            double[] dArr2 = dArr;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            SerializerProvider serializerProvider2 = serializerProvider;
            int length = dArr2.length;
            for (int i = 0; i < length; i++) {
                jsonGenerator2.writeNumber(dArr2[i]);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            SerializerProvider serializerProvider2 = serializerProvider;
            Type type2 = type;
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            JsonNode put = createSchemaNode.put("items", (JsonNode) createSchemaNode("number"));
            return createSchemaNode;
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            JsonArrayFormatVisitor expectArrayFormat;
            JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
            JavaType javaType2 = javaType;
            if (jsonFormatVisitorWrapper2 != null && (expectArrayFormat = jsonFormatVisitorWrapper2.expectArrayFormat(javaType2)) != null) {
                expectArrayFormat.itemsFormat(JsonFormatTypes.NUMBER);
            }
        }
    }
}
