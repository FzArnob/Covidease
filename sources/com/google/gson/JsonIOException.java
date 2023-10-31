package com.google.gson;

public final class JsonIOException extends JsonParseException {
    private static final long serialVersionUID = 1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonIOException(String msg) {
        super(msg);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonIOException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonIOException(Throwable cause) {
        super(cause);
    }
}
