package com.shaded.fasterxml.jackson.databind.deser.impl;

import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.JavaType;
import java.io.Serializable;

public final class NullProvider implements Serializable {
    private static final long serialVersionUID = 1;
    private final boolean _isPrimitive;
    private final Object _nullValue;
    private final Class<?> _rawType;

    public NullProvider(JavaType javaType, Object obj) {
        JavaType javaType2 = javaType;
        this._nullValue = obj;
        this._isPrimitive = javaType2.isPrimitive();
        this._rawType = javaType2.getRawClass();
    }

    public Object nullValue(DeserializationContext deserializationContext) throws JsonProcessingException {
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (!this._isPrimitive || !deserializationContext2.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            return this._nullValue;
        }
        new StringBuilder();
        throw deserializationContext2.mappingException(sb.append("Can not map JSON null into type ").append(this._rawType.getName()).append(" (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)").toString());
    }
}
