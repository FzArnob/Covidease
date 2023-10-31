package org.shaded.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.annotation.GuardedBy;
import org.shaded.apache.http.annotation.ThreadSafe;
import org.shaded.apache.http.conn.ClientConnectionManager;
import org.shaded.apache.http.conn.ClientConnectionOperator;
import org.shaded.apache.http.conn.ClientConnectionRequest;
import org.shaded.apache.http.conn.ManagedClientConnection;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.conn.routing.RouteTracker;
import org.shaded.apache.http.conn.scheme.SchemeRegistry;
import org.shaded.apache.http.params.HttpParams;

@ThreadSafe
public class SingleClientConnManager implements ClientConnectionManager {
    public static final String MISUSE_MESSAGE = "Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
    protected final boolean alwaysShutDown;
    protected final ClientConnectionOperator connOperator;
    @GuardedBy("this")
    protected long connectionExpiresTime;
    protected volatile boolean isShutDown;
    @GuardedBy("this")
    protected long lastReleaseTime;
    private final Log log = LogFactory.getLog((Class) getClass());
    @GuardedBy("this")
    protected ConnAdapter managedConn;
    protected final SchemeRegistry schemeRegistry;
    @GuardedBy("this")
    protected PoolEntry uniquePoolEntry;

    public SingleClientConnManager(HttpParams httpParams, SchemeRegistry schemeRegistry2) {
        PoolEntry poolEntry;
        Throwable th;
        HttpParams httpParams2 = httpParams;
        SchemeRegistry schreg = schemeRegistry2;
        if (schreg == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Scheme registry must not be null.");
            throw th2;
        }
        this.schemeRegistry = schreg;
        this.connOperator = createConnectionOperator(schreg);
        new PoolEntry(this);
        this.uniquePoolEntry = poolEntry;
        this.managedConn = null;
        this.lastReleaseTime = -1;
        this.alwaysShutDown = false;
        this.isShutDown = false;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            shutdown();
            super.finalize();
        } catch (Throwable th) {
            Throwable th2 = th;
            super.finalize();
            throw th2;
        }
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    /* access modifiers changed from: protected */
    public ClientConnectionOperator createConnectionOperator(SchemeRegistry schreg) {
        ClientConnectionOperator clientConnectionOperator;
        new DefaultClientConnectionOperator(schreg);
        return clientConnectionOperator;
    }

    /* access modifiers changed from: protected */
    public final void assertStillUp() throws IllegalStateException {
        Throwable th;
        if (this.isShutDown) {
            Throwable th2 = th;
            new IllegalStateException("Manager is shut down.");
            throw th2;
        }
    }

    public final ClientConnectionRequest requestConnection(HttpRoute route, Object state) {
        ClientConnectionRequest clientConnectionRequest;
        final HttpRoute httpRoute = route;
        final Object obj = state;
        new ClientConnectionRequest(this) {
            final /* synthetic */ SingleClientConnManager this$0;

            {
                this.this$0 = r7;
            }

            public void abortRequest() {
            }

            public ManagedClientConnection getConnection(long j, TimeUnit timeUnit) {
                long j2 = j;
                TimeUnit timeUnit2 = timeUnit;
                return this.this$0.getConnection(httpRoute, obj);
            }
        };
        return clientConnectionRequest;
    }

    public synchronized ManagedClientConnection getConnection(HttpRoute httpRoute, Object obj) {
        ConnAdapter connAdapter;
        ConnAdapter connAdapter2;
        PoolEntry poolEntry;
        boolean z;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        HttpRoute route = httpRoute;
        Object obj2 = obj;
        synchronized (this) {
            if (route == null) {
                Throwable th3 = th2;
                new IllegalArgumentException("Route may not be null.");
                throw th3;
            }
            assertStillUp();
            if (this.log.isDebugEnabled()) {
                Log log2 = this.log;
                new StringBuilder();
                log2.debug(sb.append("Get connection for route ").append(route).toString());
            }
            if (this.managedConn != null) {
                Throwable th4 = th;
                new IllegalStateException(MISUSE_MESSAGE);
                throw th4;
            }
            boolean recreate = false;
            boolean shutdown = false;
            closeExpiredConnections();
            if (this.uniquePoolEntry.connection.isOpen()) {
                RouteTracker tracker = this.uniquePoolEntry.tracker;
                if (tracker == null || !tracker.toRoute().equals(route)) {
                    z = true;
                } else {
                    z = false;
                }
                shutdown = z;
            } else {
                recreate = true;
            }
            if (shutdown) {
                recreate = true;
                try {
                    this.uniquePoolEntry.shutdown();
                } catch (IOException e) {
                    this.log.debug("Problem shutting down connection.", e);
                }
            }
            if (recreate) {
                new PoolEntry(this);
                this.uniquePoolEntry = poolEntry;
            }
            new ConnAdapter(this, this.uniquePoolEntry, route);
            this.managedConn = connAdapter;
            connAdapter2 = this.managedConn;
        }
        return connAdapter2;
    }

