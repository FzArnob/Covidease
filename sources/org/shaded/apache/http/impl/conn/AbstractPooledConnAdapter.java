package org.shaded.apache.http.impl.conn;

import java.io.IOException;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.conn.OperatedClientConnection;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.params.HttpParams;
import org.shaded.apache.http.protocol.HttpContext;

public abstract class AbstractPooledConnAdapter extends AbstractClientConnAdapter {
    protected volatile AbstractPoolEntry poolEntry;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected AbstractPooledConnAdapter(org.shaded.apache.http.conn.ClientConnectionManager r7, org.shaded.apache.http.impl.conn.AbstractPoolEntry r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            org.shaded.apache.http.conn.OperatedClientConnection r5 = r5.connection
            r3.<init>(r4, r5)
            r3 = r0
            r4 = r2
            r3.poolEntry = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.conn.AbstractPooledConnAdapter.<init>(org.shaded.apache.http.conn.ClientConnectionManager, org.shaded.apache.http.impl.conn.AbstractPoolEntry):void");
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final void assertAttached() {
        Throwable th;
        if (this.poolEntry == null) {
            Throwable th2 = th;
            new IllegalStateException("Adapter is detached.");
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void detach() {
        synchronized (this) {
            super.detach();
            this.poolEntry = null;
        }
    }

    public HttpRoute getRoute() {
        Throwable th;
        AbstractPoolEntry entry = this.poolEntry;
        if (entry == null) {
            Throwable th2 = th;
            new IllegalStateException("Adapter is detached.");
            throw th2;
        }
        return entry.tracker == null ? null : entry.tracker.toRoute();
    }

    public void open(HttpRoute httpRoute, HttpContext httpContext, HttpParams httpParams) throws IOException {
        Throwable th;
        HttpRoute route = httpRoute;
        HttpContext context = httpContext;
        HttpParams params = httpParams;
        assertNotAborted();
        AbstractPoolEntry entry = this.poolEntry;
        if (entry == null) {
            Throwable th2 = th;
            new IllegalStateException("Adapter is detached.");
            throw th2;
        }
        entry.open(route, context, params);
    }

    public void tunnelTarget(boolean z, HttpParams httpParams) throws IOException {
        Throwable th;
        boolean secure = z;
        HttpParams params = httpParams;
        assertNotAborted();
        AbstractPoolEntry entry = this.poolEntry;
        if (entry == null) {
            Throwable th2 = th;
            new IllegalStateException("Adapter is detached.");
            throw th2;
        }
        entry.tunnelTarget(secure, params);
    }

    public void tunnelProxy(HttpHost httpHost, boolean z, HttpParams httpParams) throws IOException {
        Throwable th;
        HttpHost next = httpHost;
        boolean secure = z;
        HttpParams params = httpParams;
        assertNotAborted();
        AbstractPoolEntry entry = this.poolEntry;
        if (entry == null) {
            Throwable th2 = th;
            new IllegalStateException("Adapter is detached.");
            throw th2;
        }
        entry.tunnelProxy(next, secure, params);
    }

    public void layerProtocol(HttpContext httpContext, HttpParams httpParams) throws IOException {
        Throwable th;
        HttpContext context = httpContext;
        HttpParams params = httpParams;
        assertNotAborted();
        AbstractPoolEntry entry = this.poolEntry;
        if (entry == null) {
            Throwable th2 = th;
            new IllegalStateException("Adapter is detached.");
            throw th2;
        }
        entry.layerProtocol(context, params);
    }

    public void close() throws IOException {
        AbstractPoolEntry entry = this.poolEntry;
        if (entry != null) {
            entry.shutdownEntry();
        }
        OperatedClientConnection conn = getWrappedConnection();
        if (conn != null) {
            conn.close();
        }
    }

    public void shutdown() throws IOException {
        AbstractPoolEntry entry = this.poolEntry;
        if (entry != null) {
            entry.shutdownEntry();
        }
        OperatedClientConnection conn = getWrappedConnection();
        if (conn != null) {
            conn.shutdown();
        }
    }

    public Object getState() {
        Throwable th;
        AbstractPoolEntry entry = this.poolEntry;
        if (entry != null) {
            return entry.getState();
        }
        Throwable th2 = th;
        new IllegalStateException("Adapter is detached.");
        throw th2;
    }

    public void setState(Object obj) {
        Throwable th;
        Object state = obj;
        AbstractPoolEntry entry = this.poolEntry;
        if (entry == null) {
            Throwable th2 = th;
            new IllegalStateException("Adapter is detached.");
            throw th2;
        }
        entry.setState(state);
    }
}
