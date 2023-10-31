package com.shaded.fasterxml.jackson.databind.ser;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.ser.impl.BeanAsArraySerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.shaded.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer;
import com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

public class BeanSerializer extends BeanSerializerBase {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BeanSerializer(JavaType javaType, BeanSerializerBuilder beanSerializerBuilder, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(javaType, beanSerializerBuilder, beanPropertyWriterArr, beanPropertyWriterArr2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BeanSerializer(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BeanSerializer(BeanSerializerBase beanSerializerBase, ObjectIdWriter objectIdWriter) {
        super(beanSerializerBase, objectIdWriter);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BeanSerializer(BeanSerializerBase beanSerializerBase, String[] strArr) {
        super(beanSerializerBase, strArr);
    }

    public static BeanSerializer createDummy(JavaType javaType) {
        BeanSerializer beanSerializer;
        new BeanSerializer(javaType, (BeanSerializerBuilder) null, NO_PROPS, (BeanPropertyWriter[]) null);
        return beanSerializer;
    }

    public JsonSerializer<Object> unwrappingSerializer(NameTransformer nameTransformer) {
        JsonSerializer<Object> jsonSerializer;
        new UnwrappingBeanSerializer((BeanSerializerBase) this, nameTransformer);
        return jsonSerializer;
    }

    public BeanSerializer withObjectIdWriter(ObjectIdWriter objectIdWriter) {
        BeanSerializer beanSerializer;
        new BeanSerializer((BeanSerializerBase) this, objectIdWriter);
        return beanSerializer;
    }

    /* access modifiers changed from: protected */
    public BeanSerializer withIgnorals(String[] strArr) {
        BeanSerializer beanSerializer;
        new BeanSerializer((BeanSerializerBase) this, strArr);
        return beanSerializer;
    }

    /* access modifiers changed from: protected */
    public BeanSerializerBase asArraySerializer() {
        BeanSerializerBase beanSerializerBase;
        if (this._objectIdWriter == null && this._anyGetterWriter == null && this._propertyFilterId == null) {
            new BeanAsArraySerializer(this);
            return beanSerializerBase;
        }
        return this;
    }

    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._objectIdWriter != null) {
            _serializeWithObjectId(obj2, jsonGenerator2, serializerProvider2, true);
            return;
        }
        jsonGenerator2.writeStartObject();
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj2, jsonGenerator2, serializerProvider2);
        } else {
            serializeFields(obj2, jsonGenerator2, serializerProvider2);
        }
        jsonGenerator2.writeEndObject();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("BeanSerializer for ").append(handledType().getName()).toString();
    }
}
