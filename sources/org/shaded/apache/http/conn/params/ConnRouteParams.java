package org.shaded.apache.http.conn.params;

import java.net.InetAddress;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.params.HttpParams;

@Immutable
public class ConnRouteParams implements ConnRoutePNames {
    public static final HttpHost NO_HOST;
    public static final HttpRoute NO_ROUTE;

    static {
        HttpHost httpHost;
        HttpRoute httpRoute;
        new HttpHost("127.0.0.255", 0, "no-host");
        NO_HOST = httpHost;
        new HttpRoute(NO_HOST);
        NO_ROUTE = httpRoute;
    }

    private ConnRouteParams() {
    }

    public static HttpHost getDefaultProxy(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Parameters must not be null.");
            throw th2;
        }
        HttpHost proxy = (HttpHost) params.getParameter(ConnRoutePNames.DEFAULT_PROXY);
        if (proxy != null && NO_HOST.equals(proxy)) {
            proxy = null;
        }
        return proxy;
    }

    public static void setDefaultProxy(HttpParams httpParams, HttpHost httpHost) {
        Throwable th;
        HttpParams params = httpParams;
        HttpHost proxy = httpHost;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Parameters must not be null.");
            throw th2;
        }
        HttpParams parameter = params.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
    }

    public static HttpRoute getForcedRoute(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Parameters must not be null.");
            throw th2;
        }
        HttpRoute route = (HttpRoute) params.getParameter(ConnRoutePNames.FORCED_ROUTE);
        if (route != null && NO_ROUTE.equals(route)) {
            route = null;
        }
        return route;
    }

    public static void setForcedRoute(HttpParams httpParams, HttpRoute httpRoute) {
        Throwable th;
        HttpParams params = httpParams;
        HttpRoute route = httpRoute;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Parameters must not be null.");
            throw th2;
        }
        HttpParams parameter = params.setParameter(ConnRoutePNames.FORCED_ROUTE, route);
    }

    public static InetAddress getLocalAddress(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return (InetAddress) params.getParameter(ConnRoutePNames.LOCAL_ADDRESS);
        }
        Throwable th2 = th;
        new IllegalArgumentException("Parameters must not be null.");
        throw th2;
    }

    public static void setLocalAddress(HttpParams httpParams, InetAddress inetAddress) {
        Throwable th;
        HttpParams params = httpParams;
        InetAddress local = inetAddress;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Parameters must not be null.");
            throw th2;
        }
        HttpParams parameter = params.setParameter(ConnRoutePNames.LOCAL_ADDRESS, local);
    }
}
