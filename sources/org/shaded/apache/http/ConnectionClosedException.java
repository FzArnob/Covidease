package org.shaded.apache.http;

import java.io.IOException;

public class ConnectionClosedException extends IOException {
    private static final long serialVersionUID = 617550366255636674L;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConnectionClosedException(String message) {
        super(message);
    }
}
