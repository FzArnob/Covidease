package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;

public abstract class PropertySerializerMap {
    public abstract PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer);

    public abstract JsonSerializer<Object> serializerFor(Class<?> cls);

    public PropertySerializerMap() {
    }

    public final SerializerAndMapResult findAndAddSerializer(Class<?> cls, SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        SerializerAndMapResult serializerAndMapResult;
        Class<?> cls2 = cls;
        JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(cls2, beanProperty);
        new SerializerAndMapResult(findValueSerializer, newWith(cls2, findValueSerializer));
        return serializerAndMapResult;
    }

    public final SerializerAndMapResult findAndAddSerializer(JavaType javaType, SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        SerializerAndMapResult serializerAndMapResult;
        JavaType javaType2 = javaType;
        JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(javaType2, beanProperty);
        new SerializerAndMapResult(findValueSerializer, newWith(javaType2.getRawClass(), findValueSerializer));
        return serializerAndMapResult;
    }

    public static PropertySerializerMap emptyMap() {
        return Empty.instance;
    }

    public static final class SerializerAndMapResult {
        public final PropertySerializerMap map;
        public final JsonSerializer<Object> serializer;

        public SerializerAndMapResult(JsonSerializer<Object> jsonSerializer, PropertySerializerMap propertySerializerMap) {
            this.serializer = jsonSerializer;
            this.map = propertySerializerMap;
        }
    }

    private static final class TypeAndSerializer {
        public final JsonSerializer<Object> serializer;
        public final Class<?> type;

        public TypeAndSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            this.type = cls;
            this.serializer = jsonSerializer;
        }
    }

    private static final class Empty extends PropertySerializerMap {
        protected static final Empty instance;

        private Empty() {
        }

        static {
            Empty empty;
            new Empty();
            instance = empty;
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            Class<?> cls2 = cls;
            return null;
        }

        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            PropertySerializerMap propertySerializerMap;
            new Single(cls, jsonSerializer);
            return propertySerializerMap;
        }
    }

    private static final class Single extends PropertySerializerMap {
        private final JsonSerializer<Object> _serializer;
        private final Class<?> _type;

        public Single(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            this._type = cls;
            this._serializer = jsonSerializer;
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            if (cls == this._type) {
                return this._serializer;
            }
            return null;
        }

        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            PropertySerializerMap propertySerializerMap;
            new Double(this._type, this._serializer, cls, jsonSerializer);
            return propertySerializerMap;
        }
    }

    private static final class Double extends PropertySerializerMap {
        private final JsonSerializer<Object> _serializer1;
        private final JsonSerializer<Object> _serializer2;
        private final Class<?> _type1;
        private final Class<?> _type2;

        public Double(Class<?> cls, JsonSerializer<Object> jsonSerializer, Class<?> cls2, JsonSerializer<Object> jsonSerializer2) {
            this._type1 = cls;
            this._serializer1 = jsonSerializer;
            this._type2 = cls2;
            this._serializer2 = jsonSerializer2;
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            Class<?> cls2 = cls;
            if (cls2 == this._type1) {
                return this._serializer1;
            }
            if (cls2 == this._type2) {
                return this._serializer2;
            }
            return null;
        }

        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            TypeAndSerializer typeAndSerializer;
            TypeAndSerializer typeAndSerializer2;
            PropertySerializerMap propertySerializerMap;
            Class<?> cls2 = cls;
            JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
            new TypeAndSerializer(this._type1, this._serializer1);
            new TypeAndSerializer(this._type2, this._serializer2);
            new Multi(new TypeAndSerializer[]{typeAndSerializer, typeAndSerializer2});
            return propertySerializerMap;
        }
    }

    private static final class Multi extends PropertySerializerMap {
        private static final int MAX_ENTRIES = 8;
        private final TypeAndSerializer[] _entries;

        public Multi(TypeAndSerializer[] typeAndSerializerArr) {
            this._entries = typeAndSerializerArr;
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            Class<?> cls2 = cls;
            int length = this._entries.length;
            for (int i = 0; i < length; i++) {
                TypeAndSerializer typeAndSerializer = this._entries[i];
                if (typeAndSerializer.type == cls2) {
                    return typeAndSerializer.serializer;
                }
            }
            return null;
        }

        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            TypeAndSerializer typeAndSerializer;
            PropertySerializerMap propertySerializerMap;
            Class<?> cls2 = cls;
            JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
            int length = this._entries.length;
            if (length == 8) {
                return this;
            }
            TypeAndSerializer[] typeAndSerializerArr = new TypeAndSerializer[(length + 1)];
            System.arraycopy(this._entries, 0, typeAndSerializerArr, 0, length);
            new TypeAndSerializer(cls2, jsonSerializer2);
            typeAndSerializerArr[length] = typeAndSerializer;
            new Multi(typeAndSerializerArr);
            return propertySerializerMap;
        }
    }
}
