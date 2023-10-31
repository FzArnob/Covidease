package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.lang.reflect.Method;

public class EnumDeserializer extends StdScalarDeserializer<Enum<?>> {
    private static final long serialVersionUID = -5893263645879532318L;
    protected final EnumResolver<?> _resolver;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnumDeserializer(EnumResolver<?> enumResolver) {
        super((Class<?>) Enum.class);
        this._resolver = enumResolver;
    }

    public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMethod annotatedMethod) {
        Class<Long> cls2;
        Throwable th;
        StringBuilder sb;
        JsonDeserializer<?> jsonDeserializer;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        Class<?> cls3 = cls;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        Class<?> rawParameterType = annotatedMethod2.getRawParameterType(0);
        if (rawParameterType == String.class) {
            cls2 = null;
        } else if (rawParameterType == Integer.TYPE || rawParameterType == Integer.class) {
            cls2 = Integer.class;
        } else if (rawParameterType == Long.TYPE || rawParameterType == Long.class) {
            cls2 = Long.class;
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Parameter #0 type for factory method (").append(annotatedMethod2).append(") not suitable, must be java.lang.String or int/Integer/long/Long").toString());
            throw th2;
        }
        if (deserializationConfig2.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(annotatedMethod2.getMember());
        }
        new FactoryBasedDeserializer(cls3, annotatedMethod2, cls2);
        return jsonDeserializer;
    }

    public boolean isCachable() {
        return true;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Enum<?>] */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Enum<?>] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Enum<?> deserialize(com.shaded.fasterxml.jackson.core.JsonParser r13, com.shaded.fasterxml.jackson.databind.DeserializationContext r14) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonProcessingException {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r6 = r1
            com.shaded.fasterxml.jackson.core.JsonToken r6 = r6.getCurrentToken()
            r3 = r6
            r6 = r3
            com.shaded.fasterxml.jackson.core.JsonToken r7 = com.shaded.fasterxml.jackson.core.JsonToken.VALUE_STRING
            if (r6 == r7) goto L_0x0013
            r6 = r3
            com.shaded.fasterxml.jackson.core.JsonToken r7 = com.shaded.fasterxml.jackson.core.JsonToken.FIELD_NAME
            if (r6 != r7) goto L_0x0060
        L_0x0013:
            r6 = r1
            java.lang.String r6 = r6.getText()
            r4 = r6
            r6 = r0
            com.shaded.fasterxml.jackson.databind.util.EnumResolver<?> r6 = r6._resolver
            r7 = r4
            java.lang.Enum r6 = r6.findEnum(r7)
            r5 = r6
            r6 = r5
            if (r6 != 0) goto L_0x005d
            r6 = r2
            com.shaded.fasterxml.jackson.databind.DeserializationFeature r7 = com.shaded.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT
            boolean r6 = r6.isEnabled(r7)
            if (r6 == 0) goto L_0x0043
            r6 = r4
            int r6 = r6.length()
            if (r6 == 0) goto L_0x0040
            r6 = r4
            java.lang.String r6 = r6.trim()
            int r6 = r6.length()
            if (r6 != 0) goto L_0x0043
        L_0x0040:
            r6 = 0
            r0 = r6
        L_0x0042:
            return r0
        L_0x0043:
            r6 = r2
            com.shaded.fasterxml.jackson.databind.DeserializationFeature r7 = com.shaded.fasterxml.jackson.databind.DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL
            boolean r6 = r6.isEnabled(r7)
            if (r6 != 0) goto L_0x005d
            r6 = r2
            r7 = r4
            r8 = r0
            com.shaded.fasterxml.jackson.databind.util.EnumResolver<?> r8 = r8._resolver
            java.lang.Class r8 = r8.getEnumClass()
            java.lang.String r9 = "value not one of declared Enum instance names"
            com.shaded.fasterxml.jackson.databind.JsonMappingException r6 = r6.weirdStringException(r7, r8, r9)
            throw r6
        L_0x005d:
            r6 = r5
            r0 = r6
            goto L_0x0042
        L_0x0060:
            r6 = r3
            com.shaded.fasterxml.jackson.core.JsonToken r7 = com.shaded.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT
            if (r6 != r7) goto L_0x00cd
            r6 = r2
            com.shaded.fasterxml.jackson.databind.DeserializationFeature r7 = com.shaded.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS
            boolean r6 = r6.isEnabled(r7)
            if (r6 == 0) goto L_0x0077
            r6 = r2
            java.lang.String r7 = "Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)"
            com.shaded.fasterxml.jackson.databind.JsonMappingException r6 = r6.mappingException((java.lang.String) r7)
            throw r6
        L_0x0077:
            r6 = r1
            int r6 = r6.getIntValue()
            r4 = r6
            r6 = r0
            com.shaded.fasterxml.jackson.databind.util.EnumResolver<?> r6 = r6._resolver
            r7 = r4
            java.lang.Enum r6 = r6.getEnum(r7)
            r5 = r6
            r6 = r5
            if (r6 != 0) goto L_0x00c9
            r6 = r2
            com.shaded.fasterxml.jackson.databind.DeserializationFeature r7 = com.shaded.fasterxml.jackson.databind.DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL
            boolean r6 = r6.isEnabled(r7)
            if (r6 != 0) goto L_0x00c9
            r6 = r2
            r7 = r4
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r8 = r0
            com.shaded.fasterxml.jackson.databind.util.EnumResolver<?> r8 = r8._resolver
            java.lang.Class r8 = r8.getEnumClass()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r11 = r9
            r9 = r11
            r10 = r11
            r10.<init>()
            java.lang.String r10 = "index value outside legal index range [0.."
            java.lang.StringBuilder r9 = r9.append(r10)
            r10 = r0
            com.shaded.fasterxml.jackson.databind.util.EnumResolver<?> r10 = r10._resolver
            int r10 = r10.lastValidIndex()
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r10 = "]"
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            com.shaded.fasterxml.jackson.databind.JsonMappingException r6 = r6.weirdNumberException(r7, r8, r9)
            throw r6
        L_0x00c9:
            r6 = r5
            r0 = r6
            goto L_0x0042
        L_0x00cd:
            r6 = r2
            r7 = r0
            com.shaded.fasterxml.jackson.databind.util.EnumResolver<?> r7 = r7._resolver
            java.lang.Class r7 = r7.getEnumClass()
            com.shaded.fasterxml.jackson.databind.JsonMappingException r6 = r6.mappingException((java.lang.Class<?>) r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.EnumDeserializer.deserialize(com.shaded.fasterxml.jackson.core.JsonParser, com.shaded.fasterxml.jackson.databind.DeserializationContext):java.lang.Enum");
    }

    protected static class FactoryBasedDeserializer extends StdScalarDeserializer<Object> {
        private static final long serialVersionUID = -7775129435872564122L;
        protected final Class<?> _enumClass;
        protected final Method _factory;
        protected final Class<?> _inputType;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FactoryBasedDeserializer(Class<?> cls, AnnotatedMethod annotatedMethod, Class<?> cls2) {
            super((Class<?>) Enum.class);
            this._enumClass = cls;
            this._factory = annotatedMethod.getAnnotated();
            this._inputType = cls2;
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Object valueOf;
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            if (this._inputType == null) {
                valueOf = jsonParser2.getText();
            } else if (this._inputType == Integer.class) {
                valueOf = Integer.valueOf(jsonParser2.getValueAsInt());
            } else if (this._inputType == Long.class) {
                valueOf = Long.valueOf(jsonParser2.getValueAsLong());
            } else {
                throw deserializationContext2.mappingException(this._enumClass);
            }
            try {
                return this._factory.invoke(this._enumClass, new Object[]{valueOf});
            } catch (Exception e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
                return null;
            }
        }
    }
}
