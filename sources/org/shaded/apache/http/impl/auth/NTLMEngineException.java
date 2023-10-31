package org.shaded.apache.http.impl.auth;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.auth.AuthenticationException;

@Immutable
public class NTLMEngineException extends AuthenticationException {
    private static final long serialVersionUID = 6027981323731768824L;

    public NTLMEngineException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NTLMEngineException(String message) {
        super(message);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NTLMEngineException(String message, Throwable cause) {
        super(message, cause);
    }
}
