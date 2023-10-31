package org.shaded.apache.http.client;

import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class NonRepeatableRequestException extends ProtocolException {
    private static final long serialVersionUID = 82685265288806048L;

    public NonRepeatableRequestException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NonRepeatableRequestException(String message) {
        super(message);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NonRepeatableRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
