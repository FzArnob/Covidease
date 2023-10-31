package org.shaded.apache.http;

import java.io.IOException;

public class NoHttpResponseException extends IOException {
    private static final long serialVersionUID = -7658940387386078766L;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NoHttpResponseException(String message) {
        super(message);
    }
}
