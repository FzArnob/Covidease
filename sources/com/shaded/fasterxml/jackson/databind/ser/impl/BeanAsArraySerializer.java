package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

public class BeanAsArraySerializer extends BeanSerializerBase {
    protected final BeanSerializerBase _defaultSerializer;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanAsArraySerializer(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            r4 = 0
            com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r4 = (com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter) r4
            r2.<init>((com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase) r3, (com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter) r4)
            r2 = r0
            r3 = r1
            r2._defaultSerializer = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.impl.BeanAsArraySerializer.<init>(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BeanAsArraySerializer(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase r7, java.lang.String[] r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>((com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase) r4, (java.lang.String[]) r5)
            r3 = r0
            r4 = r1
            r3._defaultSerializer = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.impl.BeanAsArraySerializer.<init>(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase, java.lang.String[]):void");
    }

    public JsonSerializer<Object> unwrappingSerializer(NameTransformer nameTransformer) {
        return this._defaultSerializer.unwrappingSerializer(nameTransformer);
    }

    public boolean isUnwrappingSerializer() {
        return false;
    }

    public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter) {
        return this._defaultSerializer.withObjectIdWriter(objectIdWriter);
    }

    /* access modifiers changed from: protected */
    public BeanAsArraySerializer withIgnorals(String[] strArr) {
        BeanAsArraySerializer beanAsArraySerializer;
        new BeanAsArraySerializer(this, strArr);
        return beanAsArraySerializer;
    }

    /* access modifiers changed from: protected */
    public BeanSerializerBase asArraySerializer() {
        return this;
    }

    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        this._defaultSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
    }

    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (!serializerProvider2.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || !hasSingleElement(serializerProvider2)) {
            jsonGenerator2.writeStartArray();
            serializeAsArray(obj2, jsonGenerator2, serializerProvider2);
            jsonGenerator2.writeEndArray();
            return;
        }
        serializeAsArray(obj2, jsonGenerator2, serializerProvider2);
    }

    private boolean hasSingleElement(SerializerProvider serializerProvider) {
        BeanPropertyWriter[] beanPropertyWriterArr;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._filteredProps == null || serializerProvider2.getActiveView() == null) {
            beanPropertyWriterArr = this._props;
        } else {
            beanPropertyWriterArr = this._filteredProps;
        }
        return beanPropertyWriterArr.length == 1;
    }

    /* access modifiers changed from: protected */
    public final void serializeAsArray(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        JsonMappingException jsonMappingException;
        JsonMappingException.Reference reference;
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._filteredProps == null || serializerProvider2.getActiveView() == null) {
            beanPropertyWriterArr = this._props;
        } else {
            beanPropertyWriterArr = this._filteredProps;
        }
        int i = 0;
        try {
            int length = beanPropertyWriterArr.length;
            while (i < length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (beanPropertyWriter == null) {
                    jsonGenerator2.writeNull();
                } else {
                    beanPropertyWriter.serializeAsColumn(obj2, jsonGenerator2, serializerProvider2);
                }
                i++;
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider2, (Throwable) e, obj2, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName());
        } catch (StackOverflowError e2) {
            new JsonMappingException("Infinite recursion (StackOverflowError)", (Throwable) e2);
            JsonMappingException jsonMappingException2 = jsonMappingException;
            new JsonMappingException.Reference(obj2, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName());
            jsonMappingException2.prependPath(reference);
            throw jsonMappingException2;
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("BeanAsArraySerializer for ").append(handledType().getName()).toString();
    }
}
