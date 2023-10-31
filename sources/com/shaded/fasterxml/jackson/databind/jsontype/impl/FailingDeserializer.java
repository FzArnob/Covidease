package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class FailingDeserializer extends StdDeserializer<Object> {
    private static final long serialVersionUID = 1;
    protected final String _message;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FailingDeserializer(String str) {
        super((Class<?>) Object.class);
        this._message = str;
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JsonMappingException {
        JsonParser jsonParser2 = jsonParser;
        throw deserializationContext.mappingException(this._message);
    }
}
