package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.core.p005io.SerializedString;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;

public final class ObjectIdWriter {
    public final boolean alwaysAsId;
    public final ObjectIdGenerator<?> generator;
    public final JavaType idType;
    public final SerializedString propertyName;
    public final JsonSerializer<Object> serializer;

    protected ObjectIdWriter(JavaType javaType, SerializedString serializedString, ObjectIdGenerator<?> objectIdGenerator, JsonSerializer<?> jsonSerializer, boolean z) {
        this.idType = javaType;
        this.propertyName = serializedString;
        this.generator = objectIdGenerator;
        this.serializer = jsonSerializer;
        this.alwaysAsId = z;
    }

    public static ObjectIdWriter construct(JavaType javaType, String str, ObjectIdGenerator<?> objectIdGenerator, boolean z) {
        SerializedString serializedString;
        SerializedString serializedString2;
        ObjectIdWriter objectIdWriter;
        JavaType javaType2 = javaType;
        String str2 = str;
        ObjectIdGenerator<?> objectIdGenerator2 = objectIdGenerator;
        boolean z2 = z;
        if (str2 == null) {
            serializedString2 = null;
        } else {
            serializedString2 = serializedString;
            new SerializedString(str2);
        }
        new ObjectIdWriter(javaType2, serializedString2, objectIdGenerator2, (JsonSerializer<?>) null, z2);
        return objectIdWriter;
    }

    public ObjectIdWriter withSerializer(JsonSerializer<?> jsonSerializer) {
        ObjectIdWriter objectIdWriter;
        new ObjectIdWriter(this.idType, this.propertyName, this.generator, jsonSerializer, this.alwaysAsId);
        return objectIdWriter;
    }

    public ObjectIdWriter withAlwaysAsId(boolean z) {
        ObjectIdWriter objectIdWriter;
        boolean z2 = z;
        if (z2 == this.alwaysAsId) {
            return this;
        }
        new ObjectIdWriter(this.idType, this.propertyName, this.generator, this.serializer, z2);
        return objectIdWriter;
    }
}
