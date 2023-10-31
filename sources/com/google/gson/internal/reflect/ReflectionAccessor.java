package com.google.gson.internal.reflect;

import com.google.gson.internal.JavaVersion;
import java.lang.reflect.AccessibleObject;

public abstract class ReflectionAccessor {
    private static final ReflectionAccessor instance;

    public abstract void makeAccessible(AccessibleObject accessibleObject);

    public ReflectionAccessor() {
    }

    static {
        ReflectionAccessor reflectionAccessor;
        ReflectionAccessor reflectionAccessor2;
        ReflectionAccessor reflectionAccessor3;
        if (JavaVersion.getMajorJavaVersion() < 9) {
            reflectionAccessor2 = reflectionAccessor3;
            new PreJava9ReflectionAccessor();
        } else {
            reflectionAccessor2 = reflectionAccessor;
            new UnsafeReflectionAccessor();
        }
        instance = reflectionAccessor2;
    }

    public static ReflectionAccessor getInstance() {
        return instance;
    }
}
