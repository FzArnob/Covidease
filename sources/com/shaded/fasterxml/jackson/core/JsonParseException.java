package com.shaded.fasterxml.jackson.core;

public class JsonParseException extends JsonProcessingException {
    static final long serialVersionUID = 123;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonParseException(String str, JsonLocation jsonLocation) {
        super(str, jsonLocation);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonParseException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, jsonLocation, th);
    }
}
