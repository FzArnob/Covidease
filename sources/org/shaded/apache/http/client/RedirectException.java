package org.shaded.apache.http.client;

import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class RedirectException extends ProtocolException {
    private static final long serialVersionUID = 4418824536372559326L;

    public RedirectException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RedirectException(String message) {
        super(message);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RedirectException(String message, Throwable cause) {
        super(message, cause);
    }
}
