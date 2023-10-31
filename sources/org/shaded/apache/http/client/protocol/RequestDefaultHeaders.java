package org.shaded.apache.http.client.protocol;

import java.io.IOException;
import java.util.Collection;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestInterceptor;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.client.params.ClientPNames;
import org.shaded.apache.http.protocol.HttpContext;

@Immutable
public class RequestDefaultHeaders implements HttpRequestInterceptor {
    public RequestDefaultHeaders() {
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Collection<Header> defHeaders;
        Throwable th;
        HttpRequest request = httpRequest;
        HttpContext httpContext2 = httpContext;
        if (request == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP request may not be null");
            throw th2;
        } else if (!request.getRequestLine().getMethod().equalsIgnoreCase("CONNECT") && (defHeaders = (Collection) request.getParams().getParameter(ClientPNames.DEFAULT_HEADERS)) != null) {
            for (Header defHeader : defHeaders) {
                request.addHeader(defHeader);
            }
        }
    }
}
