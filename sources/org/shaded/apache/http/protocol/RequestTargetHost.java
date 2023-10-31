package org.shaded.apache.http.protocol;

import java.io.IOException;
import java.net.InetAddress;
import org.shaded.apache.http.HttpConnection;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.HttpInetConnection;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestInterceptor;
import org.shaded.apache.http.HttpVersion;
import org.shaded.apache.http.ProtocolException;
import org.shaded.apache.http.ProtocolVersion;

public class RequestTargetHost implements HttpRequestInterceptor {
    public RequestTargetHost() {
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        HttpHost httpHost;
        Throwable th2;
        Throwable th3;
        HttpRequest request = httpRequest;
        HttpContext context = httpContext;
        if (request == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("HTTP request may not be null");
            throw th4;
        } else if (context == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("HTTP context may not be null");
            throw th5;
        } else {
            ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
            if ((!request.getRequestLine().getMethod().equalsIgnoreCase("CONNECT") || !ver.lessEquals(HttpVersion.HTTP_1_0)) && !request.containsHeader(HTTP.TARGET_HOST)) {
                HttpHost targethost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
                if (targethost == null) {
                    HttpConnection conn = (HttpConnection) context.getAttribute(ExecutionContext.HTTP_CONNECTION);
                    if (conn instanceof HttpInetConnection) {
                        InetAddress address = ((HttpInetConnection) conn).getRemoteAddress();
                        int port = ((HttpInetConnection) conn).getRemotePort();
                        if (address != null) {
                            new HttpHost(address.getHostName(), port);
                            targethost = httpHost;
                        }
                    }
                    if (targethost == null) {
                        if (!ver.lessEquals(HttpVersion.HTTP_1_0)) {
                            Throwable th6 = th;
                            new ProtocolException("Target host missing");
                            throw th6;
                        }
                        return;
                    }
                }
                request.addHeader(HTTP.TARGET_HOST, targethost.toHostString());
            }
        }
    }
}
