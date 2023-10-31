package org.shaded.apache.http.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import org.shaded.apache.http.HttpClientConnection;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.HttpInetConnection;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.protocol.HttpContext;

public interface ManagedClientConnection extends HttpClientConnection, HttpInetConnection, ConnectionReleaseTrigger {
    HttpRoute getRoute();

    SSLSession getSSLSession();

    Object getState();

    boolean isMarkedReusable();

    boolean isSecure();

    void layerProtocol(HttpContext httpContext, HttpParams httpParams) throws IOException;

    void markReusable();

    void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) throws IOException;

    void setIdleDuration(long j, TimeUnit timeUnit);

    void setState(Object obj);

    void tunnelProxy(HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException;

    void tunnelTarget(boolean z, HttpParams httpParams) throws IOException;

    void unmarkReusable();
}
