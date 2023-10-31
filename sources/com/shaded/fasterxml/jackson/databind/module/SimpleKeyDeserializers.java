package com.shaded.fasterxml.jackson.databind.module;

import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.KeyDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.shaded.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.HashMap;

public class SimpleKeyDeserializers implements KeyDeserializers, Serializable {
    private static final long serialVersionUID = -6786398737835438187L;
    protected HashMap<ClassKey, KeyDeserializer> _classMappings = null;

    public SimpleKeyDeserializers() {
    }

    public SimpleKeyDeserializers addDeserializer(Class<?> cls, KeyDeserializer keyDeserializer) {
        Object obj;
        HashMap<ClassKey, KeyDeserializer> hashMap;
        Class<?> cls2 = cls;
        KeyDeserializer keyDeserializer2 = keyDeserializer;
        if (this._classMappings == null) {
            new HashMap<>();
            this._classMappings = hashMap;
        }
        new ClassKey(cls2);
        KeyDeserializer put = this._classMappings.put(obj, keyDeserializer2);
        return this;
    }

    public KeyDeserializer findKeyDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        Object obj;
        JavaType javaType2 = javaType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        if (this._classMappings == null) {
            return null;
        }
        new ClassKey(javaType2.getRawClass());
        return this._classMappings.get(obj);
    }
}
