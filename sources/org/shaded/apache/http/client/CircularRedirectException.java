package org.shaded.apache.http.client;

import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class CircularRedirectException extends RedirectException {
    private static final long serialVersionUID = 6830063487001091803L;

    public CircularRedirectException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CircularRedirectException(String message) {
        super(message);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CircularRedirectException(String message, Throwable cause) {
        super(message, cause);
    }
}
