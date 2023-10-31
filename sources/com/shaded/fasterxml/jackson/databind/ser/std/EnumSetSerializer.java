package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;

public class EnumSetSerializer extends AsArraySerializerBase<EnumSet<? extends Enum<?>>> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnumSetSerializer(JavaType javaType, BeanProperty beanProperty) {
        super(EnumSet.class, javaType, true, (TypeSerializer) null, beanProperty, (JsonSerializer<Object>) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnumSetSerializer(EnumSetSerializer enumSetSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        super(enumSetSerializer, beanProperty, typeSerializer, jsonSerializer);
    }

    public EnumSetSerializer _withValueTypeSerializer(TypeSerializer typeSerializer) {
        TypeSerializer typeSerializer2 = typeSerializer;
        return this;
    }

    public EnumSetSerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        EnumSetSerializer enumSetSerializer;
        new EnumSetSerializer(this, beanProperty, typeSerializer, jsonSerializer);
        return enumSetSerializer;
    }

    public boolean isEmpty(EnumSet<? extends Enum<?>> enumSet) {
        EnumSet<? extends Enum<?>> enumSet2 = enumSet;
        return enumSet2 == null || enumSet2.isEmpty();
    }

    public boolean hasSingleElement(EnumSet<? extends Enum<?>> enumSet) {
        return enumSet.size() == 1;
    }

    public void serializeContents(EnumSet<? extends Enum<?>> enumSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        JsonSerializer<Object> jsonSerializer = this._elementSerializer;
        Iterator it = enumSet.iterator();
        while (it.hasNext()) {
            Enum enumR = (Enum) it.next();
            if (jsonSerializer == null) {
                jsonSerializer = serializerProvider2.findValueSerializer((Class<?>) enumR.getDeclaringClass(), this._property);
            }
            jsonSerializer.serialize(enumR, jsonGenerator2, serializerProvider2);
        }
    }
}
