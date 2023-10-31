package org.shaded.apache.http.auth;

import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class InvalidCredentialsException extends AuthenticationException {
    private static final long serialVersionUID = -4834003835215460648L;

    public InvalidCredentialsException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InvalidCredentialsException(String message) {
        super(message);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
