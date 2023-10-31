package com.firebase.tubesock;

public class WebSocketException extends RuntimeException {
    private static final long serialVersionUID = 1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebSocketException(String message) {
        super(message);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebSocketException(String message, Throwable t) {
        super(message, t);
    }
}
