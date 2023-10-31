package org.shaded.apache.http.impl.conn;

import java.net.InetAddress;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.annotation.ThreadSafe;
import org.shaded.apache.http.conn.params.ConnRouteParams;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.conn.routing.HttpRoutePlanner;
import org.shaded.apache.http.conn.scheme.SchemeRegistry;
import org.shaded.apache.http.protocol.HttpContext;

@ThreadSafe
public class DefaultHttpRoutePlanner implements HttpRoutePlanner {
    protected final SchemeRegistry schemeRegistry;

    public DefaultHttpRoutePlanner(SchemeRegistry schemeRegistry2) {
        Throwable th;
        SchemeRegistry schreg = schemeRegistry2;
        if (schreg == null) {
            Throwable th2 = th;
            new IllegalArgumentException("SchemeRegistry must not be null.");
            throw th2;
        }
        this.schemeRegistry = schreg;
    }

    public HttpRoute determineRoute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
        HttpRoute httpRoute;
        HttpRoute route;
        HttpRoute httpRoute2;
        Throwable th;
        Throwable th2;
        HttpHost target = httpHost;
        HttpRequest request = httpRequest;
        HttpContext httpContext2 = httpContext;
        if (request == null) {
            Throwable th3 = th2;
            new IllegalStateException("Request must not be null.");
            throw th3;
        }
        HttpRoute route2 = ConnRouteParams.getForcedRoute(request.getParams());
        if (route2 != null) {
            return route2;
        }
        if (target == null) {
            Throwable th4 = th;
            new IllegalStateException("Target host must not be null.");
            throw th4;
        }
        InetAddress local = ConnRouteParams.getLocalAddress(request.getParams());
        HttpHost proxy = ConnRouteParams.getDefaultProxy(request.getParams());
        boolean secure = this.schemeRegistry.getScheme(target.getSchemeName()).isLayered();
        if (proxy == null) {
            new HttpRoute(target, local, secure);
            route = httpRoute2;
        } else {
            new HttpRoute(target, local, proxy, secure);
            route = httpRoute;
        }
        return route;
    }
}
