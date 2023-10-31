package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.Iterator;

@JacksonStdImpl
public class IterableSerializer extends AsArraySerializerBase<Iterable<?>> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IterableSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        super(Iterable.class, javaType, z, typeSerializer, beanProperty, (JsonSerializer<Object>) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IterableSerializer(IterableSerializer iterableSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        super(iterableSerializer, beanProperty, typeSerializer, jsonSerializer);
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        ContainerSerializer<?> containerSerializer;
        new IterableSerializer(this._elementType, this._staticTyping, typeSerializer, this._property);
        return containerSerializer;
    }

    public IterableSerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        IterableSerializer iterableSerializer;
        new IterableSerializer(this, beanProperty, typeSerializer, jsonSerializer);
        return iterableSerializer;
    }

    public boolean isEmpty(Iterable<?> iterable) {
        Iterable<?> iterable2 = iterable;
        return iterable2 == null || !iterable2.iterator().hasNext();
    }

    public boolean hasSingleElement(Iterable<?> iterable) {
        Iterable<?> iterable2 = iterable;
        return false;
    }

    public void serializeContents(Iterable<?> iterable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<Object> findValueSerializer;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        Iterator<?> it = iterable.iterator();
        if (it.hasNext()) {
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            JsonSerializer<Object> jsonSerializer = null;
            Class<?> cls = null;
            do {
                Object next = it.next();
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
            } while (it.hasNext());
        }
    }
}
