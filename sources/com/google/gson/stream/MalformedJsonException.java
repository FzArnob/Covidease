package com.google.gson.stream;

import java.io.IOException;

public final class MalformedJsonException extends IOException {
    private static final long serialVersionUID = 1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MalformedJsonException(String msg) {
        super(msg);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MalformedJsonException(String msg, Throwable throwable) {
        super(msg);
        Throwable initCause = initCause(throwable);
    }

    public MalformedJsonException(Throwable throwable) {
        Throwable initCause = initCause(throwable);
    }
}
