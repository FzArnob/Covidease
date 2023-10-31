package com.google.gson.internal;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Primitives {
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER_TYPE;
    private static final Map<Class<?>, Class<?>> WRAPPER_TO_PRIMITIVE_TYPE;

    private Primitives() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    static {
        Map<Class<?>, Class<?>> map;
        Map<Class<?>, Class<?>> map2;
        new HashMap<>(16);
        Map<Class<?>, Class<?>> primToWrap = map;
        new HashMap<>(16);
        Map<Class<?>, Class<?>> wrapToPrim = map2;
        add(primToWrap, wrapToPrim, Boolean.TYPE, Boolean.class);
        add(primToWrap, wrapToPrim, Byte.TYPE, Byte.class);
        add(primToWrap, wrapToPrim, Character.TYPE, Character.class);
        add(primToWrap, wrapToPrim, Double.TYPE, Double.class);
        add(primToWrap, wrapToPrim, Float.TYPE, Float.class);
        add(primToWrap, wrapToPrim, Integer.TYPE, Integer.class);
        add(primToWrap, wrapToPrim, Long.TYPE, Long.class);
        add(primToWrap, wrapToPrim, Short.TYPE, Short.class);
        add(primToWrap, wrapToPrim, Void.TYPE, Void.class);
        PRIMITIVE_TO_WRAPPER_TYPE = Collections.unmodifiableMap(primToWrap);
        WRAPPER_TO_PRIMITIVE_TYPE = Collections.unmodifiableMap(wrapToPrim);
    }

    private static void add(Map<Class<?>, Class<?>> forward, Map<Class<?>, Class<?>> backward, Class<?> cls, Class<?> cls2) {
        Class<?> key = cls;
        Class<?> value = cls2;
        Class<?> put = forward.put(key, value);
        Class<?> put2 = backward.put(value, key);
    }

    public static boolean isPrimitive(Type type) {
        return PRIMITIVE_TO_WRAPPER_TYPE.containsKey(type);
    }

    public static boolean isWrapperType(Type type) {
        return WRAPPER_TO_PRIMITIVE_TYPE.containsKey(C$Gson$Preconditions.checkNotNull(type));
    }

    public static <T> Class<T> wrap(Class<T> cls) {
        Class<T> type = cls;
        Class<T> wrapped = PRIMITIVE_TO_WRAPPER_TYPE.get(C$Gson$Preconditions.checkNotNull(type));
        return wrapped == null ? type : wrapped;
    }

    public static <T> Class<T> unwrap(Class<T> cls) {
        Class<T> type = cls;
        Class<T> unwrapped = WRAPPER_TO_PRIMITIVE_TYPE.get(C$Gson$Preconditions.checkNotNull(type));
        return unwrapped == null ? type : unwrapped;
    }
}
