package com.shaded.fasterxml.jackson.databind.exc;

import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;

public class InvalidFormatException extends JsonMappingException {
    private static final long serialVersionUID = 1;
    protected final Class<?> _targetType;
    protected final Object _value;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InvalidFormatException(String str, Object obj, Class<?> cls) {
        super(str);
        this._value = obj;
        this._targetType = cls;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InvalidFormatException(String str, JsonLocation jsonLocation, Object obj, Class<?> cls) {
        super(str, jsonLocation);
        this._value = obj;
        this._targetType = cls;
    }

    public static InvalidFormatException from(JsonParser jsonParser, String str, Object obj, Class<?> cls) {
        InvalidFormatException invalidFormatException;
        new InvalidFormatException(str, jsonParser.getTokenLocation(), obj, cls);
        return invalidFormatException;
    }

    public Object getValue() {
        return this._value;
    }

    public Class<?> getTargetType() {
        return this._targetType;
    }
}
