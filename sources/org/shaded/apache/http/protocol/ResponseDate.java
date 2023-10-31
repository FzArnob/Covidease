package org.shaded.apache.http.protocol;

import java.io.IOException;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseInterceptor;

public class ResponseDate implements HttpResponseInterceptor {
    private static final HttpDateGenerator DATE_GENERATOR;

    static {
        HttpDateGenerator httpDateGenerator;
        new HttpDateGenerator();
        DATE_GENERATOR = httpDateGenerator;
    }

    public ResponseDate() {
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        HttpResponse response = httpResponse;
        HttpContext httpContext2 = httpContext;
        if (response == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP response may not be null.");
            throw th2;
        } else if (response.getStatusLine().getStatusCode() >= 200 && !response.containsHeader(HTTP.DATE_HEADER)) {
            response.setHeader(HTTP.DATE_HEADER, DATE_GENERATOR.getCurrentDate());
        }
    }
}
