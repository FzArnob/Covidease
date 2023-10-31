package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.core.p005io.SerializedString;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;

public class UnwrappingBeanPropertyWriter extends BeanPropertyWriter {
    protected final NameTransformer _nameTransformer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnwrappingBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, NameTransformer nameTransformer) {
        super(beanPropertyWriter);
        this._nameTransformer = nameTransformer;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private UnwrappingBeanPropertyWriter(UnwrappingBeanPropertyWriter unwrappingBeanPropertyWriter, NameTransformer nameTransformer, SerializedString serializedString) {
        super(unwrappingBeanPropertyWriter, serializedString);
        this._nameTransformer = nameTransformer;
    }

    public UnwrappingBeanPropertyWriter rename(NameTransformer nameTransformer) {
        UnwrappingBeanPropertyWriter unwrappingBeanPropertyWriter;
        SerializedString serializedString;
        NameTransformer nameTransformer2 = nameTransformer;
        String transform = nameTransformer2.transform(this._name.getValue());
        NameTransformer chainedTransformer = NameTransformer.chainedTransformer(nameTransformer2, this._nameTransformer);
        new SerializedString(transform);
        new UnwrappingBeanPropertyWriter(this, chainedTransformer, serializedString);
        return unwrappingBeanPropertyWriter;
    }

    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        Object obj3 = get(obj2);
        if (obj3 != null) {
            JsonSerializer<Object> jsonSerializer = this._serializer;
            if (jsonSerializer == null) {
                Class<?> cls = obj3.getClass();
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                jsonSerializer = propertySerializerMap.serializerFor(cls);
                if (jsonSerializer == null) {
                    jsonSerializer = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider2);
                }
            }
            if (this._suppressableValue != null) {
                if (MARKER_FOR_EMPTY == this._suppressableValue) {
                    if (jsonSerializer.isEmpty(obj3)) {
                        return;
                    }
                } else if (this._suppressableValue.equals(obj3)) {
                    return;
                }
            }
            if (obj3 == obj2) {
                _handleSelfReference(obj2, jsonSerializer);
            }
            if (!jsonSerializer.isUnwrappingSerializer()) {
                jsonGenerator2.writeFieldName((SerializableString) this._name);
            }
            if (this._typeSerializer == null) {
                jsonSerializer.serialize(obj3, jsonGenerator2, serializerProvider2);
            } else {
                jsonSerializer.serializeWithType(obj3, jsonGenerator2, serializerProvider2, this._typeSerializer);
            }
        }
    }

    public void assignSerializer(JsonSerializer<Object> jsonSerializer) {
        super.assignSerializer(jsonSerializer);
        if (this._serializer != null) {
            NameTransformer nameTransformer = this._nameTransformer;
            if (this._serializer.isUnwrappingSerializer()) {
                nameTransformer = NameTransformer.chainedTransformer(nameTransformer, ((UnwrappingBeanSerializer) this._serializer)._nameTransformer);
            }
            this._serializer = this._serializer.unwrappingSerializer(nameTransformer);
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        JsonSerializer<Object> findValueSerializer;
        PropertySerializerMap propertySerializerMap2 = propertySerializerMap;
        Class<?> cls2 = cls;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._nonTrivialBaseType != null) {
            findValueSerializer = serializerProvider2.findValueSerializer(serializerProvider2.constructSpecializedType(this._nonTrivialBaseType, cls2), (BeanProperty) this);
        } else {
            findValueSerializer = serializerProvider2.findValueSerializer(cls2, (BeanProperty) this);
        }
        NameTransformer nameTransformer = this._nameTransformer;
        if (findValueSerializer.isUnwrappingSerializer()) {
            nameTransformer = NameTransformer.chainedTransformer(nameTransformer, ((UnwrappingBeanSerializer) findValueSerializer)._nameTransformer);
        }
        JsonSerializer<Object> unwrappingSerializer = findValueSerializer.unwrappingSerializer(nameTransformer);
        this._dynamicSerializers = this._dynamicSerializers.newWith(cls2, unwrappingSerializer);
        return unwrappingSerializer;
    }
}
