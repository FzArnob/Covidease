package org.shaded.apache.http.protocol;

import java.io.IOException;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseInterceptor;
import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.ProtocolVersion;

public class ResponseContent implements HttpResponseInterceptor {
    public ResponseContent() {
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        HttpResponse response = httpResponse;
        HttpContext httpContext2 = httpContext;
        if (response == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("HTTP request may not be null");
            throw th4;
        } else if (response.containsHeader(HTTP.TRANSFER_ENCODING)) {
            Throwable th5 = th2;
            new ProtocolException("Transfer-encoding header already present");
            throw th5;
        } else if (response.containsHeader(HTTP.CONTENT_LEN)) {
            Throwable th6 = th;
            new ProtocolException("Content-Length header already present");
            throw th6;
        } else {
            ProtocolVersion ver = response.getStatusLine().getProtocolVersion();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                long len = entity.getContentLength();
                if (entity.isChunked() && !ver.lessEquals(HttpVersion.HTTP_1_0)) {
                    response.addHeader(HTTP.TRANSFER_ENCODING, HTTP.CHUNK_CODING);
                } else if (len >= 0) {
                    response.addHeader(HTTP.CONTENT_LEN, Long.toString(entity.getContentLength()));
                }
                if (entity.getContentType() != null && !response.containsHeader(HTTP.CONTENT_TYPE)) {
                    response.addHeader(entity.getContentType());
                }
                if (entity.getContentEncoding() != null && !response.containsHeader(HTTP.CONTENT_ENCODING)) {
                    response.addHeader(entity.getContentEncoding());
                    return;
                }
                return;
            }
            int status = response.getStatusLine().getStatusCode();
            if (status != 204 && status != 304 && status != 205) {
                response.addHeader(HTTP.CONTENT_LEN, "0");
            }
        }
    }
}
