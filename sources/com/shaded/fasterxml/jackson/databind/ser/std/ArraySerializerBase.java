package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.SerializationFeature;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;

public abstract class ArraySerializerBase<T> extends ContainerSerializer<T> {
    protected final BeanProperty _property;

    /* access modifiers changed from: protected */
    public abstract void serializeContents(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ArraySerializerBase(Class<T> cls) {
        super(cls);
        this._property = null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ArraySerializerBase(Class<T> cls, BeanProperty beanProperty) {
        super(cls);
        this._property = beanProperty;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected ArraySerializerBase(com.shaded.fasterxml.jackson.databind.ser.std.ArraySerializerBase<?> r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            java.lang.Class r3 = r3._handledType
            r4 = 0
            r2.<init>(r3, r4)
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.BeanProperty r3 = r3._property
            r2._property = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.ArraySerializerBase.<init>(com.shaded.fasterxml.jackson.databind.ser.std.ArraySerializerBase):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ArraySerializerBase(ArraySerializerBase<?> arraySerializerBase, BeanProperty beanProperty) {
        super(arraySerializerBase._handledType, false);
        this._property = beanProperty;
    }

    public final void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        T t2 = t;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (!serializerProvider2.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || !hasSingleElement(t2)) {
            jsonGenerator2.writeStartArray();
            serializeContents(t2, jsonGenerator2, serializerProvider2);
            jsonGenerator2.writeEndArray();
            return;
        }
        serializeContents(t2, jsonGenerator2, serializerProvider2);
    }

    public final void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        T t2 = t;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        TypeSerializer typeSerializer2 = typeSerializer;
        typeSerializer2.writeTypePrefixForArray(t2, jsonGenerator2);
        serializeContents(t2, jsonGenerator2, serializerProvider);
        typeSerializer2.writeTypeSuffixForArray(t2, jsonGenerator2);
    }
}
