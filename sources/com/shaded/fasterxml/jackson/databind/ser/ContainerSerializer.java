package com.shaded.fasterxml.jackson.databind.ser;

import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class ContainerSerializer<T> extends StdSerializer<T> {
    /* access modifiers changed from: protected */
    public abstract ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer);

    public abstract JsonSerializer<?> getContentSerializer();

    public abstract JavaType getContentType();

    public abstract boolean hasSingleElement(T t);

    public abstract boolean isEmpty(T t);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ContainerSerializer(Class<T> cls) {
        super(cls);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ContainerSerializer(Class<?> cls, boolean z) {
        super(cls, z);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ContainerSerializer(ContainerSerializer<?> containerSerializer) {
        super(containerSerializer._handledType, false);
    }

    public ContainerSerializer<?> withValueTypeSerializer(TypeSerializer typeSerializer) {
        TypeSerializer typeSerializer2 = typeSerializer;
        if (typeSerializer2 != null) {
            return _withValueTypeSerializer(typeSerializer2);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean hasContentTypeAnnotation(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        if (beanProperty2 == null || (annotationIntrospector = serializerProvider2.getAnnotationIntrospector()) == null || annotationIntrospector.findSerializationContentType(beanProperty2.getMember(), beanProperty2.getType()) == null) {
            return false;
        }
        return true;
    }
}
