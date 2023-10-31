package org.shaded.apache.http.protocol;

import java.io.IOException;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestInterceptor;

public class RequestConnControl implements HttpRequestInterceptor {
    public RequestConnControl() {
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        HttpRequest request = httpRequest;
        HttpContext httpContext2 = httpContext;
        if (request == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP request may not be null");
            throw th2;
        } else if (!request.getRequestLine().getMethod().equalsIgnoreCase("CONNECT") && !request.containsHeader(HTTP.CONN_DIRECTIVE)) {
            request.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        }
    }
}
