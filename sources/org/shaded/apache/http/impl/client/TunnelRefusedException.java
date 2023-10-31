package org.shaded.apache.http.impl.client;

import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.annotation.Immutable;

@Immutable
public class TunnelRefusedException extends HttpException {
    private static final long serialVersionUID = -8646722842745617323L;
    private final HttpResponse response;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TunnelRefusedException(String message, HttpResponse response2) {
        super(message);
        this.response = response2;
    }

    public HttpResponse getResponse() {
        return this.response;
    }
}
