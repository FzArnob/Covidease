package org.shaded.apache.http.cookie;

import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class MalformedCookieException extends ProtocolException {
    private static final long serialVersionUID = -6695462944287282185L;

    public MalformedCookieException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MalformedCookieException(String message) {
        super(message);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MalformedCookieException(String message, Throwable cause) {
        super(message, cause);
    }
}
