package com.shaded.fasterxml.jackson.core.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeReference<T> implements Comparable<TypeReference<T>> {
    protected final Type _type;

    protected TypeReference() {
        Throwable th;
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            Throwable th2 = th;
            new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
            throw th2;
        }
        this._type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return this._type;
    }

    public int compareTo(TypeReference<T> typeReference) {
        TypeReference<T> typeReference2 = typeReference;
        return 0;
    }
}
