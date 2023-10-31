package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public abstract class DeserializationProblemHandler {
    public DeserializationProblemHandler() {
    }

    public boolean handleUnknownProperty(DeserializationContext deserializationContext, JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException, JsonProcessingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        JsonParser jsonParser2 = jsonParser;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        Object obj2 = obj;
        String str2 = str;
        return false;
    }
}
