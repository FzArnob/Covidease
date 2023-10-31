package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.Base64Variants;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;

public abstract class PrimitiveArrayDeserializers<T> extends StdDeserializer<T> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected PrimitiveArrayDeserializers(Class<T> cls) {
        super((Class<?>) cls);
    }

    public static JsonDeserializer<?> forType(Class<?> cls) {
        Throwable th;
        JsonDeserializer<?> jsonDeserializer;
        JsonDeserializer<?> jsonDeserializer2;
        JsonDeserializer<?> jsonDeserializer3;
        JsonDeserializer<?> jsonDeserializer4;
        JsonDeserializer<?> jsonDeserializer5;
        JsonDeserializer<?> jsonDeserializer6;
        Class<?> cls2 = cls;
        if (cls2 == Integer.TYPE) {
            return IntDeser.instance;
        }
        if (cls2 == Long.TYPE) {
            return LongDeser.instance;
        }
        if (cls2 == Byte.TYPE) {
            new ByteDeser();
            return jsonDeserializer6;
        } else if (cls2 == Short.TYPE) {
            new ShortDeser();
            return jsonDeserializer5;
        } else if (cls2 == Float.TYPE) {
            new FloatDeser();
            return jsonDeserializer4;
        } else if (cls2 == Double.TYPE) {
            new DoubleDeser();
            return jsonDeserializer3;
        } else if (cls2 == Boolean.TYPE) {
            new BooleanDeser();
            return jsonDeserializer2;
        } else if (cls2 == Character.TYPE) {
            new CharDeser();
            return jsonDeserializer;
        } else {
            Throwable th2 = th;
            new IllegalStateException();
            throw th2;
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    @JacksonStdImpl
    static final class CharDeser extends PrimitiveArrayDeserializers<char[]> {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CharDeser() {
            super(char[].class);
        }

        public char[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            StringBuilder sb;
            StringBuilder sb2;
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            JsonToken currentToken = jsonParser2.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                char[] textCharacters = jsonParser2.getTextCharacters();
                int textOffset = jsonParser2.getTextOffset();
                int textLength = jsonParser2.getTextLength();
                char[] cArr = new char[textLength];
                System.arraycopy(textCharacters, textOffset, cArr, 0, textLength);
                return cArr;
            } else if (jsonParser2.isExpectedStartArrayToken()) {
                new StringBuilder(64);
                StringBuilder sb3 = sb;
                while (true) {
                    JsonToken nextToken = jsonParser2.nextToken();
                    JsonToken jsonToken = nextToken;
                    if (nextToken == JsonToken.END_ARRAY) {
                        return sb3.toString().toCharArray();
                    }
                    if (jsonToken != JsonToken.VALUE_STRING) {
                        throw deserializationContext2.mappingException((Class<?>) Character.TYPE);
                    }
                    String text = jsonParser2.getText();
                    if (text.length() != 1) {
                        new StringBuilder();
                        throw JsonMappingException.from(jsonParser2, sb2.append("Can not convert a JSON String of length ").append(text.length()).append(" into a char element of char array").toString());
                    }
                    StringBuilder append = sb3.append(text.charAt(0));
                }
            } else {
                if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                    Object embeddedObject = jsonParser2.getEmbeddedObject();
                    if (embeddedObject == null) {
                        return null;
                    }
                    if (embeddedObject instanceof char[]) {
                        return (char[]) embeddedObject;
                    }
                    if (embeddedObject instanceof String) {
                        return ((String) embeddedObject).toCharArray();
                    }
                    if (embeddedObject instanceof byte[]) {
                        return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false).toCharArray();
                    }
                }
                throw deserializationContext2.mappingException((Class<?>) this._valueClass);
            }
        }
    }

    @JacksonStdImpl
    static final class BooleanDeser extends PrimitiveArrayDeserializers<boolean[]> {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public BooleanDeser() {
            super(boolean[].class);
        }

        public boolean[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (!jsonParser2.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser2, deserializationContext2);
            }
            ArrayBuilders.BooleanBuilder booleanBuilder = deserializationContext2.getArrayBuilders().getBooleanBuilder();
            boolean[] zArr = (boolean[]) booleanBuilder.resetAndStart();
            int i = 0;
            while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
                boolean _parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser2, deserializationContext2);
                if (i >= zArr.length) {
                    zArr = (boolean[]) booleanBuilder.appendCompletedChunk(zArr, i);
                    i = 0;
                }
                int i2 = i;
                i++;
                zArr[i2] = _parseBooleanPrimitive;
            }
            return (boolean[]) booleanBuilder.completeAndClearBuffer(zArr, i);
        }

        private final boolean[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (jsonParser2.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser2.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext2.mappingException((Class<?>) this._valueClass);
            }
            return new boolean[]{_parseBooleanPrimitive(jsonParser2, deserializationContext2)};
        }
    }

    @JacksonStdImpl
    static final class ByteDeser extends PrimitiveArrayDeserializers<byte[]> {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ByteDeser() {
            super(byte[].class);
        }

        public byte[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            byte b;
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            JsonToken currentToken = jsonParser2.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                return jsonParser2.getBinaryValue(deserializationContext2.getBase64Variant());
            }
            if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object embeddedObject = jsonParser2.getEmbeddedObject();
                if (embeddedObject == null) {
                    return null;
                }
                if (embeddedObject instanceof byte[]) {
                    return (byte[]) embeddedObject;
                }
            }
            if (!jsonParser2.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser2, deserializationContext2);
            }
            ArrayBuilders.ByteBuilder byteBuilder = deserializationContext2.getArrayBuilders().getByteBuilder();
            byte[] bArr = (byte[]) byteBuilder.resetAndStart();
            int i = 0;
            while (true) {
                JsonToken nextToken = jsonParser2.nextToken();
                JsonToken jsonToken = nextToken;
                if (nextToken == JsonToken.END_ARRAY) {
                    return (byte[]) byteBuilder.completeAndClearBuffer(bArr, i);
                }
                if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
                    b = jsonParser2.getByteValue();
                } else if (jsonToken != JsonToken.VALUE_NULL) {
                    throw deserializationContext2.mappingException(this._valueClass.getComponentType());
                } else {
                    b = 0;
                }
                if (i >= bArr.length) {
                    bArr = (byte[]) byteBuilder.appendCompletedChunk(bArr, i);
                    i = 0;
                }
                int i2 = i;
                i++;
                bArr[i2] = b;
            }
        }

        private final byte[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            byte b;
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (jsonParser2.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser2.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext2.mappingException((Class<?>) this._valueClass);
            }
            JsonToken currentToken = jsonParser2.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                b = jsonParser2.getByteValue();
            } else if (currentToken != JsonToken.VALUE_NULL) {
                throw deserializationContext2.mappingException(this._valueClass.getComponentType());
            } else {
                b = 0;
            }
            return new byte[]{b};
        }
    }

    @JacksonStdImpl
    static final class ShortDeser extends PrimitiveArrayDeserializers<short[]> {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ShortDeser() {
            super(short[].class);
        }

        public short[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (!jsonParser2.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser2, deserializationContext2);
            }
            ArrayBuilders.ShortBuilder shortBuilder = deserializationContext2.getArrayBuilders().getShortBuilder();
            short[] sArr = (short[]) shortBuilder.resetAndStart();
            int i = 0;
            while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
                short _parseShortPrimitive = _parseShortPrimitive(jsonParser2, deserializationContext2);
                if (i >= sArr.length) {
                    sArr = (short[]) shortBuilder.appendCompletedChunk(sArr, i);
                    i = 0;
                }
                int i2 = i;
                i++;
                sArr[i2] = _parseShortPrimitive;
            }
            return (short[]) shortBuilder.completeAndClearBuffer(sArr, i);
        }

        private final short[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (jsonParser2.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser2.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext2.mappingException((Class<?>) this._valueClass);
            }
            return new short[]{_parseShortPrimitive(jsonParser2, deserializationContext2)};
        }
    }

    @JacksonStdImpl
    static final class IntDeser extends PrimitiveArrayDeserializers<int[]> {
        public static final IntDeser instance;
        private static final long serialVersionUID = 1;

        static {
            IntDeser intDeser;
            new IntDeser();
            instance = intDeser;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IntDeser() {
            super(int[].class);
        }

        public int[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (!jsonParser2.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser2, deserializationContext2);
            }
            ArrayBuilders.IntBuilder intBuilder = deserializationContext2.getArrayBuilders().getIntBuilder();
            int[] iArr = (int[]) intBuilder.resetAndStart();
            int i = 0;
            while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
                int _parseIntPrimitive = _parseIntPrimitive(jsonParser2, deserializationContext2);
                if (i >= iArr.length) {
                    iArr = (int[]) intBuilder.appendCompletedChunk(iArr, i);
                    i = 0;
                }
                int i2 = i;
                i++;
                iArr[i2] = _parseIntPrimitive;
            }
            return (int[]) intBuilder.completeAndClearBuffer(iArr, i);
        }

        private final int[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (jsonParser2.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser2.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext2.mappingException((Class<?>) this._valueClass);
            }
            return new int[]{_parseIntPrimitive(jsonParser2, deserializationContext2)};
        }
    }

    @JacksonStdImpl
    static final class LongDeser extends PrimitiveArrayDeserializers<long[]> {
        public static final LongDeser instance;
        private static final long serialVersionUID = 1;

        static {
            LongDeser longDeser;
            new LongDeser();
            instance = longDeser;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LongDeser() {
            super(long[].class);
        }

        public long[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (!jsonParser2.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser2, deserializationContext2);
            }
            ArrayBuilders.LongBuilder longBuilder = deserializationContext2.getArrayBuilders().getLongBuilder();
            long[] jArr = (long[]) longBuilder.resetAndStart();
            int i = 0;
            while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
                long _parseLongPrimitive = _parseLongPrimitive(jsonParser2, deserializationContext2);
                if (i >= jArr.length) {
                    jArr = (long[]) longBuilder.appendCompletedChunk(jArr, i);
                    i = 0;
                }
                int i2 = i;
                i++;
                jArr[i2] = _parseLongPrimitive;
            }
            return (long[]) longBuilder.completeAndClearBuffer(jArr, i);
        }

        private final long[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (jsonParser2.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser2.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext2.mappingException((Class<?>) this._valueClass);
            }
            return new long[]{_parseLongPrimitive(jsonParser2, deserializationContext2)};
        }
    }

    @JacksonStdImpl
    static final class FloatDeser extends PrimitiveArrayDeserializers<float[]> {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FloatDeser() {
            super(float[].class);
        }

        public float[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (!jsonParser2.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser2, deserializationContext2);
            }
            ArrayBuilders.FloatBuilder floatBuilder = deserializationContext2.getArrayBuilders().getFloatBuilder();
            float[] fArr = (float[]) floatBuilder.resetAndStart();
            int i = 0;
            while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
                float _parseFloatPrimitive = _parseFloatPrimitive(jsonParser2, deserializationContext2);
                if (i >= fArr.length) {
                    fArr = (float[]) floatBuilder.appendCompletedChunk(fArr, i);
                    i = 0;
                }
                int i2 = i;
                i++;
                fArr[i2] = _parseFloatPrimitive;
            }
            return (float[]) floatBuilder.completeAndClearBuffer(fArr, i);
        }

        private final float[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (jsonParser2.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser2.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext2.mappingException((Class<?>) this._valueClass);
            }
            return new float[]{_parseFloatPrimitive(jsonParser2, deserializationContext2)};
        }
    }

    @JacksonStdImpl
    static final class DoubleDeser extends PrimitiveArrayDeserializers<double[]> {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DoubleDeser() {
            super(double[].class);
        }

        public double[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (!jsonParser2.isExpectedStartArrayToken()) {
                return handleNonArray(jsonParser2, deserializationContext2);
            }
            ArrayBuilders.DoubleBuilder doubleBuilder = deserializationContext2.getArrayBuilders().getDoubleBuilder();
            double[] dArr = (double[]) doubleBuilder.resetAndStart();
            int i = 0;
            while (jsonParser2.nextToken() != JsonToken.END_ARRAY) {
                double _parseDoublePrimitive = _parseDoublePrimitive(jsonParser2, deserializationContext2);
                if (i >= dArr.length) {
                    dArr = (double[]) doubleBuilder.appendCompletedChunk(dArr, i);
                    i = 0;
                }
                int i2 = i;
                i++;
                dArr[i2] = _parseDoublePrimitive;
            }
            return (double[]) doubleBuilder.completeAndClearBuffer(dArr, i);
        }

        private final double[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (jsonParser2.getCurrentToken() == JsonToken.VALUE_STRING && deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser2.getText().length() == 0) {
                return null;
            }
            if (!deserializationContext2.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                throw deserializationContext2.mappingException((Class<?>) this._valueClass);
            }
            return new double[]{_parseDoublePrimitive(jsonParser2, deserializationContext2)};
        }
    }
}
