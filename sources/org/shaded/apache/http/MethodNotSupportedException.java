package org.shaded.apache.http;

public class MethodNotSupportedException extends HttpException {
    private static final long serialVersionUID = 3365359036840171201L;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MethodNotSupportedException(String message) {
        super(message);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MethodNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
