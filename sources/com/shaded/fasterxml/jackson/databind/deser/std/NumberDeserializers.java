package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

public class NumberDeserializers {
    private static final HashSet<String> _classNames;

    public NumberDeserializers() {
    }

    static {
        HashSet<String> hashSet;
        new HashSet<>();
        _classNames = hashSet;
        Class[] clsArr = new Class[11];
        clsArr[0] = Boolean.class;
        Class[] clsArr2 = clsArr;
        clsArr2[1] = Byte.class;
        Class[] clsArr3 = clsArr2;
        clsArr3[2] = Short.class;
        Class[] clsArr4 = clsArr3;
        clsArr4[3] = Character.class;
        Class[] clsArr5 = clsArr4;
        clsArr5[4] = Integer.class;
        Class[] clsArr6 = clsArr5;
        clsArr6[5] = Long.class;
        Class[] clsArr7 = clsArr6;
        clsArr7[6] = Float.class;
        Class[] clsArr8 = clsArr7;
        clsArr8[7] = Double.class;
        Class[] clsArr9 = clsArr8;
        clsArr9[8] = Number.class;
        Class[] clsArr10 = clsArr9;
        clsArr10[9] = BigDecimal.class;
        Class[] clsArr11 = clsArr10;
        clsArr11[10] = BigInteger.class;
        Class[] clsArr12 = clsArr11;
        int length = clsArr12.length;
        for (int i = 0; i < length; i++) {
            boolean add = _classNames.add(clsArr12[i].getName());
        }
    }

