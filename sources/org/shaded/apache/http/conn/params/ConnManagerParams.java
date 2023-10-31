package org.shaded.apache.http.conn.params;

import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.params.HttpParams;

@Immutable
public final class ConnManagerParams implements ConnManagerPNames {
    private static final ConnPerRoute DEFAULT_CONN_PER_ROUTE;
    public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;

    public ConnManagerParams() {
    }

    public static long getTimeout(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return params.getLongParameter(ConnManagerPNames.TIMEOUT, 0);
        }
        Throwable th2 = th;
        new IllegalArgumentException("HTTP parameters may not be null");
        throw th2;
    }

    public static void setTimeout(HttpParams httpParams, long j) {
        Throwable th;
        HttpParams params = httpParams;
        long timeout = j;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th2;
        }
        HttpParams longParameter = params.setLongParameter(ConnManagerPNames.TIMEOUT, timeout);
    }

    static {
        ConnPerRoute connPerRoute;
        new ConnPerRoute() {
            public int getMaxForRoute(HttpRoute httpRoute) {
                HttpRoute httpRoute2 = httpRoute;
                return 2;
            }
        };
        DEFAULT_CONN_PER_ROUTE = connPerRoute;
    }

    public static void setMaxConnectionsPerRoute(HttpParams httpParams, ConnPerRoute connPerRoute) {
        Throwable th;
        HttpParams params = httpParams;
        ConnPerRoute connPerRoute2 = connPerRoute;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters must not be null.");
            throw th2;
        }
        HttpParams parameter = params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, connPerRoute2);
    }

    public static ConnPerRoute getMaxConnectionsPerRoute(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters must not be null.");
            throw th2;
        }
        ConnPerRoute connPerRoute = (ConnPerRoute) params.getParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE);
        if (connPerRoute == null) {
            connPerRoute = DEFAULT_CONN_PER_ROUTE;
        }
        return connPerRoute;
    }

    public static void setMaxTotalConnections(HttpParams httpParams, int i) {
        Throwable th;
        HttpParams params = httpParams;
        int maxTotalConnections = i;
        if (params == null) {
            Throwable th2 = th;
            new IllegalArgumentException("HTTP parameters must not be null.");
            throw th2;
        }
        HttpParams intParameter = params.setIntParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, maxTotalConnections);
    }

    public static int getMaxTotalConnections(HttpParams httpParams) {
        Throwable th;
        HttpParams params = httpParams;
        if (params != null) {
            return params.getIntParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, 20);
        }
        Throwable th2 = th;
        new IllegalArgumentException("HTTP parameters must not be null.");
        throw th2;
    }
}
