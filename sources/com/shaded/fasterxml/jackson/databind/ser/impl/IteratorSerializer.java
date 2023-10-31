package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import java.io.IOException;
import java.util.Iterator;

@JacksonStdImpl
public class IteratorSerializer extends AsArraySerializerBase<Iterator<?>> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IteratorSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        super(Iterator.class, javaType, z, typeSerializer, beanProperty, (JsonSerializer<Object>) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IteratorSerializer(IteratorSerializer iteratorSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        super(iteratorSerializer, beanProperty, typeSerializer, jsonSerializer);
    }

    public boolean isEmpty(Iterator<?> it) {
        Iterator<?> it2 = it;
        return it2 == null || !it2.hasNext();
    }

    public boolean hasSingleElement(Iterator<?> it) {
        Iterator<?> it2 = it;
        return false;
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        ContainerSerializer<?> containerSerializer;
        new IteratorSerializer(this._elementType, this._staticTyping, typeSerializer, this._property);
        return containerSerializer;
    }

    public IteratorSerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        IteratorSerializer iteratorSerializer;
        new IteratorSerializer(this, beanProperty, typeSerializer, jsonSerializer);
        return iteratorSerializer;
    }

    public void serializeContents(Iterator<?> it, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<Object> findValueSerializer;
        Iterator<?> it2 = it;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (it2.hasNext()) {
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            JsonSerializer<Object> jsonSerializer = null;
            Class<?> cls = null;
            do {
                Object next = it2.next();
                if (next == null) {
                    serializerProvider2.defaultSerializeNull(jsonGenerator2);
                } else {
                    Class<?> cls2 = next.getClass();
                    if (cls2 == cls) {
                        findValueSerializer = jsonSerializer;
                    } else {
                        findValueSerializer = serializerProvider2.findValueSerializer(cls2, this._property);
                        jsonSerializer = findValueSerializer;
                        cls = cls2;
                    }
                    if (typeSerializer == null) {
                        findValueSerializer.serialize(next, jsonGenerator2, serializerProvider2);
                    } else {
                        findValueSerializer.serializeWithType(next, jsonGenerator2, serializerProvider2, typeSerializer);
                    }
                }
            } while (it2.hasNext());
        }
    }
}
