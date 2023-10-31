package org.shaded.apache.http.client.protocol;

import java.io.IOException;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestInterceptor;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.conn.ManagedClientConnection;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.protocol.ExecutionContext;
import org.shaded.apache.http.protocol.HTTP;
import org.shaded.apache.http.protocol.HttpContext;

@Immutable
public class RequestClientConnControl implements HttpRequestInterceptor {
    private static final String PROXY_CONN_DIRECTIVE = "Proxy-Connection";

    public RequestClientConnControl() {
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Throwable th;
        Throwable th2;
        HttpRequest request = httpRequest;
        HttpContext context = httpContext;
        if (request == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("HTTP request may not be null");
            throw th3;
        } else if (request.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) {
            request.setHeader(PROXY_CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        } else {
            ManagedClientConnection conn = (ManagedClientConnection) context.getAttribute(ExecutionContext.HTTP_CONNECTION);
            if (conn == null) {
                Throwable th4 = th;
                new IllegalStateException("Client connection not specified in HTTP context");
                throw th4;
            }
            HttpRoute route = conn.getRoute();
            if ((route.getHopCount() == 1 || route.isTunnelled()) && !request.containsHeader(HTTP.CONN_DIRECTIVE)) {
                request.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
            }
            if (route.getHopCount() == 2 && !route.isTunnelled() && !request.containsHeader(PROXY_CONN_DIRECTIVE)) {
                request.addHeader(PROXY_CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
            }
        }
    }
}