    @Deprecated
    public static StdDeserializer<?>[] all() {
        StdDeserializer<?> stdDeserializer;
        StdDeserializer<?> stdDeserializer2;
        StdDeserializer<?> stdDeserializer3;
        StdDeserializer<?> stdDeserializer4;
        StdDeserializer<?> stdDeserializer5;
        StdDeserializer<?> stdDeserializer6;
        StdDeserializer<?> stdDeserializer7;
        StdDeserializer<?> stdDeserializer8;
        StdDeserializer<?> stdDeserializer9;
        StdDeserializer<?> stdDeserializer10;
        StdDeserializer<?> stdDeserializer11;
        StdDeserializer<?> stdDeserializer12;
        StdDeserializer<?> stdDeserializer13;
        StdDeserializer<?> stdDeserializer14;
        StdDeserializer<?> stdDeserializer15;
        StdDeserializer<?> stdDeserializer16;
        StdDeserializer<?> stdDeserializer17;
        StdDeserializer<?> stdDeserializer18;
        StdDeserializer stdDeserializer19;
        StdDeserializer<?>[] stdDeserializerArr = new StdDeserializer[19];
        new BooleanDeserializer(Boolean.class, (Boolean) null);
        stdDeserializerArr[0] = stdDeserializer;
        StdDeserializer<?>[] stdDeserializerArr2 = stdDeserializerArr;
        new ByteDeserializer(Byte.class, (Byte) null);
        stdDeserializerArr2[1] = stdDeserializer2;
        StdDeserializer<?>[] stdDeserializerArr3 = stdDeserializerArr2;
        new ShortDeserializer(Short.class, (Short) null);
        stdDeserializerArr3[2] = stdDeserializer3;
        StdDeserializer<?>[] stdDeserializerArr4 = stdDeserializerArr3;
        new CharacterDeserializer(Character.class, (Character) null);
        stdDeserializerArr4[3] = stdDeserializer4;
        StdDeserializer<?>[] stdDeserializerArr5 = stdDeserializerArr4;
        new IntegerDeserializer(Integer.class, (Integer) null);
        stdDeserializerArr5[4] = stdDeserializer5;
        StdDeserializer<?>[] stdDeserializerArr6 = stdDeserializerArr5;
        new LongDeserializer(Long.class, (Long) null);
        stdDeserializerArr6[5] = stdDeserializer6;
        StdDeserializer<?>[] stdDeserializerArr7 = stdDeserializerArr6;
        new FloatDeserializer(Float.class, (Float) null);
        stdDeserializerArr7[6] = stdDeserializer7;
        StdDeserializer<?>[] stdDeserializerArr8 = stdDeserializerArr7;
        new DoubleDeserializer(Double.class, (Double) null);
        stdDeserializerArr8[7] = stdDeserializer8;
        StdDeserializer<?>[] stdDeserializerArr9 = stdDeserializerArr8;
        new BooleanDeserializer(Boolean.TYPE, Boolean.FALSE);
        stdDeserializerArr9[8] = stdDeserializer9;
        StdDeserializer<?>[] stdDeserializerArr10 = stdDeserializerArr9;
        new ByteDeserializer(Byte.TYPE, (byte) 0);
        stdDeserializerArr10[9] = stdDeserializer10;
        StdDeserializer<?>[] stdDeserializerArr11 = stdDeserializerArr10;
        new ShortDeserializer(Short.TYPE, 0);
        stdDeserializerArr11[10] = stdDeserializer11;
        StdDeserializer<?>[] stdDeserializerArr12 = stdDeserializerArr11;
        new CharacterDeserializer(Character.TYPE, 0);
        stdDeserializerArr12[11] = stdDeserializer12;
        StdDeserializer<?>[] stdDeserializerArr13 = stdDeserializerArr12;
        new IntegerDeserializer(Integer.TYPE, 0);
        stdDeserializerArr13[12] = stdDeserializer13;
        StdDeserializer<?>[] stdDeserializerArr14 = stdDeserializerArr13;
        new LongDeserializer(Long.TYPE, 0L);
        stdDeserializerArr14[13] = stdDeserializer14;
        StdDeserializer<?>[] stdDeserializerArr15 = stdDeserializerArr14;
        new FloatDeserializer(Float.TYPE, Float.valueOf(0.0f));
        stdDeserializerArr15[14] = stdDeserializer15;
        StdDeserializer<?>[] stdDeserializerArr16 = stdDeserializerArr15;
        new DoubleDeserializer(Double.TYPE, Double.valueOf(0.0d));
        stdDeserializerArr16[15] = stdDeserializer16;
        StdDeserializer<?>[] stdDeserializerArr17 = stdDeserializerArr16;
        new NumberDeserializer();
        stdDeserializerArr17[16] = stdDeserializer17;
        StdDeserializer<?>[] stdDeserializerArr18 = stdDeserializerArr17;
        new BigDecimalDeserializer();
        stdDeserializerArr18[17] = stdDeserializer18;
        StdDeserializer<?>[] stdDeserializerArr19 = stdDeserializerArr18;
        StdDeserializer<?>[] stdDeserializerArr20 = stdDeserializerArr19;
        new BigIntegerDeserializer();
        stdDeserializerArr19[18] = stdDeserializer19;
        return stdDeserializerArr20;
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        Throwable th;
        StringBuilder sb;
        Class<?> cls2 = cls;
        String str2 = str;
        if (cls2.isPrimitive()) {
            if (cls2 == Integer.TYPE) {
                return IntegerDeserializer.primitiveInstance;
            }
            if (cls2 == Boolean.TYPE) {
                return BooleanDeserializer.primitiveInstance;
            }
            if (cls2 == Long.TYPE) {
                return LongDeserializer.primitiveInstance;
            }
            if (cls2 == Double.TYPE) {
                return DoubleDeserializer.primitiveInstance;
            }
            if (cls2 == Character.TYPE) {
                return CharacterDeserializer.primitiveInstance;
            }
            if (cls2 == Byte.TYPE) {
                return ByteDeserializer.primitiveInstance;
            }
            if (cls2 == Short.TYPE) {
                return ShortDeserializer.primitiveInstance;
            }
            if (cls2 == Float.TYPE) {
                return FloatDeserializer.primitiveInstance;
            }
        } else if (!_classNames.contains(str2)) {
            return null;
        } else {
            if (cls2 == Integer.class) {
                return IntegerDeserializer.wrapperInstance;
            }
            if (cls2 == Boolean.class) {
                return BooleanDeserializer.wrapperInstance;
            }
            if (cls2 == Long.class) {
                return LongDeserializer.wrapperInstance;
            }
            if (cls2 == Double.class) {
                return DoubleDeserializer.wrapperInstance;
            }
            if (cls2 == Character.class) {
                return CharacterDeserializer.wrapperInstance;
            }
            if (cls2 == Byte.class) {
                return ByteDeserializer.wrapperInstance;
            }
            if (cls2 == Short.class) {
                return ShortDeserializer.wrapperInstance;
            }
            if (cls2 == Float.class) {
                return FloatDeserializer.wrapperInstance;
            }
            if (cls2 == Number.class) {
                return NumberDeserializer.instance;
            }
            if (cls2 == BigDecimal.class) {
                return BigDecimalDeserializer.instance;
            }
            if (cls2 == BigInteger.class) {
                return BigIntegerDeserializer.instance;
            }
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Internal error: can't find deserializer for ").append(cls2.getName()).toString());
        throw th2;
    }

    protected static abstract class PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
        private static final long serialVersionUID = 1;
        protected final T _nullValue;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected PrimitiveOrWrapperDeserializer(Class<T> cls, T t) {
            super((Class<?>) cls);
            this._nullValue = t;
        }

        public final T getNullValue() {
            return this._nullValue;
        }
    }

