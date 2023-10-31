package org.shaded.apache.http.impl.conn;

import java.io.IOException;
import java.net.Socket;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.conn.ClientConnectionOperator;
import org.shaded.apache.http.conn.OperatedClientConnection;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.conn.routing.RouteTracker;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.protocol.HttpContext;

public abstract class AbstractPoolEntry {
    protected final ClientConnectionOperator connOperator;
    protected final OperatedClientConnection connection;
    protected volatile HttpRoute route;
    protected volatile Object state;
    protected volatile RouteTracker tracker;

    protected AbstractPoolEntry(ClientConnectionOperator clientConnectionOperator, HttpRoute httpRoute) {
        Throwable th;
        ClientConnectionOperator connOperator2 = clientConnectionOperator;
        HttpRoute route2 = httpRoute;
        if (connOperator2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Connection operator may not be null");
            throw th2;
        }
        this.connOperator = connOperator2;
        this.connection = connOperator2.createConnection();
        this.route = route2;
        this.tracker = null;
    }

    public Object getState() {
        return this.state;
    }

    public void setState(Object state2) {
        Object obj = state2;
        this.state = obj;
    }

    public void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) throws IOException {
        RouteTracker routeTracker;
        HttpHost targetHost;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        HttpRoute route2 = httpRoute;
        HttpContext context = httpContext;
        HttpParams params = httpParams;
        if (route2 == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("Route must not be null.");
            throw th5;
        } else if (params == null) {
            Throwable th6 = th3;
            new IllegalArgumentException("Parameters must not be null.");
            throw th6;
        } else if (this.tracker == null || !this.tracker.isConnected()) {
            new RouteTracker(route2);
            this.tracker = routeTracker;
            HttpHost proxy = route2.getProxyHost();
            ClientConnectionOperator clientConnectionOperator = this.connOperator;
            OperatedClientConnection operatedClientConnection = this.connection;
            if (proxy != null) {
                targetHost = proxy;
            } else {
                targetHost = route2.getTargetHost();
            }
            clientConnectionOperator.openConnection(operatedClientConnection, targetHost, route2.getLocalAddress(), context, params);
            RouteTracker localTracker = this.tracker;
            if (localTracker == null) {
                Throwable th7 = th;
                new IOException("Request aborted");
                throw th7;
            } else if (proxy == null) {
                localTracker.connectTarget(this.connection.isSecure());
            } else {
                localTracker.connectProxy(proxy, this.connection.isSecure());
            }
        } else {
            Throwable th8 = th2;
            new IllegalStateException("Connection already open.");
            throw th8;
        }
    }

    public void tunnelTarget(boolean z, HttpParams httpParams) throws IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        boolean secure = z;
        HttpParams params = httpParams;
        if (params == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Parameters must not be null.");
            throw th4;
        } else if (this.tracker == null || !this.tracker.isConnected()) {
            Throwable th5 = th;
            new IllegalStateException("Connection not open.");
            throw th5;
        } else if (this.tracker.isTunnelled()) {
            Throwable th6 = th2;
            new IllegalStateException("Connection is already tunnelled.");
            throw th6;
        } else {
            this.connection.update((Socket) null, this.tracker.getTargetHost(), secure, params);
            this.tracker.tunnelTarget(secure);
        }
    }

    public void tunnelProxy(HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        HttpHost next = httpHost;
        boolean secure = z;
        HttpParams params = httpParams;
        if (next == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Next proxy must not be null.");
            throw th4;
        } else if (params == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("Parameters must not be null.");
            throw th5;
        } else if (this.tracker == null || !this.tracker.isConnected()) {
            Throwable th6 = th;
            new IllegalStateException("Connection not open.");
            throw th6;
        } else {
            this.connection.update((Socket) null, next, secure, params);
            this.tracker.tunnelProxy(next, secure);
        }
    }

    public void layerProtocol(HttpContext httpContext, HttpParams httpParams) throws IOException {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        HttpContext context = httpContext;
        HttpParams params = httpParams;
        if (params == null) {
            Throwable th5 = th4;
            new IllegalArgumentException("Parameters must not be null.");
            throw th5;
        } else if (this.tracker == null || !this.tracker.isConnected()) {
            Throwable th6 = th;
            new IllegalStateException("Connection not open.");
            throw th6;
        } else if (!this.tracker.isTunnelled()) {
            Throwable th7 = th3;
            new IllegalStateException("Protocol layering without a tunnel not supported.");
            throw th7;
        } else if (this.tracker.isLayered()) {
            Throwable th8 = th2;
            new IllegalStateException("Multiple protocol layering not supported.");
            throw th8;
        } else {
            this.connOperator.updateSecureConnection(this.connection, this.tracker.getTargetHost(), context, params);
            this.tracker.layerProtocol(this.connection.isSecure());
        }
    }

    /* access modifiers changed from: protected */
    public void shutdownEntry() {
        this.tracker = null;
        this.state = null;
    }
}
