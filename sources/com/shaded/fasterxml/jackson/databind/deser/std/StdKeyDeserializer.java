package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.p005io.NumberInput;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public abstract class StdKeyDeserializer extends KeyDeserializer implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Class<?> _keyClass;

    /* access modifiers changed from: protected */
    public abstract Object _parse(String str, DeserializationContext deserializationContext) throws Exception;

    protected StdKeyDeserializer(Class<?> cls) {
        this._keyClass = cls;
    }

    public final Object deserializeKey(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StringBuilder sb;
        String str2 = str;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (str2 == null) {
            return null;
        }
        try {
            Object _parse = _parse(str2, deserializationContext2);
            if (_parse != null) {
                return _parse;
            }
            if (this._keyClass.isEnum() && deserializationContext2.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return null;
            }
            throw deserializationContext2.weirdKeyException(this._keyClass, str2, "not a valid representation");
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            throw deserializationContext2.weirdKeyException(this._keyClass, str2, sb.append("not a valid representation: ").append(exc.getMessage()).toString());
        }
    }

    public Class<?> getKeyClass() {
        return this._keyClass;
    }

    /* access modifiers changed from: protected */
    public int _parseInt(String str) throws IllegalArgumentException {
        return Integer.parseInt(str);
    }

    /* access modifiers changed from: protected */
    public long _parseLong(String str) throws IllegalArgumentException {
        return Long.parseLong(str);
    }

    /* access modifiers changed from: protected */
    public double _parseDouble(String str) throws IllegalArgumentException {
        return NumberInput.parseDouble(str);
    }

    @JacksonStdImpl
    static final class StringKD extends StdKeyDeserializer {
        private static final StringKD sObject;
        private static final StringKD sString;
        private static final long serialVersionUID = 1;

        static {
            StringKD stringKD;
            StringKD stringKD2;
            new StringKD(String.class);
            sString = stringKD;
            new StringKD(Object.class);
            sObject = stringKD2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private StringKD(Class<?> cls) {
            super(cls);
        }

        public static StringKD forType(Class<?> cls) {
            StringKD stringKD;
            Class<?> cls2 = cls;
            if (cls2 == String.class) {
                return sString;
            }
            if (cls2 == Object.class) {
                return sObject;
            }
            new StringKD(cls2);
            return stringKD;
        }

        public String _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return str;
        }
    }

    @JacksonStdImpl
    static final class BoolKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        BoolKD() {
            super(Boolean.class);
        }

        public Boolean _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            String str2 = str;
            DeserializationContext deserializationContext2 = deserializationContext;
            if ("true".equals(str2)) {
                return Boolean.TRUE;
            }
            if ("false".equals(str2)) {
                return Boolean.FALSE;
            }
            throw deserializationContext2.weirdKeyException(this._keyClass, str2, "value not 'true' or 'false'");
        }
    }

    @JacksonStdImpl
    static final class ByteKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        ByteKD() {
            super(Byte.class);
        }

        public Byte _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            String str2 = str;
            DeserializationContext deserializationContext2 = deserializationContext;
            int _parseInt = _parseInt(str2);
            if (_parseInt >= -128 && _parseInt <= 255) {
                return Byte.valueOf((byte) _parseInt);
            }
            throw deserializationContext2.weirdKeyException(this._keyClass, str2, "overflow, value can not be represented as 8-bit value");
        }
    }

    @JacksonStdImpl
    static final class ShortKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        ShortKD() {
            super(Integer.class);
        }

        public Short _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            String str2 = str;
            DeserializationContext deserializationContext2 = deserializationContext;
            int _parseInt = _parseInt(str2);
            if (_parseInt >= -32768 && _parseInt <= 32767) {
                return Short.valueOf((short) _parseInt);
            }
            throw deserializationContext2.weirdKeyException(this._keyClass, str2, "overflow, value can not be represented as 16-bit value");
        }
    }

    @JacksonStdImpl
    static final class CharKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        CharKD() {
            super(Character.class);
        }

        public Character _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            String str2 = str;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (str2.length() == 1) {
                return Character.valueOf(str2.charAt(0));
            }
            throw deserializationContext2.weirdKeyException(this._keyClass, str2, "can only convert 1-character Strings");
        }
    }

    @JacksonStdImpl
    static final class IntKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        IntKD() {
            super(Integer.class);
        }

        public Integer _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return Integer.valueOf(_parseInt(str));
        }
    }

    @JacksonStdImpl
    static final class LongKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        LongKD() {
            super(Long.class);
        }

        public Long _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return Long.valueOf(_parseLong(str));
        }
    }

    @JacksonStdImpl
    static final class DoubleKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        DoubleKD() {
            super(Double.class);
        }

        public Double _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return Double.valueOf(_parseDouble(str));
        }
    }

    @JacksonStdImpl
    static final class FloatKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        FloatKD() {
            super(Float.class);
        }

        public Float _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return Float.valueOf((float) _parseDouble(str));
        }
    }

    @JacksonStdImpl
    static final class LocaleKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        protected JdkDeserializers.LocaleDeserializer _localeDeserializer;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        LocaleKD() {
            super(Locale.class);
            JdkDeserializers.LocaleDeserializer localeDeserializer;
            new JdkDeserializers.LocaleDeserializer();
            this._localeDeserializer = localeDeserializer;
        }

        /* access modifiers changed from: protected */
        public Locale _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            String str2 = str;
            DeserializationContext deserializationContext2 = deserializationContext;
            try {
                return this._localeDeserializer._deserialize(str2, deserializationContext2);
            } catch (IOException e) {
                IOException iOException = e;
                throw deserializationContext2.weirdKeyException(this._keyClass, str2, "unable to parse key as locale");
            }
        }
    }

    static final class DelegatingKD extends KeyDeserializer implements Serializable {
        private static final long serialVersionUID = 1;
        protected final JsonDeserializer<?> _delegate;
        protected final Class<?> _keyClass;

        protected DelegatingKD(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
            this._keyClass = cls;
            this._delegate = jsonDeserializer;
        }

        public final Object deserializeKey(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            StringBuilder sb;
            String str2 = str;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (str2 == null) {
                return null;
            }
            try {
                Object deserialize = this._delegate.deserialize(deserializationContext2.getParser(), deserializationContext2);
                if (deserialize != null) {
                    return deserialize;
                }
                throw deserializationContext2.weirdKeyException(this._keyClass, str2, "not a valid representation");
            } catch (Exception e) {
                Exception exc = e;
                new StringBuilder();
                throw deserializationContext2.weirdKeyException(this._keyClass, str2, sb.append("not a valid representation: ").append(exc.getMessage()).toString());
            }
        }

        public Class<?> getKeyClass() {
            return this._keyClass;
        }
    }

    @JacksonStdImpl
    static final class EnumKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        protected final AnnotatedMethod _factory;
        protected final EnumResolver<?> _resolver;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected EnumKD(com.shaded.fasterxml.jackson.databind.util.EnumResolver<?> r6, com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod r7) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r2 = r7
                r3 = r0
                r4 = r1
                java.lang.Class r4 = r4.getEnumClass()
                r3.<init>(r4)
                r3 = r0
                r4 = r1
                r3._resolver = r4
                r3 = r0
                r4 = r2
                r3._factory = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.EnumKD.<init>(com.shaded.fasterxml.jackson.databind.util.EnumResolver, com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod):void");
        }

        public Object _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            String str2 = str;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (this._factory != null) {
                try {
                    return this._factory.call1(str2);
                } catch (Exception e) {
                    ClassUtil.unwrapAndThrowAsIAE(e);
                }
            }
            Object findEnum = this._resolver.findEnum(str2);
            if (findEnum != null || deserializationContext2.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return findEnum;
            }
            throw deserializationContext2.weirdKeyException(this._keyClass, str2, "not one of values for Enum class");
        }
    }

    static final class StringCtorKeyDeserializer extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        protected final Constructor<?> _ctor;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public StringCtorKeyDeserializer(java.lang.reflect.Constructor<?> r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                java.lang.Class r3 = r3.getDeclaringClass()
                r2.<init>(r3)
                r2 = r0
                r3 = r1
                r2._ctor = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.StringCtorKeyDeserializer.<init>(java.lang.reflect.Constructor):void");
        }

        public Object _parse(String str, DeserializationContext deserializationContext) throws Exception {
            DeserializationContext deserializationContext2 = deserializationContext;
            return this._ctor.newInstance(new Object[]{str});
        }
    }

    static final class StringFactoryKeyDeserializer extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        final Method _factoryMethod;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public StringFactoryKeyDeserializer(java.lang.reflect.Method r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                java.lang.Class r3 = r3.getDeclaringClass()
                r2.<init>(r3)
                r2 = r0
                r3 = r1
                r2._factoryMethod = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.StringFactoryKeyDeserializer.<init>(java.lang.reflect.Method):void");
        }

        public Object _parse(String str, DeserializationContext deserializationContext) throws Exception {
            DeserializationContext deserializationContext2 = deserializationContext;
            return this._factoryMethod.invoke((Object) null, new Object[]{str});
        }
    }

    @JacksonStdImpl
    static final class DateKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected DateKD() {
            super(Date.class);
        }

        public Object _parse(String str, DeserializationContext deserializationContext) throws IllegalArgumentException, JsonMappingException {
            return deserializationContext.parseDate(str);
        }
    }

    @JacksonStdImpl
    static final class CalendarKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected CalendarKD() {
            super(Calendar.class);
        }

        public Object _parse(String str, DeserializationContext deserializationContext) throws IllegalArgumentException, JsonMappingException {
            DeserializationContext deserializationContext2 = deserializationContext;
            Date parseDate = deserializationContext2.parseDate(str);
            return parseDate == null ? null : deserializationContext2.constructCalendar(parseDate);
        }
    }

    @JacksonStdImpl
    static final class UuidKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected UuidKD() {
            super(UUID.class);
        }

        public Object _parse(String str, DeserializationContext deserializationContext) throws IllegalArgumentException, JsonMappingException {
            DeserializationContext deserializationContext2 = deserializationContext;
            return UUID.fromString(str);
        }
    }
}
