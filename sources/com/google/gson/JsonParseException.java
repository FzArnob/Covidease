package com.google.gson;

public class JsonParseException extends RuntimeException {
    static final long serialVersionUID = -4086729973971783390L;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonParseException(String msg) {
        super(msg);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonParseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonParseException(Throwable cause) {
        super(cause);
    }
}
