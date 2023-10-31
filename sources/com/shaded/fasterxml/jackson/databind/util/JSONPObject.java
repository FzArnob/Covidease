package com.shaded.fasterxml.jackson.databind.util;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonSerializable;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public class JSONPObject implements JsonSerializable {
    protected final String _function;
    protected final JavaType _serializationType;
    protected final Object _value;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JSONPObject(String str, Object obj) {
        this(str, obj, (JavaType) null);
    }

    public JSONPObject(String str, Object obj, JavaType javaType) {
        this._function = str;
        this._value = obj;
        this._serializationType = javaType;
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        TypeSerializer typeSerializer2 = typeSerializer;
        serialize(jsonGenerator, serializerProvider);
    }

    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        jsonGenerator2.writeRaw(this._function);
        jsonGenerator2.writeRaw('(');
        if (this._value == null) {
            serializerProvider2.defaultSerializeNull(jsonGenerator2);
        } else if (this._serializationType != null) {
            serializerProvider2.findTypedValueSerializer(this._serializationType, true, (BeanProperty) null).serialize(this._value, jsonGenerator2, serializerProvider2);
        } else {
            serializerProvider2.findTypedValueSerializer(this._value.getClass(), true, (BeanProperty) null).serialize(this._value, jsonGenerator2, serializerProvider2);
        }
        jsonGenerator2.writeRaw(')');
    }

    public String getFunction() {
        return this._function;
    }

    public Object getValue() {
        return this._value;
    }

    public JavaType getSerializationType() {
        return this._serializationType;
    }
}
