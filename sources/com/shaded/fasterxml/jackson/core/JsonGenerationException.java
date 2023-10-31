package com.shaded.fasterxml.jackson.core;

public class JsonGenerationException extends JsonProcessingException {
    private static final long serialVersionUID = 123;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonGenerationException(Throwable th) {
        super(th);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonGenerationException(String str) {
        super(str, (JsonLocation) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonGenerationException(String str, Throwable th) {
        super(str, (JsonLocation) null, th);
    }
}
