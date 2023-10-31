package com.shaded.fasterxml.jackson.databind.ser;

import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.type.ArrayType;
import com.shaded.fasterxml.jackson.databind.type.CollectionLikeType;
import com.shaded.fasterxml.jackson.databind.type.CollectionType;
import com.shaded.fasterxml.jackson.databind.type.MapLikeType;
import com.shaded.fasterxml.jackson.databind.type.MapType;

public interface Serializers {
    JsonSerializer<?> findArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer);

    JsonSerializer<?> findCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer);

    JsonSerializer<?> findCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer);

    JsonSerializer<?> findMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2);

    JsonSerializer<?> findMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2);

    JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription);

    public static class Base implements Serializers {
        public Base() {
        }

        public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) {
            SerializationConfig serializationConfig2 = serializationConfig;
            JavaType javaType2 = javaType;
            BeanDescription beanDescription2 = beanDescription;
            return null;
        }

        public JsonSerializer<?> findArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
            SerializationConfig serializationConfig2 = serializationConfig;
            ArrayType arrayType2 = arrayType;
            BeanDescription beanDescription2 = beanDescription;
            TypeSerializer typeSerializer2 = typeSerializer;
            JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
            return null;
        }

        public JsonSerializer<?> findCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
            SerializationConfig serializationConfig2 = serializationConfig;
            CollectionType collectionType2 = collectionType;
            BeanDescription beanDescription2 = beanDescription;
            TypeSerializer typeSerializer2 = typeSerializer;
            JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
            return null;
        }

        public JsonSerializer<?> findCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
            SerializationConfig serializationConfig2 = serializationConfig;
            CollectionLikeType collectionLikeType2 = collectionLikeType;
            BeanDescription beanDescription2 = beanDescription;
            TypeSerializer typeSerializer2 = typeSerializer;
            JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
            return null;
        }

        public JsonSerializer<?> findMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
            SerializationConfig serializationConfig2 = serializationConfig;
            MapType mapType2 = mapType;
            BeanDescription beanDescription2 = beanDescription;
            JsonSerializer<Object> jsonSerializer3 = jsonSerializer;
            TypeSerializer typeSerializer2 = typeSerializer;
            JsonSerializer<Object> jsonSerializer4 = jsonSerializer2;
            return null;
        }

        public JsonSerializer<?> findMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
            SerializationConfig serializationConfig2 = serializationConfig;
            MapLikeType mapLikeType2 = mapLikeType;
            BeanDescription beanDescription2 = beanDescription;
            JsonSerializer<Object> jsonSerializer3 = jsonSerializer;
            TypeSerializer typeSerializer2 = typeSerializer;
            JsonSerializer<Object> jsonSerializer4 = jsonSerializer2;
            return null;
        }
    }
}
