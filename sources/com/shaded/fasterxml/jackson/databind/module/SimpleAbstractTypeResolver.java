package com.shaded.fasterxml.jackson.databind.module;

import com.shaded.fasterxml.jackson.databind.AbstractTypeResolver;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class SimpleAbstractTypeResolver extends AbstractTypeResolver implements Serializable {
    private static final long serialVersionUID = 8635483102371490919L;
    protected final HashMap<ClassKey, Class<?>> _mappings;

    public SimpleAbstractTypeResolver() {
        HashMap<ClassKey, Class<?>> hashMap;
        new HashMap<>();
        this._mappings = hashMap;
    }

    public <T> SimpleAbstractTypeResolver addMapping(Class<T> cls, Class<? extends T> cls2) {
        Object obj;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        Class<T> cls3 = cls;
        Class<? extends T> cls4 = cls2;
        if (cls3 == cls4) {
            Throwable th4 = th3;
            new IllegalArgumentException("Can not add mapping from class to itself");
            throw th4;
        } else if (!cls3.isAssignableFrom(cls4)) {
            Throwable th5 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Can not add mapping from class ").append(cls3.getName()).append(" to ").append(cls4.getName()).append(", as latter is not a subtype of former").toString());
            throw th5;
        } else if (!Modifier.isAbstract(cls3.getModifiers())) {
            Throwable th6 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Can not add mapping from class ").append(cls3.getName()).append(" since it is not abstract").toString());
            throw th6;
        } else {
            new ClassKey(cls3);
            Class<?> put = this._mappings.put(obj, cls4);
            return this;
        }
    }

    public JavaType findTypeMapping(DeserializationConfig deserializationConfig, JavaType javaType) {
        Object obj;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        new ClassKey(javaType2.getRawClass());
        Class cls = this._mappings.get(obj);
        if (cls == null) {
            return null;
        }
        return javaType2.narrowBy(cls);
    }

    public JavaType resolveAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        return null;
    }
}
