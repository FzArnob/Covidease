package com.shaded.fasterxml.jackson.databind.util;

import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;

public abstract class StdConverter<IN, OUT> implements Converter<IN, OUT> {
    public abstract OUT convert(IN in);

    public StdConverter() {
    }

    public JavaType getInputType(TypeFactory typeFactory) {
        Throwable th;
        StringBuilder sb;
        JavaType[] findTypeParameters = typeFactory.findTypeParameters(getClass(), (Class<?>) Converter.class);
        if (findTypeParameters != null && findTypeParameters.length >= 2) {
            return findTypeParameters[0];
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Can not find OUT type parameter for Converter of type ").append(getClass().getName()).toString());
        throw th2;
    }

    public JavaType getOutputType(TypeFactory typeFactory) {
        Throwable th;
        StringBuilder sb;
        JavaType[] findTypeParameters = typeFactory.findTypeParameters(getClass(), (Class<?>) Converter.class);
        if (findTypeParameters != null && findTypeParameters.length >= 2) {
            return findTypeParameters[1];
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Can not find OUT type parameter for Converter of type ").append(getClass().getName()).toString());
        throw th2;
    }
}
