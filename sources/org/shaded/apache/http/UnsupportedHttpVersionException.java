package org.shaded.apache.http;

public class UnsupportedHttpVersionException extends ProtocolException {
    private static final long serialVersionUID = -1348448090193107031L;

    public UnsupportedHttpVersionException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnsupportedHttpVersionException(String message) {
        super(message);
    }
}
