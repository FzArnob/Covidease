package org.shaded.apache.http.conn;

import java.io.InterruptedIOException;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class ConnectTimeoutException extends InterruptedIOException {
    private static final long serialVersionUID = -4816682903149535989L;

    public ConnectTimeoutException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConnectTimeoutException(String message) {
        super(message);
    }
}
