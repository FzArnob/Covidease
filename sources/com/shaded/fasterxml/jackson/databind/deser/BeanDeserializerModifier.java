package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.shaded.fasterxml.jackson.databind.type.ArrayType;
import com.shaded.fasterxml.jackson.databind.type.CollectionLikeType;
import com.shaded.fasterxml.jackson.databind.type.CollectionType;
import com.shaded.fasterxml.jackson.databind.type.MapLikeType;
import com.shaded.fasterxml.jackson.databind.type.MapType;
import java.util.List;

public abstract class BeanDeserializerModifier {
    public BeanDeserializerModifier() {
    }

    public List<BeanPropertyDefinition> updateProperties(DeserializationConfig deserializationConfig, BeanDescription beanDescription, List<BeanPropertyDefinition> list) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        return list;
    }

    public BeanDeserializerBuilder updateBuilder(DeserializationConfig deserializationConfig, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        return beanDeserializerBuilder;
    }

    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig deserializationConfig, BeanDescription beanDescription, JsonDeserializer<?> jsonDeserializer) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        return jsonDeserializer;
    }

    public JsonDeserializer<?> modifyArrayDeserializer(DeserializationConfig deserializationConfig, ArrayType arrayType, BeanDescription beanDescription, JsonDeserializer<?> jsonDeserializer) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        ArrayType arrayType2 = arrayType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonDeserializer;
    }

    public JsonDeserializer<?> modifyCollectionDeserializer(DeserializationConfig deserializationConfig, CollectionType collectionType, BeanDescription beanDescription, JsonDeserializer<?> jsonDeserializer) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        CollectionType collectionType2 = collectionType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonDeserializer;
    }

    public JsonDeserializer<?> modifyCollectionLikeDeserializer(DeserializationConfig deserializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, JsonDeserializer<?> jsonDeserializer) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        CollectionLikeType collectionLikeType2 = collectionLikeType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonDeserializer;
    }

    public JsonDeserializer<?> modifyMapDeserializer(DeserializationConfig deserializationConfig, MapType mapType, BeanDescription beanDescription, JsonDeserializer<?> jsonDeserializer) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        MapType mapType2 = mapType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonDeserializer;
    }

    public JsonDeserializer<?> modifyMapLikeDeserializer(DeserializationConfig deserializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, JsonDeserializer<?> jsonDeserializer) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        MapLikeType mapLikeType2 = mapLikeType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonDeserializer;
    }

    public JsonDeserializer<?> modifyEnumDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanDescription beanDescription, JsonDeserializer<?> jsonDeserializer) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        return jsonDeserializer;
    }

    public KeyDeserializer modifyKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, KeyDeserializer keyDeserializer) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        return keyDeserializer;
    }
}
