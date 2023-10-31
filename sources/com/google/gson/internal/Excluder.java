package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Excluder implements TypeAdapterFactory, Cloneable {
    public static final Excluder DEFAULT;
    private static final double IGNORE_VERSIONS = -1.0d;
    private List<ExclusionStrategy> deserializationStrategies = Collections.emptyList();
    private int modifiers = 136;
    private boolean requireExpose;
    private List<ExclusionStrategy> serializationStrategies = Collections.emptyList();
    private boolean serializeInnerClasses = true;
    private double version = IGNORE_VERSIONS;

    public Excluder() {
    }

    static {
        Excluder excluder;
        new Excluder();
        DEFAULT = excluder;
    }

    /* access modifiers changed from: protected */
    public Excluder clone() {
        Throwable th;
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException e) {
            CloneNotSupportedException e2 = e;
            Throwable th2 = th;
            new AssertionError(e2);
            throw th2;
        }
    }

    public Excluder withVersion(double ignoreVersionsAfter) {
        Excluder result = clone();
        result.version = ignoreVersionsAfter;
        return result;
    }

    public Excluder withModifiers(int... modifiers2) {
        Excluder result = clone();
        result.modifiers = 0;
        int[] iArr = modifiers2;
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            result.modifiers |= iArr[i];
        }
        return result;
    }

    public Excluder disableInnerClassSerialization() {
        Excluder result = clone();
        result.serializeInnerClasses = false;
        return result;
    }

    public Excluder excludeFieldsWithoutExposeAnnotation() {
        Excluder result = clone();
        result.requireExpose = true;
        return result;
    }

    public Excluder withExclusionStrategy(ExclusionStrategy exclusionStrategy, boolean serialization, boolean z) {
        List<ExclusionStrategy> list;
        List<ExclusionStrategy> list2;
        ExclusionStrategy exclusionStrategy2 = exclusionStrategy;
        boolean deserialization = z;
        Excluder result = clone();
        if (serialization) {
            new ArrayList(this.serializationStrategies);
            result.serializationStrategies = list2;
            boolean add = result.serializationStrategies.add(exclusionStrategy2);
        }
        if (deserialization) {
            new ArrayList(this.deserializationStrategies);
            result.deserializationStrategies = list;
            boolean add2 = result.deserializationStrategies.add(exclusionStrategy2);
        }
        return result;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        TypeAdapter<T> typeAdapter;
        Gson gson2 = gson;
        TypeToken<T> type = typeToken;
        Class<? super T> rawType = type.getRawType();
        boolean excludeClass = excludeClassChecks(rawType);
        boolean skipSerialize = excludeClass || excludeClassInStrategy(rawType, true);
        boolean skipDeserialize = excludeClass || excludeClassInStrategy(rawType, false);
        if (!skipSerialize && !skipDeserialize) {
            return null;
        }
        final boolean z = skipDeserialize;
        final boolean z2 = skipSerialize;
        final Gson gson3 = gson2;
        final TypeToken<T> typeToken2 = type;
        new TypeAdapter<T>(this) {
            private TypeAdapter<T> delegate;
            final /* synthetic */ Excluder this$0;

            {
                this.this$0 = this$0;
            }

            public T read(JsonReader jsonReader) throws IOException {
                JsonReader in = jsonReader;
                if (!z) {
                    return delegate().read(in);
                }
                in.skipValue();
                return null;
            }

            public void write(JsonWriter jsonWriter, T t) throws IOException {
                JsonWriter out = jsonWriter;
                T value = t;
                if (z2) {
                    JsonWriter nullValue = out.nullValue();
                } else {
                    delegate().write(out, value);
                }
            }

            private TypeAdapter<T> delegate() {
                TypeAdapter<T> typeAdapter;
                TypeAdapter<T> d = this.delegate;
                if (d != null) {
                    typeAdapter = d;
                } else {
                    TypeAdapter<T> delegateAdapter = gson3.getDelegateAdapter(this.this$0, typeToken2);
                    typeAdapter = delegateAdapter;
                    this.delegate = delegateAdapter;
                }
                return typeAdapter;
            }
        };
        return typeAdapter;
    }

    public boolean excludeField(Field field, boolean z) {
        FieldAttributes fieldAttributes;
        Expose annotation;
        Field field2 = field;
        boolean serialize = z;
        if ((this.modifiers & field2.getModifiers()) != 0) {
            return true;
        }
        if (this.version != IGNORE_VERSIONS && !isValidVersion((Since) field2.getAnnotation(Since.class), (Until) field2.getAnnotation(Until.class))) {
            return true;
        }
        if (field2.isSynthetic()) {
            return true;
        }
        if (this.requireExpose && ((annotation = (Expose) field2.getAnnotation(Expose.class)) == null || (!serialize ? !annotation.deserialize() : !annotation.serialize()))) {
            return true;
        }
        if (!this.serializeInnerClasses && isInnerClass(field2.getType())) {
            return true;
        }
        if (isAnonymousOrLocal(field2.getType())) {
            return true;
        }
        List<ExclusionStrategy> list = serialize ? this.serializationStrategies : this.deserializationStrategies;
        if (!list.isEmpty()) {
            new FieldAttributes(field2);
            FieldAttributes fieldAttributes2 = fieldAttributes;
            for (ExclusionStrategy exclusionStrategy : list) {
                if (exclusionStrategy.shouldSkipField(fieldAttributes2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean excludeClassChecks(Class<?> cls) {
        Class<?> clazz = cls;
        if (this.version != IGNORE_VERSIONS && !isValidVersion((Since) clazz.getAnnotation(Since.class), (Until) clazz.getAnnotation(Until.class))) {
            return true;
        }
        if (!this.serializeInnerClasses && isInnerClass(clazz)) {
            return true;
        }
        if (isAnonymousOrLocal(clazz)) {
            return true;
        }
        return false;
    }

    public boolean excludeClass(Class<?> cls, boolean serialize) {
        Class<?> clazz = cls;
        return excludeClassChecks(clazz) || excludeClassInStrategy(clazz, serialize);
    }

    private boolean excludeClassInStrategy(Class<?> cls, boolean serialize) {
        Class<?> clazz = cls;
        for (ExclusionStrategy exclusionStrategy : serialize ? this.serializationStrategies : this.deserializationStrategies) {
            if (exclusionStrategy.shouldSkipClass(clazz)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnonymousOrLocal(Class<?> cls) {
        Class<?> clazz = cls;
        return !Enum.class.isAssignableFrom(clazz) && (clazz.isAnonymousClass() || clazz.isLocalClass());
    }

    private boolean isInnerClass(Class<?> cls) {
        Class<?> clazz = cls;
        return clazz.isMemberClass() && !isStatic(clazz);
    }

    private boolean isStatic(Class<?> clazz) {
        return (clazz.getModifiers() & 8) != 0;
    }

    private boolean isValidVersion(Since since, Until until) {
        return isValidSince(since) && isValidUntil(until);
    }

    private boolean isValidSince(Since since) {
        Since annotation = since;
        if (annotation == null || annotation.value() <= this.version) {
            return true;
        }
        return false;
    }

    private boolean isValidUntil(Until until) {
        Until annotation = until;
        if (annotation == null || annotation.value() > this.version) {
            return true;
        }
        return false;
    }
}
