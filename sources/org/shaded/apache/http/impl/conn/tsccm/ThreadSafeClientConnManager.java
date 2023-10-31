package org.shaded.apache.http.impl.conn.tsccm;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.conn.ClientConnectionManager;
import org.shaded.apache.http.conn.ClientConnectionOperator;
import org.shaded.apache.http.conn.ClientConnectionRequest;
import org.shaded.apache.http.conn.ConnectionPoolTimeoutException;
import org.shaded.apache.http.conn.ManagedClientConnection;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.conn.scheme.SchemeRegistry;
import org.shaded.apache.http.impl.conn.DefaultClientConnectionOperator;
import org.shaded.apache.http.params.HttpParams;

public class ThreadSafeClientConnManager implements ClientConnectionManager {
    protected final ClientConnectionOperator connOperator;
    protected final AbstractConnPool connectionPool;
    /* access modifiers changed from: private */
    public final Log log = LogFactory.getLog((Class) getClass());
    protected final SchemeRegistry schemeRegistry;

    public ThreadSafeClientConnManager(HttpParams httpParams, SchemeRegistry schemeRegistry2) {
        Throwable th;
        Throwable th2;
        HttpParams params = httpParams;
        SchemeRegistry schreg = schemeRegistry2;
        if (params == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("HTTP parameters may not be null");
            throw th3;
        } else if (schreg == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Scheme registry may not be null");
            throw th4;
        } else {
            this.schemeRegistry = schreg;
            this.connOperator = createConnectionOperator(schreg);
            this.connectionPool = createConnectionPool(params);
        }
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

    /* access modifiers changed from: protected */
    public AbstractConnPool createConnectionPool(HttpParams params) {
        AbstractConnPool abstractConnPool;
        new ConnPoolByRoute(this.connOperator, params);
        return abstractConnPool;
    }

    /* access modifiers changed from: protected */
    public ClientConnectionOperator createConnectionOperator(SchemeRegistry schreg) {
        ClientConnectionOperator clientConnectionOperator;
        new DefaultClientConnectionOperator(schreg);
        return clientConnectionOperator;
    }

    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    public ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object state) {
        ClientConnectionRequest clientConnectionRequest;
        HttpRoute route = httpRoute;
        final PoolEntryRequest requestPoolEntry = this.connectionPool.requestPoolEntry(route, state);
        final HttpRoute httpRoute2 = route;
        new ClientConnectionRequest(this) {
            final /* synthetic */ ThreadSafeClientConnManager this$0;

            {
                this.this$0 = r7;
            }

            public void abortRequest() {
                requestPoolEntry.abortRequest();
            }

            public ManagedClientConnection getConnection(long j, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException {
                ManagedClientConnection managedClientConnection;
                StringBuilder sb;
                Throwable th;
                long timeout = j;
                TimeUnit tunit = timeUnit;
                if (httpRoute2 == null) {
                    Throwable th2 = th;
                    new IllegalArgumentException("Route may not be null.");
                    throw th2;
                }
                if (this.this$0.log.isDebugEnabled()) {
                    Log access$000 = this.this$0.log;
                    new StringBuilder();
                    access$000.debug(sb.append("ThreadSafeClientConnManager.getConnection: ").append(httpRoute2).append(", timeout = ").append(timeout).toString());
                }
                new BasicPooledConnAdapter(this.this$0, requestPoolEntry.getPoolEntry(timeout, tunit));
                return managedClientConnection;
            }
        };
        return clientConnectionRequest;
    }

    public void releaseConnection(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit) {
        BasicPoolEntry entry;
        Throwable th;
        Throwable th2;
        ManagedClientConnection conn = managedClientConnection;
        long validDuration = j;
        TimeUnit timeUnit2 = timeUnit;
        if (!(conn instanceof BasicPooledConnAdapter)) {
            Throwable th3 = th2;
            new IllegalArgumentException("Connection class mismatch, connection not obtained from this manager.");
            throw th3;
        }
        BasicPooledConnAdapter hca = (BasicPooledConnAdapter) conn;
        if (hca.getPoolEntry() == null || hca.getManager() == this) {
            BasicPooledConnAdapter basicPooledConnAdapter = hca;
            BasicPooledConnAdapter basicPooledConnAdapter2 = basicPooledConnAdapter;
            synchronized (basicPooledConnAdapter) {
                try {
                    entry = (BasicPoolEntry) hca.getPoolEntry();
                    if (entry == null) {
                        return;
                    }
                    if (hca.isOpen() && !hca.isMarkedReusable()) {
                        hca.shutdown();
                    }
                    boolean reusable = hca.isMarkedReusable();
                    if (this.log.isDebugEnabled()) {
                        if (reusable) {
                            this.log.debug("Released connection is reusable.");
                        } else {
                            this.log.debug("Released connection is not reusable.");
                        }
                    }
                    hca.detach();
                    this.connectionPool.freeEntry(entry, reusable, validDuration, timeUnit2);
                } catch (IOException e) {
                    IOException iox = e;
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Exception shutting down released connection.", iox);
                    }
                    boolean reusable2 = hca.isMarkedReusable();
                    if (this.log.isDebugEnabled()) {
                        if (reusable2) {
                            this.log.debug("Released connection is reusable.");
                        } else {
                            this.log.debug("Released connection is not reusable.");
                        }
                    }
                    hca.detach();
                    this.connectionPool.freeEntry(entry, reusable2, validDuration, timeUnit2);
                } catch (Throwable th4) {
                    Throwable th5 = th4;
                    BasicPooledConnAdapter basicPooledConnAdapter3 = basicPooledConnAdapter2;
                    throw th5;
                }
            }
        } else {
            Throwable th6 = th;
            new IllegalArgumentException("Connection not obtained from this manager.");
            throw th6;
        }
    }

    public void shutdown() {
        this.log.debug("Shutting down");
        this.connectionPool.shutdown();
    }

    public int getConnectionsInPool(HttpRoute route) {
        return ((ConnPoolByRoute) this.connectionPool).getConnectionsInPool(route);
    }

    public int getConnectionsInPool() {
        this.connectionPool.poolLock.lock();
        int count = this.connectionPool.numConnections;
        this.connectionPool.poolLock.unlock();
        return count;
    }

    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        StringBuilder sb;
        long idleTimeout = j;
        TimeUnit tunit = timeUnit;
        if (this.log.isDebugEnabled()) {
            Log log2 = this.log;
            new StringBuilder();
            log2.debug(sb.append("Closing connections idle for ").append(idleTimeout).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(tunit).toString());
        }
        this.connectionPool.closeIdleConnections(idleTimeout, tunit);
        this.connectionPool.deleteClosedConnections();
    }

    public void closeExpiredConnections() {
        this.log.debug("Closing expired connections");
        this.connectionPool.closeExpiredConnections();
        this.connectionPool.deleteClosedConnections();
    }
}
