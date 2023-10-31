package org.shaded.apache.http.client;

import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class HttpResponseException extends ClientProtocolException {
    private static final long serialVersionUID = -7186627969477257933L;
    private final int statusCode;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HttpResponseException(int statusCode2, String s) {
        super(s);
        this.statusCode = statusCode2;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
