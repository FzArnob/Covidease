package com.shaded.fasterxml.jackson.databind.module;

import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.Deserializers;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.type.ArrayType;
import com.shaded.fasterxml.jackson.databind.type.ClassKey;
import com.shaded.fasterxml.jackson.databind.type.CollectionLikeType;
import com.shaded.fasterxml.jackson.databind.type.CollectionType;
import com.shaded.fasterxml.jackson.databind.type.MapLikeType;
import com.shaded.fasterxml.jackson.databind.type.MapType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SimpleDeserializers implements Deserializers, Serializable {
    private static final long serialVersionUID = -3006673354353448880L;
    protected HashMap<ClassKey, JsonDeserializer<?>> _classMappings = null;

    public SimpleDeserializers() {
    }

    public SimpleDeserializers(Map<Class<?>, JsonDeserializer<?>> map) {
        addDeserializers(map);
    }

    public <T> void addDeserializer(Class<T> cls, JsonDeserializer<? extends T> jsonDeserializer) {
        Object obj;
        HashMap<ClassKey, JsonDeserializer<?>> hashMap;
        JsonDeserializer<? extends T> jsonDeserializer2 = jsonDeserializer;
        new ClassKey(cls);
        Object obj2 = obj;
        if (this._classMappings == null) {
            new HashMap<>();
            this._classMappings = hashMap;
        }
        JsonDeserializer<?> put = this._classMappings.put(obj2, jsonDeserializer2);
    }

    public void addDeserializers(Map<Class<?>, JsonDeserializer<?>> map) {
        for (Map.Entry next : map.entrySet()) {
            addDeserializer((Class) next.getKey(), (JsonDeserializer) next.getValue());
        }
    }

    public JsonDeserializer<?> findArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Object obj;
        JsonDeserializer<?> jsonDeserializer2;
        ArrayType arrayType2 = arrayType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> jsonDeserializer3 = jsonDeserializer;
        if (this._classMappings == null) {
            jsonDeserializer2 = null;
        } else {
            new ClassKey(arrayType2.getRawClass());
            jsonDeserializer2 = this._classMappings.get(obj);
        }
        return jsonDeserializer2;
    }

    public JsonDeserializer<?> findBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        Object obj;
        JsonDeserializer<?> jsonDeserializer;
        JavaType javaType2 = javaType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        if (this._classMappings == null) {
            jsonDeserializer = null;
        } else {
            new ClassKey(javaType2.getRawClass());
            jsonDeserializer = this._classMappings.get(obj);
        }
        return jsonDeserializer;
    }

    public JsonDeserializer<?> findCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Object obj;
        JsonDeserializer<?> jsonDeserializer2;
        CollectionType collectionType2 = collectionType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> jsonDeserializer3 = jsonDeserializer;
        if (this._classMappings == null) {
            jsonDeserializer2 = null;
        } else {
            new ClassKey(collectionType2.getRawClass());
            jsonDeserializer2 = this._classMappings.get(obj);
        }
        return jsonDeserializer2;
    }

    public JsonDeserializer<?> findCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Object obj;
        JsonDeserializer<?> jsonDeserializer2;
        CollectionLikeType collectionLikeType2 = collectionLikeType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> jsonDeserializer3 = jsonDeserializer;
        if (this._classMappings == null) {
            jsonDeserializer2 = null;
        } else {
            new ClassKey(collectionLikeType2.getRawClass());
            jsonDeserializer2 = this._classMappings.get(obj);
        }
        return jsonDeserializer2;
    }

    public JsonDeserializer<?> findEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        Object obj;
        JsonDeserializer<?> jsonDeserializer;
        Class<?> cls2 = cls;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        if (this._classMappings == null) {
            jsonDeserializer = null;
        } else {
            new ClassKey(cls2);
            jsonDeserializer = this._classMappings.get(obj);
        }
        return jsonDeserializer;
    }

    public JsonDeserializer<?> findMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Object obj;
        JsonDeserializer<?> jsonDeserializer2;
        MapType mapType2 = mapType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        KeyDeserializer keyDeserializer2 = keyDeserializer;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> jsonDeserializer3 = jsonDeserializer;
        if (this._classMappings == null) {
            jsonDeserializer2 = null;
        } else {
            new ClassKey(mapType2.getRawClass());
            jsonDeserializer2 = this._classMappings.get(obj);
        }
        return jsonDeserializer2;
    }

    public JsonDeserializer<?> findMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Object obj;
        JsonDeserializer<?> jsonDeserializer2;
        MapLikeType mapLikeType2 = mapLikeType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        KeyDeserializer keyDeserializer2 = keyDeserializer;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> jsonDeserializer3 = jsonDeserializer;
        if (this._classMappings == null) {
            jsonDeserializer2 = null;
        } else {
            new ClassKey(mapLikeType2.getRawClass());
            jsonDeserializer2 = this._classMappings.get(obj);
        }
        return jsonDeserializer2;
    }

    public JsonDeserializer<?> findTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        Object obj;
        JsonDeserializer<?> jsonDeserializer;
        Class<? extends JsonNode> cls2 = cls;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        if (this._classMappings == null) {
            jsonDeserializer = null;
        } else {
            new ClassKey(cls2);
            jsonDeserializer = this._classMappings.get(obj);
        }
        return jsonDeserializer;
    }
}
