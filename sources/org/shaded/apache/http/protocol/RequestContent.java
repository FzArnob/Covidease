package org.shaded.apache.http.protocol;

import java.io.IOException;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpEntityEnclosingRequest;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestInterceptor;
import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.ProtocolVersion;

public class RequestContent implements HttpRequestInterceptor {
    public RequestContent() {
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        HttpRequest request = httpRequest;
        HttpContext httpContext2 = httpContext;
        if (request == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("HTTP request may not be null");
            throw th5;
        } else if (!(request instanceof HttpEntityEnclosingRequest)) {
        } else {
            if (request.containsHeader(HTTP.TRANSFER_ENCODING)) {
                Throwable th6 = th3;
                new ProtocolException("Transfer-encoding header already present");
                throw th6;
            } else if (request.containsHeader(HTTP.CONTENT_LEN)) {
                Throwable th7 = th2;
                new ProtocolException("Content-Length header already present");
                throw th7;
            } else {
                ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
                HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
                if (entity == null) {
                    request.addHeader(HTTP.CONTENT_LEN, "0");
                    return;
                }
                if (!entity.isChunked() && entity.getContentLength() >= 0) {
                    request.addHeader(HTTP.CONTENT_LEN, Long.toString(entity.getContentLength()));
                } else if (ver.lessEquals(HttpVersion.HTTP_1_0)) {
                    Throwable th8 = th;
                    new StringBuffer();
                    new ProtocolException(stringBuffer.append("Chunked transfer encoding not allowed for ").append(ver).toString());
                    throw th8;
                } else {
                    request.addHeader(HTTP.TRANSFER_ENCODING, HTTP.CHUNK_CODING);
                }
                if (entity.getContentType() != null && !request.containsHeader(HTTP.CONTENT_TYPE)) {
                    request.addHeader(entity.getContentType());
                }
                if (entity.getContentEncoding() != null && !request.containsHeader(HTTP.CONTENT_ENCODING)) {
                    request.addHeader(entity.getContentEncoding());
                }
            }
        }
    }
}
