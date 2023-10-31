package com.shaded.fasterxml.jackson.databind.util;

import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;

public interface Converter<IN, OUT> {
    OUT convert(IN in);

    JavaType getInputType(TypeFactory typeFactory);

    JavaType getOutputType(TypeFactory typeFactory);

    public static abstract class None implements Converter<Object, Object> {
        public None() {
        }
    }
}
