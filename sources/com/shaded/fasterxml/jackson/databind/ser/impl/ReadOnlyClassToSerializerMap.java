package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.ser.SerializerCache;
import java.util.HashMap;

public final class ReadOnlyClassToSerializerMap {
    protected SerializerCache.TypeKey _cacheKey = null;
    protected final JsonSerializerMap _map;

    private ReadOnlyClassToSerializerMap(JsonSerializerMap jsonSerializerMap) {
        this._map = jsonSerializerMap;
    }

    public ReadOnlyClassToSerializerMap instance() {
        ReadOnlyClassToSerializerMap readOnlyClassToSerializerMap;
        new ReadOnlyClassToSerializerMap(this._map);
        return readOnlyClassToSerializerMap;
    }

    public static ReadOnlyClassToSerializerMap from(HashMap<SerializerCache.TypeKey, JsonSerializer<Object>> hashMap) {
        ReadOnlyClassToSerializerMap readOnlyClassToSerializerMap;
        JsonSerializerMap jsonSerializerMap;
        new JsonSerializerMap(hashMap);
        new ReadOnlyClassToSerializerMap(jsonSerializerMap);
        return readOnlyClassToSerializerMap;
    }

    public JsonSerializer<Object> typedValueSerializer(JavaType javaType) {
        SerializerCache.TypeKey typeKey;
        JavaType javaType2 = javaType;
        if (this._cacheKey == null) {
            new SerializerCache.TypeKey(javaType2, true);
            this._cacheKey = typeKey;
        } else {
            this._cacheKey.resetTyped(javaType2);
        }
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer<Object> typedValueSerializer(Class<?> cls) {
        SerializerCache.TypeKey typeKey;
        Class<?> cls2 = cls;
        if (this._cacheKey == null) {
            new SerializerCache.TypeKey(cls2, true);
            this._cacheKey = typeKey;
        } else {
            this._cacheKey.resetTyped(cls2);
        }
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer<Object> untypedValueSerializer(JavaType javaType) {
        SerializerCache.TypeKey typeKey;
        JavaType javaType2 = javaType;
        if (this._cacheKey == null) {
            new SerializerCache.TypeKey(javaType2, false);
            this._cacheKey = typeKey;
        } else {
            this._cacheKey.resetUntyped(javaType2);
        }
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer<Object> untypedValueSerializer(Class<?> cls) {
        SerializerCache.TypeKey typeKey;
        Class<?> cls2 = cls;
        if (this._cacheKey == null) {
            new SerializerCache.TypeKey(cls2, false);
            this._cacheKey = typeKey;
        } else {
            this._cacheKey.resetUntyped(cls2);
        }
        return this._map.find(this._cacheKey);
    }
}