    @JacksonStdImpl
    public static final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer<Boolean> {
        /* access modifiers changed from: private */
        public static final BooleanDeserializer primitiveInstance;
        private static final long serialVersionUID = 1;
        /* access modifiers changed from: private */
        public static final BooleanDeserializer wrapperInstance;

        static {
            BooleanDeserializer booleanDeserializer;
            BooleanDeserializer booleanDeserializer2;
            new BooleanDeserializer(Boolean.class, Boolean.FALSE);
            primitiveInstance = booleanDeserializer;
            new BooleanDeserializer(Boolean.TYPE, (Boolean) null);
            wrapperInstance = booleanDeserializer2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
            super(cls, bool);
        }

        public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseBoolean(jsonParser, deserializationContext);
        }

        public Boolean deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            TypeDeserializer typeDeserializer2 = typeDeserializer;
            return _parseBoolean(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static final class ByteDeserializer extends PrimitiveOrWrapperDeserializer<Byte> {
        /* access modifiers changed from: private */
        public static final ByteDeserializer primitiveInstance;
        private static final long serialVersionUID = 1;
        /* access modifiers changed from: private */
        public static final ByteDeserializer wrapperInstance;

        static {
            ByteDeserializer byteDeserializer;
            ByteDeserializer byteDeserializer2;
            new ByteDeserializer(Byte.TYPE, (byte) 0);
            primitiveInstance = byteDeserializer;
            new ByteDeserializer(Byte.class, (Byte) null);
            wrapperInstance = byteDeserializer2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ByteDeserializer(Class<Byte> cls, Byte b) {
            super(cls, b);
        }

        public Byte deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseByte(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static final class ShortDeserializer extends PrimitiveOrWrapperDeserializer<Short> {
        /* access modifiers changed from: private */
        public static final ShortDeserializer primitiveInstance;
        private static final long serialVersionUID = 1;
        /* access modifiers changed from: private */
        public static final ShortDeserializer wrapperInstance;

        static {
            ShortDeserializer shortDeserializer;
            ShortDeserializer shortDeserializer2;
            new ShortDeserializer(Short.class, 0);
            primitiveInstance = shortDeserializer;
            new ShortDeserializer(Short.TYPE, (Short) null);
            wrapperInstance = shortDeserializer2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ShortDeserializer(Class<Short> cls, Short sh) {
            super(cls, sh);
        }

        public Short deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseShort(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static final class CharacterDeserializer extends PrimitiveOrWrapperDeserializer<Character> {
        /* access modifiers changed from: private */
        public static final CharacterDeserializer primitiveInstance;
        private static final long serialVersionUID = 1;
        /* access modifiers changed from: private */
        public static final CharacterDeserializer wrapperInstance;

        static {
            CharacterDeserializer characterDeserializer;
            CharacterDeserializer characterDeserializer2;
            new CharacterDeserializer(Character.class, 0);
            primitiveInstance = characterDeserializer;
            new CharacterDeserializer(Character.TYPE, (Character) null);
            wrapperInstance = characterDeserializer2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CharacterDeserializer(Class<Character> cls, Character ch) {
            super(cls, ch);
        }

        public Character deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            JsonToken currentToken = jsonParser2.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                int intValue = jsonParser2.getIntValue();
                if (intValue >= 0 && intValue <= 65535) {
                    return Character.valueOf((char) intValue);
                }
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String text = jsonParser2.getText();
                if (text.length() == 1) {
                    return Character.valueOf(text.charAt(0));
                }
                if (text.length() == 0) {
                    return (Character) getEmptyValue();
                }
            }
            throw deserializationContext2.mappingException(this._valueClass, currentToken);
        }
    }

    @JacksonStdImpl
    public static final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer<Integer> {
        /* access modifiers changed from: private */
        public static final IntegerDeserializer primitiveInstance;
        private static final long serialVersionUID = 1;
        /* access modifiers changed from: private */
        public static final IntegerDeserializer wrapperInstance;

        static {
            IntegerDeserializer integerDeserializer;
            IntegerDeserializer integerDeserializer2;
            new IntegerDeserializer(Integer.class, 0);
            primitiveInstance = integerDeserializer;
            new IntegerDeserializer(Integer.TYPE, (Integer) null);
            wrapperInstance = integerDeserializer2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IntegerDeserializer(Class<Integer> cls, Integer num) {
            super(cls, num);
        }

        public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseInteger(jsonParser, deserializationContext);
        }

        public Integer deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            TypeDeserializer typeDeserializer2 = typeDeserializer;
            return _parseInteger(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static final class LongDeserializer extends PrimitiveOrWrapperDeserializer<Long> {
        /* access modifiers changed from: private */
        public static final LongDeserializer primitiveInstance;
        private static final long serialVersionUID = 1;
        /* access modifiers changed from: private */
        public static final LongDeserializer wrapperInstance;

        static {
            LongDeserializer longDeserializer;
            LongDeserializer longDeserializer2;
            new LongDeserializer(Long.class, 0L);
            primitiveInstance = longDeserializer;
            new LongDeserializer(Long.TYPE, (Long) null);
            wrapperInstance = longDeserializer2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LongDeserializer(Class<Long> cls, Long l) {
            super(cls, l);
        }

        public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseLong(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static final class FloatDeserializer extends PrimitiveOrWrapperDeserializer<Float> {
        /* access modifiers changed from: private */
        public static final FloatDeserializer primitiveInstance;
        private static final long serialVersionUID = 1;
        /* access modifiers changed from: private */
        public static final FloatDeserializer wrapperInstance;

        static {
            FloatDeserializer floatDeserializer;
            FloatDeserializer floatDeserializer2;
            new FloatDeserializer(Float.class, Float.valueOf(0.0f));
            primitiveInstance = floatDeserializer;
            new FloatDeserializer(Float.TYPE, (Float) null);
            wrapperInstance = floatDeserializer2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FloatDeserializer(Class<Float> cls, Float f) {
            super(cls, f);
        }

        public Float deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseFloat(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static final class DoubleDeserializer extends PrimitiveOrWrapperDeserializer<Double> {
        /* access modifiers changed from: private */
        public static final DoubleDeserializer primitiveInstance;
        private static final long serialVersionUID = 1;
        /* access modifiers changed from: private */
        public static final DoubleDeserializer wrapperInstance;

        static {
            DoubleDeserializer doubleDeserializer;
            DoubleDeserializer doubleDeserializer2;
            new DoubleDeserializer(Double.class, Double.valueOf(0.0d));
            primitiveInstance = doubleDeserializer;
            new DoubleDeserializer(Double.TYPE, (Double) null);
            wrapperInstance = doubleDeserializer2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DoubleDeserializer(Class<Double> cls, Double d) {
            super(cls, d);
        }

        public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return _parseDouble(jsonParser, deserializationContext);
        }

        public Double deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            TypeDeserializer typeDeserializer2 = typeDeserializer;
            return _parseDouble(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static final class NumberDeserializer extends StdScalarDeserializer<Number> {
        public static final NumberDeserializer instance;

        static {
            NumberDeserializer numberDeserializer;
            new NumberDeserializer();
            instance = numberDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public NumberDeserializer() {
            super((Class<?>) Number.class);
        }

        public Number deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Number number;
            Number number2;
            Number number3;
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            JsonToken currentToken = jsonParser2.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                if (deserializationContext2.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonParser2.getBigIntegerValue();
                }
                return jsonParser2.getNumberValue();
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                if (deserializationContext2.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser2.getDecimalValue();
                }
                return Double.valueOf(jsonParser2.getDoubleValue());
            } else if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser2.getText().trim();
                try {
                    if (trim.indexOf(46) >= 0) {
                        if (deserializationContext2.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                            new BigDecimal(trim);
                            return number3;
                        }
                        new Double(trim);
                        return number2;
                    } else if (deserializationContext2.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                        new BigInteger(trim);
                        return number;
                    } else {
                        long parseLong = Long.parseLong(trim);
                        if (parseLong > 2147483647L || parseLong < -2147483648L) {
                            return Long.valueOf(parseLong);
                        }
                        return Integer.valueOf((int) parseLong);
                    }
                } catch (IllegalArgumentException e) {
                    IllegalArgumentException illegalArgumentException = e;
                    throw deserializationContext2.weirdStringException(trim, this._valueClass, "not a valid number");
                }
            } else {
                throw deserializationContext2.mappingException(this._valueClass, currentToken);
            }
        }

        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            TypeDeserializer typeDeserializer2 = typeDeserializer;
            switch (jsonParser2.getCurrentToken()) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                case VALUE_STRING:
                    return deserialize(jsonParser2, deserializationContext2);
                default:
                    return typeDeserializer2.deserializeTypedFromScalar(jsonParser2, deserializationContext2);
            }
        }
    }

    @JacksonStdImpl
    public static class BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
        public static final BigIntegerDeserializer instance;

        static {
            BigIntegerDeserializer bigIntegerDeserializer;
            new BigIntegerDeserializer();
            instance = bigIntegerDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public BigIntegerDeserializer() {
            super((Class<?>) BigInteger.class);
        }

        public BigInteger deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            BigInteger bigInteger;
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            JsonToken currentToken = jsonParser2.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                switch (jsonParser2.getNumberType()) {
                    case INT:
                    case LONG:
                        return BigInteger.valueOf(jsonParser2.getLongValue());
                }
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser2.getDecimalValue().toBigInteger();
            } else {
                if (currentToken != JsonToken.VALUE_STRING) {
                    throw deserializationContext2.mappingException(this._valueClass, currentToken);
                }
            }
            String trim = jsonParser2.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            try {
                BigInteger bigInteger2 = bigInteger;
                new BigInteger(trim);
                return bigInteger2;
            } catch (IllegalArgumentException e) {
                IllegalArgumentException illegalArgumentException = e;
                throw deserializationContext2.weirdStringException(trim, this._valueClass, "not a valid representation");
            }
        }
    }

    @JacksonStdImpl
    public static class BigDecimalDeserializer extends StdScalarDeserializer<BigDecimal> {
        public static final BigDecimalDeserializer instance;

        static {
            BigDecimalDeserializer bigDecimalDeserializer;
            new BigDecimalDeserializer();
            instance = bigDecimalDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public BigDecimalDeserializer() {
            super((Class<?>) BigDecimal.class);
        }

        public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            BigDecimal bigDecimal;
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            JsonToken currentToken = jsonParser2.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                return jsonParser2.getDecimalValue();
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser2.getText().trim();
                if (trim.length() == 0) {
                    return null;
                }
                try {
                    BigDecimal bigDecimal2 = bigDecimal;
                    new BigDecimal(trim);
                    return bigDecimal2;
                } catch (IllegalArgumentException e) {
                    IllegalArgumentException illegalArgumentException = e;
                    throw deserializationContext2.weirdStringException(trim, this._valueClass, "not a valid representation");
                }
            } else {
                throw deserializationContext2.mappingException(this._valueClass, currentToken);
            }
        }
    }
}
