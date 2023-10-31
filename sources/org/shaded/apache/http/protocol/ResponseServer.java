package org.shaded.apache.http.protocol;

import java.io.IOException;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseInterceptor;
import org.shaded.apache.http.params.CoreProtocolPNames;

public class ResponseServer implements HttpResponseInterceptor {
    public ResponseServer() {
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        String s;
        Throwable th;
        HttpResponse response = httpResponse;
        HttpContext httpContext2 = httpContext;
        if (response == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP request may not be null");
            throw th2;
        } else if (!response.containsHeader(HTTP.SERVER_HEADER) && (s = (String) response.getParams().getParameter(CoreProtocolPNames.ORIGIN_SERVER)) != null) {
            response.addHeader(HTTP.SERVER_HEADER, s);
        }
    }
}
