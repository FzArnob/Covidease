package org.shaded.apache.http.protocol;

import java.io.IOException;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpEntityEnclosingRequest;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestInterceptor;
import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.params.HttpProtocolParams;

public class RequestExpectContinue implements HttpRequestInterceptor {
    public RequestExpectContinue() {
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        HttpEntity entity;
        Throwable th;
        HttpRequest request = httpRequest;
        HttpContext httpContext2 = httpContext;
        if (request == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP request may not be null");
            throw th2;
        } else if ((request instanceof HttpEntityEnclosingRequest) && (entity = ((HttpEntityEnclosingRequest) request).getEntity()) != null && entity.getContentLength() != 0) {
            ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
            if (HttpProtocolParams.useExpectContinue(request.getParams()) && !ver.lessEquals(HttpVersion.HTTP_1_0)) {
                request.addHeader(HTTP.EXPECT_DIRECTIVE, HTTP.EXPECT_CONTINUE);
            }
        }
    }
}
