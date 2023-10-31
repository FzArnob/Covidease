package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

public class UnwrappingBeanSerializer extends BeanSerializerBase {
    protected final NameTransformer _nameTransformer;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public UnwrappingBeanSerializer(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase r7, com.shaded.fasterxml.jackson.databind.util.NameTransformer r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>((com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase) r4, (com.shaded.fasterxml.jackson.databind.util.NameTransformer) r5)
            r3 = r0
            r4 = r2
            r3._nameTransformer = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer.<init>(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.shaded.fasterxml.jackson.databind.util.NameTransformer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public UnwrappingBeanSerializer(com.shaded.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer r7, com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>((com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase) r4, (com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter) r5)
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.util.NameTransformer r4 = r4._nameTransformer
            r3._nameTransformer = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer.<init>(com.shaded.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer, com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected UnwrappingBeanSerializer(com.shaded.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer r7, java.lang.String[] r8) {
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
            com.shaded.fasterxml.jackson.databind.util.NameTransformer r4 = r4._nameTransformer
            r3._nameTransformer = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer.<init>(com.shaded.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer, java.lang.String[]):void");
    }

    public JsonSerializer<Object> unwrappingSerializer(NameTransformer nameTransformer) {
        JsonSerializer<Object> jsonSerializer;
        new UnwrappingBeanSerializer((BeanSerializerBase) this, nameTransformer);
        return jsonSerializer;
    }

    public boolean isUnwrappingSerializer() {
        return true;
    }

    public UnwrappingBeanSerializer withObjectIdWriter(ObjectIdWriter objectIdWriter) {
        UnwrappingBeanSerializer unwrappingBeanSerializer;
        new UnwrappingBeanSerializer(this, objectIdWriter);
        return unwrappingBeanSerializer;
    }

    /* access modifiers changed from: protected */
    public UnwrappingBeanSerializer withIgnorals(String[] strArr) {
        UnwrappingBeanSerializer unwrappingBeanSerializer;
        new UnwrappingBeanSerializer(this, strArr);
        return unwrappingBeanSerializer;
    }

    /* access modifiers changed from: protected */
    public BeanSerializerBase asArraySerializer() {
        return this;
    }

    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._objectIdWriter != null) {
            _serializeWithObjectId(obj2, jsonGenerator2, serializerProvider2, false);
        } else if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj2, jsonGenerator2, serializerProvider2);
        } else {
            serializeFields(obj2, jsonGenerator2, serializerProvider2);
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("UnwrappingBeanSerializer for ").append(handledType().getName()).toString();
    }
}
