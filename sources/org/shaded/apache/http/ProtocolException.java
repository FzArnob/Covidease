package org.shaded.apache.http;

public class ProtocolException extends HttpException {
    private static final long serialVersionUID = -2143571074341228994L;

    public ProtocolException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProtocolException(String message) {
        super(message);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProtocolException(String message, Throwable cause) {
        super(message, cause);
    }
}
