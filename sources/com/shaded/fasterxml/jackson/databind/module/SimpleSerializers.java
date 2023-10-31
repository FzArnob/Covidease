package com.shaded.fasterxml.jackson.databind.module;

import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.Serializers;
import com.shaded.fasterxml.jackson.databind.type.ArrayType;
import com.shaded.fasterxml.jackson.databind.type.ClassKey;
import com.shaded.fasterxml.jackson.databind.type.CollectionLikeType;
import com.shaded.fasterxml.jackson.databind.type.CollectionType;
import com.shaded.fasterxml.jackson.databind.type.MapLikeType;
import com.shaded.fasterxml.jackson.databind.type.MapType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class SimpleSerializers extends Serializers.Base implements Serializable {
    private static final long serialVersionUID = 8531646511998456779L;
    protected HashMap<ClassKey, JsonSerializer<?>> _classMappings = null;
    protected HashMap<ClassKey, JsonSerializer<?>> _interfaceMappings = null;

    public SimpleSerializers() {
    }

    public SimpleSerializers(List<JsonSerializer<?>> list) {
        addSerializers(list);
    }

    public void addSerializer(JsonSerializer<?> jsonSerializer) {
        Throwable th;
        StringBuilder sb;
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        Class<?> handledType = jsonSerializer2.handledType();
        if (handledType == null || handledType == Object.class) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("JsonSerializer of type ").append(jsonSerializer2.getClass().getName()).append(" does not define valid handledType() -- must either register with method that takes type argument ").append(" or make serializer extend 'com.fasterxml.jackson.databind.ser.std.StdSerializer'").toString());
            throw th2;
        }
        _addSerializer(handledType, jsonSerializer2);
    }

    public <T> void addSerializer(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        _addSerializer(cls, jsonSerializer);
    }

    public void addSerializers(List<JsonSerializer<?>> list) {
        for (JsonSerializer addSerializer : list) {
            addSerializer(addSerializer);
        }
    }

    private void _addSerializer(Class<?> cls, JsonSerializer<?> jsonSerializer) {
        Object obj;
        HashMap<ClassKey, JsonSerializer<?>> hashMap;
        HashMap<ClassKey, JsonSerializer<?>> hashMap2;
        Class<?> cls2 = cls;
        JsonSerializer<?> jsonSerializer2 = jsonSerializer;
        new ClassKey(cls2);
        Object obj2 = obj;
        if (cls2.isInterface()) {
            if (this._interfaceMappings == null) {
                new HashMap<>();
                this._interfaceMappings = hashMap2;
            }
            JsonSerializer<?> put = this._interfaceMappings.put(obj2, jsonSerializer2);
            return;
        }
        if (this._classMappings == null) {
            new HashMap<>();
            this._classMappings = hashMap;
        }
        JsonSerializer<?> put2 = this._classMappings.put(obj2, jsonSerializer2);
    }

    public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) {
        ClassKey classKey;
        JsonSerializer<?> _findInterfaceMapping;
        JsonSerializer<?> jsonSerializer;
        SerializationConfig serializationConfig2 = serializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        Class<? super Object> rawClass = javaType.getRawClass();
        new ClassKey(rawClass);
        ClassKey classKey2 = classKey;
        if (rawClass.isInterface()) {
            if (!(this._interfaceMappings == null || (jsonSerializer = this._interfaceMappings.get(classKey2)) == null)) {
                return jsonSerializer;
            }
        } else if (this._classMappings != null) {
            JsonSerializer<?> jsonSerializer2 = this._classMappings.get(classKey2);
            if (jsonSerializer2 == null) {
                Class<?> cls = rawClass;
                while (true) {
                    Class<?> cls2 = cls;
                    if (cls2 == null) {
                        break;
                    }
                    classKey2.reset(cls2);
                    JsonSerializer<?> jsonSerializer3 = this._classMappings.get(classKey2);
                    if (jsonSerializer3 != null) {
                        return jsonSerializer3;
                    }
                    cls = cls2.getSuperclass();
                }
            } else {
                return jsonSerializer2;
            }
        }
        if (this._interfaceMappings != null) {
            JsonSerializer<?> _findInterfaceMapping2 = _findInterfaceMapping(rawClass, classKey2);
            if (_findInterfaceMapping2 != null) {
                return _findInterfaceMapping2;
            }
            if (!rawClass.isInterface()) {
                do {
                    Class<? super Object> superclass = rawClass.getSuperclass();
                    rawClass = superclass;
                    if (superclass != null) {
                        _findInterfaceMapping = _findInterfaceMapping(rawClass, classKey2);
                    }
                } while (_findInterfaceMapping == null);
                return _findInterfaceMapping;
            }
        }
        return null;
    }

    public JsonSerializer<?> findArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        TypeSerializer typeSerializer2 = typeSerializer;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        return findSerializer(serializationConfig, arrayType, beanDescription);
    }

    public JsonSerializer<?> findCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        TypeSerializer typeSerializer2 = typeSerializer;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        return findSerializer(serializationConfig, collectionType, beanDescription);
    }

    public JsonSerializer<?> findCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        TypeSerializer typeSerializer2 = typeSerializer;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        return findSerializer(serializationConfig, collectionLikeType, beanDescription);
    }

    public JsonSerializer<?> findMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        JsonSerializer<Object> jsonSerializer3 = jsonSerializer;
        TypeSerializer typeSerializer2 = typeSerializer;
        JsonSerializer<Object> jsonSerializer4 = jsonSerializer2;
        return findSerializer(serializationConfig, mapType, beanDescription);
    }

    public JsonSerializer<?> findMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        JsonSerializer<Object> jsonSerializer3 = jsonSerializer;
        TypeSerializer typeSerializer2 = typeSerializer;
        JsonSerializer<Object> jsonSerializer4 = jsonSerializer2;
        return findSerializer(serializationConfig, mapLikeType, beanDescription);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> _findInterfaceMapping(Class<?> cls, ClassKey classKey) {
        ClassKey classKey2 = classKey;
        Class[] interfaces = cls.getInterfaces();
        int length = interfaces.length;
        for (int i = 0; i < length; i++) {
            Class cls2 = interfaces[i];
            classKey2.reset(cls2);
            JsonSerializer<?> jsonSerializer = this._interfaceMappings.get(classKey2);
            if (jsonSerializer != null) {
                return jsonSerializer;
            }
            JsonSerializer<?> _findInterfaceMapping = _findInterfaceMapping(cls2, classKey2);
            if (_findInterfaceMapping != null) {
                return _findInterfaceMapping;
            }
        }
        return null;
    }
}
