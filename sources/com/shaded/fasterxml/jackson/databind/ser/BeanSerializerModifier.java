package com.shaded.fasterxml.jackson.databind.ser;

import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.type.ArrayType;
import com.shaded.fasterxml.jackson.databind.type.CollectionLikeType;
import com.shaded.fasterxml.jackson.databind.type.CollectionType;
import com.shaded.fasterxml.jackson.databind.type.MapLikeType;
import com.shaded.fasterxml.jackson.databind.type.MapType;
import java.util.List;

public abstract class BeanSerializerModifier {
    public BeanSerializerModifier() {
    }

    public List<BeanPropertyWriter> changeProperties(SerializationConfig serializationConfig, BeanDescription beanDescription, List<BeanPropertyWriter> list) {
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        return list;
    }

    public List<BeanPropertyWriter> orderProperties(SerializationConfig serializationConfig, BeanDescription beanDescription, List<BeanPropertyWriter> list) {
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        return list;
    }

    public BeanSerializerBuilder updateBuilder(SerializationConfig serializationConfig, BeanDescription beanDescription, BeanSerializerBuilder beanSerializerBuilder) {
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        return beanSerializerBuilder;
    }

    public JsonSerializer<?> modifySerializer(SerializationConfig serializationConfig, BeanDescription beanDescription, JsonSerializer<?> jsonSerializer) {
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        return jsonSerializer;
    }

    public JsonSerializer<?> modifyArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, JsonSerializer<?> jsonSerializer) {
        SerializationConfig serializationConfig2 = serializationConfig;
        ArrayType arrayType2 = arrayType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonSerializer;
    }

    public JsonSerializer<?> modifyCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, JsonSerializer<?> jsonSerializer) {
        SerializationConfig serializationConfig2 = serializationConfig;
        CollectionType collectionType2 = collectionType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonSerializer;
    }

    public JsonSerializer<?> modifyCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, JsonSerializer<?> jsonSerializer) {
        SerializationConfig serializationConfig2 = serializationConfig;
        CollectionLikeType collectionLikeType2 = collectionLikeType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonSerializer;
    }

    public JsonSerializer<?> modifyMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, JsonSerializer<?> jsonSerializer) {
        SerializationConfig serializationConfig2 = serializationConfig;
        MapType mapType2 = mapType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonSerializer;
    }

    public JsonSerializer<?> modifyMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, JsonSerializer<?> jsonSerializer) {
        SerializationConfig serializationConfig2 = serializationConfig;
        MapLikeType mapLikeType2 = mapLikeType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonSerializer;
    }

    public JsonSerializer<?> modifyEnumSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, JsonSerializer<?> jsonSerializer) {
        SerializationConfig serializationConfig2 = serializationConfig;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonSerializer;
    }

    public JsonSerializer<?> modifyKeySerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, JsonSerializer<?> jsonSerializer) {
        SerializationConfig serializationConfig2 = serializationConfig;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonSerializer;
    }
}
