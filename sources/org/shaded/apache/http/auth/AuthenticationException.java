package org.shaded.apache.http.auth;

import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class AuthenticationException extends ProtocolException {
    private static final long serialVersionUID = -6794031905674764776L;

    public AuthenticationException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AuthenticationException(String message) {
        super(message);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
