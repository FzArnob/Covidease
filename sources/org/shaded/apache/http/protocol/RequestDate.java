package org.shaded.apache.http.protocol;

import java.io.IOException;
import org.shaded.apache.http.HttpEntityEnclosingRequest;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestInterceptor;

public class RequestDate implements HttpRequestInterceptor {
    private static final HttpDateGenerator DATE_GENERATOR;

    static {
        HttpDateGenerator httpDateGenerator;
        new HttpDateGenerator();
        DATE_GENERATOR = httpDateGenerator;
    }

    public RequestDate() {
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        HttpRequest request = httpRequest;
        HttpContext httpContext2 = httpContext;
        if (request == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP request may not be null.");
            throw th2;
        } else if ((request instanceof HttpEntityEnclosingRequest) && !request.containsHeader(HTTP.DATE_HEADER)) {
            request.setHeader(HTTP.DATE_HEADER, DATE_GENERATOR.getCurrentDate());
        }
    }
}
