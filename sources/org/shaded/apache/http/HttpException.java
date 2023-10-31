package org.shaded.apache.http;

import org.shaded.apache.http.util.ExceptionUtils;

public class HttpException extends Exception {
    private static final long serialVersionUID = -5437299376222011036L;

    public HttpException() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpException(String message) {
        super(message);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpException(String message, Throwable cause) {
        super(message);
        ExceptionUtils.initCause(this, cause);
    }
}