    public synchronized void releaseConnection(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        ManagedClientConnection conn = managedClientConnection;
        long validDuration = j;
        TimeUnit timeUnit2 = timeUnit;
        synchronized (this) {
            assertStillUp();
            if (!(conn instanceof ConnAdapter)) {
                Throwable th3 = th2;
                new IllegalArgumentException("Connection class mismatch, connection not obtained from this manager.");
                throw th3;
            }
            if (this.log.isDebugEnabled()) {
                Log log2 = this.log;
                new StringBuilder();
                log2.debug(sb.append("Releasing connection ").append(conn).toString());
            }
            ConnAdapter sca = (ConnAdapter) conn;
            if (sca.poolEntry != null) {
                ClientConnectionManager manager = sca.getManager();
                if (manager == null || manager == this) {
                    try {
                        if (sca.isOpen() && (this.alwaysShutDown || !sca.isMarkedReusable())) {
                            if (this.log.isDebugEnabled()) {
                                this.log.debug("Released connection open but not reusable.");
                            }
                            sca.shutdown();
                        }
                        sca.detach();
                        this.managedConn = null;
                        this.lastReleaseTime = System.currentTimeMillis();
                        if (validDuration > 0) {
                            this.connectionExpiresTime = timeUnit2.toMillis(validDuration) + this.lastReleaseTime;
                        } else {
                            this.connectionExpiresTime = Long.MAX_VALUE;
                        }
                    } catch (IOException e) {
                        IOException iox = e;
                        if (this.log.isDebugEnabled()) {
                            this.log.debug("Exception shutting down released connection.", iox);
                        }
                        sca.detach();
                        this.managedConn = null;
                        this.lastReleaseTime = System.currentTimeMillis();
                        if (validDuration > 0) {
                            this.connectionExpiresTime = timeUnit2.toMillis(validDuration) + this.lastReleaseTime;
                        } else {
                            this.connectionExpiresTime = Long.MAX_VALUE;
                        }
                    } catch (Throwable th4) {
                        Throwable th5 = th4;
                        sca.detach();
                        this.managedConn = null;
                        this.lastReleaseTime = System.currentTimeMillis();
                        if (validDuration > 0) {
                            this.connectionExpiresTime = timeUnit2.toMillis(validDuration) + this.lastReleaseTime;
                        } else {
                            this.connectionExpiresTime = Long.MAX_VALUE;
                        }
                        throw th5;
                    }
                } else {
                    Throwable th6 = th;
                    new IllegalArgumentException("Connection not obtained from this manager.");
                    throw th6;
                }
            }
        }
    }

    public synchronized void closeExpiredConnections() {
        synchronized (this) {
            if (System.currentTimeMillis() >= this.connectionExpiresTime) {
                closeIdleConnections(0, TimeUnit.MILLISECONDS);
            }
        }
    }

    public synchronized void closeIdleConnections(long j, TimeUnit timeUnit) {
        Throwable th;
        long idletime = j;
        TimeUnit tunit = timeUnit;
        synchronized (this) {
            assertStillUp();
            if (tunit == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Time unit must not be null.");
                throw th2;
            } else if (this.managedConn == null && this.uniquePoolEntry.connection.isOpen()) {
                if (this.lastReleaseTime <= System.currentTimeMillis() - tunit.toMillis(idletime)) {
                    try {
                        this.uniquePoolEntry.close();
                    } catch (IOException e) {
                        this.log.debug("Problem closing idle connection.", e);
                    }
                }
            }
        }
        return;
    }

    public synchronized void shutdown() {
        synchronized (this) {
            this.isShutDown = true;
            if (this.managedConn != null) {
                this.managedConn.detach();
            }
            try {
                if (this.uniquePoolEntry != null) {
                    this.uniquePoolEntry.shutdown();
                }
                this.uniquePoolEntry = null;
            } catch (IOException e) {
                this.log.debug("Problem while shutting down manager.", e);
                this.uniquePoolEntry = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                this.uniquePoolEntry = null;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public synchronized void revokeConnection() {
        synchronized (this) {
            if (this.managedConn != null) {
                this.managedConn.detach();
                try {
                    this.uniquePoolEntry.shutdown();
                } catch (IOException e) {
                    this.log.debug("Problem while shutting down connection.", e);
                }
            }
        }
        return;
    }

    protected class PoolEntry extends AbstractPoolEntry {
        final /* synthetic */ SingleClientConnManager this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected PoolEntry(org.shaded.apache.http.impl.conn.SingleClientConnManager r6) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r2 = r0
                r3 = r1
                r2.this$0 = r3
                r2 = r0
                r3 = r1
                org.shaded.apache.http.conn.ClientConnectionOperator r3 = r3.connOperator
                r4 = 0
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.conn.SingleClientConnManager.PoolEntry.<init>(org.shaded.apache.http.impl.conn.SingleClientConnManager):void");
        }

        /* access modifiers changed from: protected */
        public void close() throws IOException {
            shutdownEntry();
            if (this.connection.isOpen()) {
                this.connection.close();
            }
        }

        /* access modifiers changed from: protected */
        public void shutdown() throws IOException {
            shutdownEntry();
            if (this.connection.isOpen()) {
                this.connection.shutdown();
            }
        }
    }

    protected class ConnAdapter extends AbstractPooledConnAdapter {
        final /* synthetic */ SingleClientConnManager this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected ConnAdapter(org.shaded.apache.http.impl.conn.SingleClientConnManager r8, org.shaded.apache.http.impl.conn.SingleClientConnManager.PoolEntry r9, org.shaded.apache.http.conn.routing.HttpRoute r10) {
            /*
                r7 = this;
                r0 = r7
                r1 = r8
                r2 = r9
                r3 = r10
                r4 = r0
                r5 = r1
                r4.this$0 = r5
                r4 = r0
                r5 = r1
                r6 = r2
                r4.<init>(r5, r6)
                r4 = r0
                r4.markReusable()
                r4 = r2
                r5 = r3
                r4.route = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.conn.SingleClientConnManager.ConnAdapter.<init>(org.shaded.apache.http.impl.conn.SingleClientConnManager, org.shaded.apache.http.impl.conn.SingleClientConnManager$PoolEntry, org.shaded.apache.http.conn.routing.HttpRoute):void");
        }
    }
}
