package com.google.gson;

public final class JsonSyntaxException extends JsonParseException {
    private static final long serialVersionUID = 1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonSyntaxException(String msg) {
        super(msg);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonSyntaxException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonSyntaxException(Throwable cause) {
        super(cause);
    }
}
