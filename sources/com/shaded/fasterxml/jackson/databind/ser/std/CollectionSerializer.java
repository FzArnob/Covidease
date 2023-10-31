package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class CollectionSerializer extends AsArraySerializerBase<Collection<?>> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CollectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        super(Collection.class, javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CollectionSerializer(CollectionSerializer collectionSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        super(collectionSerializer, beanProperty, typeSerializer, jsonSerializer);
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        ContainerSerializer<?> containerSerializer;
        new CollectionSerializer(this._elementType, this._staticTyping, typeSerializer, this._property, this._elementSerializer);
        return containerSerializer;
    }

    public CollectionSerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        CollectionSerializer collectionSerializer;
        new CollectionSerializer(this, beanProperty, typeSerializer, jsonSerializer);
        return collectionSerializer;
    }

    public boolean isEmpty(Collection<?> collection) {
        Collection<?> collection2 = collection;
        return collection2 == null || collection2.isEmpty();
    }

    public boolean hasSingleElement(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        if (!it.hasNext()) {
            return false;
        }
        Object next = it.next();
        return !it.hasNext();
    }

    public void serializeContents(Collection<?> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Collection<?> collection2 = collection;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._elementSerializer != null) {
            serializeContentsUsing(collection2, jsonGenerator2, serializerProvider2, this._elementSerializer);
            return;
        }
        Iterator<?> it = collection2.iterator();
        if (it.hasNext()) {
            PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            int i = 0;
            do {
                try {
                    Object next = it.next();
                    if (next == null) {
                        serializerProvider2.defaultSerializeNull(jsonGenerator2);
                    } else {
                        Class<?> cls = next.getClass();
                        JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                        if (serializerFor == null) {
                            if (this._elementType.hasGenericTypes()) {
                                serializerFor = _findAndAddDynamic(propertySerializerMap, serializerProvider2.constructSpecializedType(this._elementType, cls), serializerProvider2);
                            } else {
                                serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider2);
                            }
                            propertySerializerMap = this._dynamicSerializers;
                        }
                        if (typeSerializer == null) {
                            serializerFor.serialize(next, jsonGenerator2, serializerProvider2);
                        } else {
                            serializerFor.serializeWithType(next, jsonGenerator2, serializerProvider2, typeSerializer);
                        }
                    }
                    i++;
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider2, (Throwable) e, (Object) collection2, i);
                }
            } while (it.hasNext());
        }
    }

    public void serializeContentsUsing(Collection<?> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        Collection<?> collection2 = collection;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        Iterator<?> it = collection2.iterator();
        if (it.hasNext()) {
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            int i = 0;
            do {
                Object next = it.next();
                if (next == null) {
                    try {
                        serializerProvider2.defaultSerializeNull(jsonGenerator2);
                    } catch (Exception e) {
                        wrapAndThrow(serializerProvider2, (Throwable) e, (Object) collection2, i);
                    }
                } else if (typeSerializer == null) {
                    jsonSerializer2.serialize(next, jsonGenerator2, serializerProvider2);
                } else {
                    jsonSerializer2.serializeWithType(next, jsonGenerator2, serializerProvider2, typeSerializer);
                }
                i++;
            } while (it.hasNext());
        }
    }
}
