package org.shaded.apache.http.protocol;

import java.io.IOException;
import org.shaded.apache.http.Header;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseInterceptor;
import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.ProtocolVersion;

public class ResponseConnControl implements HttpResponseInterceptor {
    public ResponseConnControl() {
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        Header header;
        Throwable th;
        Throwable th2;
        HttpResponse response = httpResponse;
        HttpContext context = httpContext;
        if (response == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("HTTP response may not be null");
            throw th3;
        } else if (context == null) {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP context may not be null");
            throw th4;
        } else {
            int status = response.getStatusLine().getStatusCode();
            if (status == 400 || status == 408 || status == 411 || status == 413 || status == 414 || status == 503 || status == 501) {
                response.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
                return;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                ProtocolVersion ver = response.getStatusLine().getProtocolVersion();
                if (entity.getContentLength() < 0 && (!entity.isChunked() || ver.lessEquals(HttpVersion.HTTP_1_0))) {
                    response.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
                    return;
                }
            }
            HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
            if (request != null && (header = request.getFirstHeader(HTTP.CONN_DIRECTIVE)) != null) {
                response.setHeader(HTTP.CONN_DIRECTIVE, header.getValue());
            }
        }
    }
}
