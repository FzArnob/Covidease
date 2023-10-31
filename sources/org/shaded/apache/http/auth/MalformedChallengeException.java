package org.shaded.apache.http.auth;

import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class MalformedChallengeException extends ProtocolException {
    private static final long serialVersionUID = 814586927989932284L;

    public MalformedChallengeException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MalformedChallengeException(String message) {
        super(message);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MalformedChallengeException(String message, Throwable cause) {
        super(message, cause);
    }
}
