package org.shaded.apache.http.conn;

import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class ConnectionPoolTimeoutException extends ConnectTimeoutException {
    private static final long serialVersionUID = -7898874842020245128L;

    public ConnectionPoolTimeoutException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConnectionPoolTimeoutException(String message) {
        super(message);
    }
}
