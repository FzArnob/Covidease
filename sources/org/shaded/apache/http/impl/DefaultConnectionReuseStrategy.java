package org.shaded.apache.http.impl;

import org.shaded.apache.http.ConnectionReuseStrategy;
import org.shaded.apache.http.HeaderIterator;
import org.shaded.apache.http.HttpConnection;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.ParseException;
import org.shaded.apache.http.ProtocolVersion;
import org.shaded.apache.http.TokenIterator;
import org.shaded.apache.http.message.BasicTokenIterator;
import org.shaded.apache.http.protocol.ExecutionContext;
import org.shaded.apache.http.protocol.HTTP;
import org.shaded.apache.http.protocol.HttpContext;

public class DefaultConnectionReuseStrategy implements ConnectionReuseStrategy {
    public DefaultConnectionReuseStrategy() {
    }

    public boolean keepAlive(HttpResponse httpResponse, HttpContext httpContext) {
        boolean z;
        Throwable th;
        Throwable th2;
        HttpResponse response = httpResponse;
        HttpContext context = httpContext;
        if (response == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("HTTP response may not be null.");
            throw th3;
        } else if (context == null) {
            Throwable th4 = th;
            new IllegalArgumentException("HTTP context may not be null.");
            throw th4;
        } else {
            HttpConnection conn = (HttpConnection) context.getAttribute(ExecutionContext.HTTP_CONNECTION);
            if (conn != null && !conn.isOpen()) {
                return false;
            }
            HttpEntity entity = response.getEntity();
            ProtocolVersion ver = response.getStatusLine().getProtocolVersion();
            if (entity != null && entity.getContentLength() < 0 && (!entity.isChunked() || ver.lessEquals(HttpVersion.HTTP_1_0))) {
                return false;
            }
            HeaderIterator hit = response.headerIterator(HTTP.CONN_DIRECTIVE);
            if (!hit.hasNext()) {
                hit = response.headerIterator("Proxy-Connection");
            }
            if (hit.hasNext()) {
                try {
                    TokenIterator ti = createTokenIterator(hit);
                    boolean keepalive = false;
                    while (ti.hasNext()) {
                        String token = ti.nextToken();
                        if (HTTP.CONN_CLOSE.equalsIgnoreCase(token)) {
                            return false;
                        }
                        if (HTTP.CONN_KEEP_ALIVE.equalsIgnoreCase(token)) {
                            keepalive = true;
                        }
                    }
                    if (keepalive) {
                        return true;
                    }
                } catch (ParseException e) {
                    ParseException parseException = e;
                    return false;
                }
            }
            if (!ver.lessEquals(HttpVersion.HTTP_1_0)) {
                z = true;
            } else {
                z = false;
            }
            return z;
        }
    }

    /* access modifiers changed from: protected */
    public TokenIterator createTokenIterator(HeaderIterator hit) {
        TokenIterator tokenIterator;
        new BasicTokenIterator(hit);
        return tokenIterator;
    }
}
