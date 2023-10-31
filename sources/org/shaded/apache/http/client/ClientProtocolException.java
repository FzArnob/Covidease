package org.shaded.apache.http.client;

import java.io.IOException;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class ClientProtocolException extends IOException {
    private static final long serialVersionUID = -5596590843227115865L;

    public ClientProtocolException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClientProtocolException(String s) {
        super(s);
    }

    public ClientProtocolException(Throwable cause) {
        Throwable initCause = initCause(cause);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClientProtocolException(String message, Throwable cause) {
        super(message);
        Throwable initCause = initCause(cause);
    }
}
