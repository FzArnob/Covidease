package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.core.p005io.SerializedString;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public final class WritableObjectId {
    public final ObjectIdGenerator<?> generator;

    /* renamed from: id */
    public Object f289id;
    protected boolean idWritten = false;

    public WritableObjectId(ObjectIdGenerator<?> objectIdGenerator) {
        this.generator = objectIdGenerator;
    }

    public boolean writeAsId(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, ObjectIdWriter objectIdWriter) throws IOException, JsonGenerationException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        ObjectIdWriter objectIdWriter2 = objectIdWriter;
        if (this.f289id == null || (!this.idWritten && !objectIdWriter2.alwaysAsId)) {
            return false;
        }
        objectIdWriter2.serializer.serialize(this.f289id, jsonGenerator2, serializerProvider2);
        return true;
    }

    public Object generateId(Object obj) {
        Object generateId = this.generator.generateId(obj);
        Object obj2 = generateId;
        this.f289id = obj2;
        return generateId;
    }

    public void writeAsField(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, ObjectIdWriter objectIdWriter) throws IOException, JsonGenerationException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        ObjectIdWriter objectIdWriter2 = objectIdWriter;
        SerializedString serializedString = objectIdWriter2.propertyName;
        this.idWritten = true;
        if (serializedString != null) {
            jsonGenerator2.writeFieldName((SerializableString) serializedString);
            objectIdWriter2.serializer.serialize(this.f289id, jsonGenerator2, serializerProvider2);
        }
    }
}
