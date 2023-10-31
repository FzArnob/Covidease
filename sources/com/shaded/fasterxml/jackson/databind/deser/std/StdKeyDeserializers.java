package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.shaded.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.EnumResolver;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class StdKeyDeserializers implements KeyDeserializers, Serializable {
    private static final long serialVersionUID = 923268084968181479L;

    public StdKeyDeserializers() {
    }

    @Deprecated
    public static KeyDeserializer constructStringKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        return StdKeyDeserializer.StringKD.forType(javaType.getRawClass());
    }

    public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver<?> enumResolver) {
        KeyDeserializer keyDeserializer;
        new StdKeyDeserializer.EnumKD(enumResolver, (AnnotatedMethod) null);
        return keyDeserializer;
    }

    public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver<?> enumResolver, AnnotatedMethod annotatedMethod) {
        KeyDeserializer keyDeserializer;
        new StdKeyDeserializer.EnumKD(enumResolver, annotatedMethod);
        return keyDeserializer;
    }

    public static KeyDeserializer constructDelegatingKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        KeyDeserializer keyDeserializer;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        new StdKeyDeserializer.DelegatingKD(javaType.getRawClass(), jsonDeserializer);
        return keyDeserializer;
    }

    public static KeyDeserializer findStringBasedKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        KeyDeserializer keyDeserializer;
        KeyDeserializer keyDeserializer2;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription introspect = deserializationConfig2.introspect(javaType);
        Constructor<?> findSingleArgConstructor = introspect.findSingleArgConstructor(String.class);
        if (findSingleArgConstructor != null) {
            if (deserializationConfig2.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(findSingleArgConstructor);
            }
            new StdKeyDeserializer.StringCtorKeyDeserializer(findSingleArgConstructor);
            return keyDeserializer2;
        }
        Method findFactoryMethod = introspect.findFactoryMethod(String.class);
        if (findFactoryMethod == null) {
            return null;
        }
        if (deserializationConfig2.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(findFactoryMethod);
        }
        new StdKeyDeserializer.StringFactoryKeyDeserializer(findFactoryMethod);
        return keyDeserializer;
    }

    public KeyDeserializer findKeyDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        KeyDeserializer keyDeserializer;
        KeyDeserializer keyDeserializer2;
        KeyDeserializer keyDeserializer3;
        KeyDeserializer keyDeserializer4;
        KeyDeserializer keyDeserializer5;
        KeyDeserializer keyDeserializer6;
        KeyDeserializer keyDeserializer7;
        KeyDeserializer keyDeserializer8;
        KeyDeserializer keyDeserializer9;
        KeyDeserializer keyDeserializer10;
        KeyDeserializer keyDeserializer11;
        KeyDeserializer keyDeserializer12;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == String.class || rawClass == Object.class) {
            return StdKeyDeserializer.StringKD.forType(rawClass);
        }
        if (rawClass == UUID.class) {
            new StdKeyDeserializer.UuidKD();
            return keyDeserializer12;
        }
        if (rawClass.isPrimitive()) {
            rawClass = ClassUtil.wrapperType(rawClass);
        }
        if (rawClass == Integer.class) {
            new StdKeyDeserializer.IntKD();
            return keyDeserializer11;
        } else if (rawClass == Long.class) {
            new StdKeyDeserializer.LongKD();
            return keyDeserializer10;
        } else if (rawClass == Date.class) {
            new StdKeyDeserializer.DateKD();
            return keyDeserializer9;
        } else if (rawClass == Calendar.class) {
            new StdKeyDeserializer.CalendarKD();
            return keyDeserializer8;
        } else if (rawClass == Boolean.class) {
            new StdKeyDeserializer.BoolKD();
            return keyDeserializer7;
        } else if (rawClass == Byte.class) {
            new StdKeyDeserializer.ByteKD();
            return keyDeserializer6;
        } else if (rawClass == Character.class) {
            new StdKeyDeserializer.CharKD();
            return keyDeserializer5;
        } else if (rawClass == Short.class) {
            new StdKeyDeserializer.ShortKD();
            return keyDeserializer4;
        } else if (rawClass == Float.class) {
            new StdKeyDeserializer.FloatKD();
            return keyDeserializer3;
        } else if (rawClass == Double.class) {
            new StdKeyDeserializer.DoubleKD();
            return keyDeserializer2;
        } else if (rawClass != Locale.class) {
            return null;
        } else {
            new StdKeyDeserializer.LocaleKD();
            return keyDeserializer;
        }
    }
}
