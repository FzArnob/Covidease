package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.IndexedListSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.IteratorSerializer;

public class StdContainerSerializers {
    protected StdContainerSerializers() {
    }

    public static ContainerSerializer<?> indexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        ContainerSerializer<?> containerSerializer;
        new IndexedListSerializer(javaType, z, typeSerializer, (BeanProperty) null, jsonSerializer);
        return containerSerializer;
    }

    public static ContainerSerializer<?> collectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        ContainerSerializer<?> containerSerializer;
        new CollectionSerializer(javaType, z, typeSerializer, (BeanProperty) null, jsonSerializer);
        return containerSerializer;
    }

    public static ContainerSerializer<?> iteratorSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer) {
        ContainerSerializer<?> containerSerializer;
        new IteratorSerializer(javaType, z, typeSerializer, (BeanProperty) null);
        return containerSerializer;
    }

    public static ContainerSerializer<?> iterableSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer) {
        ContainerSerializer<?> containerSerializer;
        new IterableSerializer(javaType, z, typeSerializer, (BeanProperty) null);
        return containerSerializer;
    }

    public static JsonSerializer<?> enumSetSerializer(JavaType javaType) {
        JsonSerializer<?> jsonSerializer;
        new EnumSetSerializer(javaType, (BeanProperty) null);
        return jsonSerializer;
    }

    @Deprecated
    public static ContainerSerializer<?> indexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        BeanProperty beanProperty2 = beanProperty;
        return indexedListSerializer(javaType, z, typeSerializer, jsonSerializer);
    }

    @Deprecated
    public static ContainerSerializer<?> collectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        BeanProperty beanProperty2 = beanProperty;
        return collectionSerializer(javaType, z, typeSerializer, jsonSerializer);
    }
}
