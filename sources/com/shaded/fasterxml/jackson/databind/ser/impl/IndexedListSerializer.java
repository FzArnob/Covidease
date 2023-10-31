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
import java.util.List;

@JacksonStdImpl
public final class IndexedListSerializer extends AsArraySerializerBase<List<?>> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IndexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        super(List.class, javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IndexedListSerializer(IndexedListSerializer indexedListSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        super(indexedListSerializer, beanProperty, typeSerializer, jsonSerializer);
    }

    public IndexedListSerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        IndexedListSerializer indexedListSerializer;
        new IndexedListSerializer(this, beanProperty, typeSerializer, jsonSerializer);
        return indexedListSerializer;
    }

    public boolean isEmpty(List<?> list) {
        List<?> list2 = list;
        return list2 == null || list2.isEmpty();
    }

    public boolean hasSingleElement(List<?> list) {
        return list.size() == 1;
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        ContainerSerializer<?> containerSerializer;
        new IndexedListSerializer(this._elementType, this._staticTyping, typeSerializer, this._property, this._elementSerializer);
        return containerSerializer;
    }

    public void serializeContents(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        List<?> list2 = list;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._elementSerializer != null) {
            serializeContentsUsing(list2, jsonGenerator2, serializerProvider2, this._elementSerializer);
        } else if (this._valueTypeSerializer != null) {
            serializeTypedContents(list2, jsonGenerator2, serializerProvider2);
        } else {
            int size = list2.size();
            if (size != 0) {
                int i = 0;
                try {
                    PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                    while (i < size) {
                        Object obj = list2.get(i);
                        if (obj == null) {
                            serializerProvider2.defaultSerializeNull(jsonGenerator2);
                        } else {
                            Class<?> cls = obj.getClass();
                            JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                            if (serializerFor == null) {
                                if (this._elementType.hasGenericTypes()) {
                                    serializerFor = _findAndAddDynamic(propertySerializerMap, serializerProvider2.constructSpecializedType(this._elementType, cls), serializerProvider2);
                                } else {
                                    serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider2);
                                }
                                propertySerializerMap = this._dynamicSerializers;
                            }
                            serializerFor.serialize(obj, jsonGenerator2, serializerProvider2);
                        }
                        i++;
                    }
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider2, (Throwable) e, (Object) list2, i);
                }
            }
        }
    }

    public void serializeContentsUsing(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        List<?> list2 = list;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        int size = list2.size();
        if (size != 0) {
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            for (int i = 0; i < size; i++) {
                Object obj = list2.get(i);
                if (obj == null) {
                    try {
                        serializerProvider2.defaultSerializeNull(jsonGenerator2);
                    } catch (Exception e) {
                        wrapAndThrow(serializerProvider2, (Throwable) e, (Object) list2, i);
                    }
                } else if (typeSerializer == null) {
                    jsonSerializer2.serialize(obj, jsonGenerator2, serializerProvider2);
                } else {
                    jsonSerializer2.serializeWithType(obj, jsonGenerator2, serializerProvider2, typeSerializer);
                }
            }
        }
    }

    public void serializeTypedContents(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        List<?> list2 = list;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        int size = list2.size();
        if (size != 0) {
            int i = 0;
            try {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                while (i < size) {
                    Object obj = list2.get(i);
                    if (obj == null) {
                        serializerProvider2.defaultSerializeNull(jsonGenerator2);
                    } else {
                        Class<?> cls = obj.getClass();
                        JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                        if (serializerFor == null) {
                            if (this._elementType.hasGenericTypes()) {
                                serializerFor = _findAndAddDynamic(propertySerializerMap, serializerProvider2.constructSpecializedType(this._elementType, cls), serializerProvider2);
                            } else {
                                serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider2);
                            }
                            propertySerializerMap = this._dynamicSerializers;
                        }
                        serializerFor.serializeWithType(obj, jsonGenerator2, serializerProvider2, typeSerializer);
                    }
                    i++;
                }
            } catch (Exception e) {
                wrapAndThrow(serializerProvider2, (Throwable) e, (Object) list2, i);
            }
        }
    }
}
