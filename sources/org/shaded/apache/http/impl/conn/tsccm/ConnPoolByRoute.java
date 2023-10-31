package org.shaded.apache.http.impl.conn.tsccm;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.conn.ClientConnectionOperator;
import org.shaded.apache.http.conn.ConnectionPoolTimeoutException;
import org.shaded.apache.http.conn.params.ConnManagerParams;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.params.HttpParams;

public class ConnPoolByRoute extends AbstractConnPool {
    protected final Queue<BasicPoolEntry> freeConnections;
    private final Log log = LogFactory.getLog((Class) getClass());
    protected final ClientConnectionOperator operator;
    private final HttpParams params;
    protected final Map<HttpRoute, RouteSpecificPool> routeToPool;
    protected final Queue<WaitingThread> waitingThreads;

    public ConnPoolByRoute(ClientConnectionOperator clientConnectionOperator, HttpParams httpParams) {
        Throwable th;
        ClientConnectionOperator operator2 = clientConnectionOperator;
        HttpParams params2 = httpParams;
        if (operator2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Connection operator may not be null");
            throw th2;
        }
        this.operator = operator2;
        this.params = params2;
        this.freeConnections = createFreeConnQueue();
        this.waitingThreads = createWaitingThreadQueue();
        this.routeToPool = createRouteToPoolMap();
    }

    /* access modifiers changed from: protected */
    public Queue<BasicPoolEntry> createFreeConnQueue() {
        Queue<BasicPoolEntry> queue;
        new LinkedList();
        return queue;
    }

    /* access modifiers changed from: protected */
    public Queue<WaitingThread> createWaitingThreadQueue() {
        Queue<WaitingThread> queue;
        new LinkedList();
        return queue;
    }

    /* access modifiers changed from: protected */
    public Map<HttpRoute, RouteSpecificPool> createRouteToPoolMap() {
        Map<HttpRoute, RouteSpecificPool> map;
        new HashMap();
        return map;
    }

    /* access modifiers changed from: protected */
    public RouteSpecificPool newRouteSpecificPool(HttpRoute httpRoute) {
        RouteSpecificPool routeSpecificPool;
        HttpRoute route = httpRoute;
        new RouteSpecificPool(route, ConnManagerParams.getMaxConnectionsPerRoute(this.params).getMaxForRoute(route));
        return routeSpecificPool;
    }

    /* access modifiers changed from: protected */
    public WaitingThread newWaitingThread(Condition cond, RouteSpecificPool rospl) {
        WaitingThread waitingThread;
        new WaitingThread(cond, rospl);
        return waitingThread;
    }

    /* access modifiers changed from: protected */
    public RouteSpecificPool getRoutePool(HttpRoute httpRoute, boolean z) {
        HttpRoute route = httpRoute;
        boolean create = z;
        this.poolLock.lock();
        try {
            RouteSpecificPool rospl = this.routeToPool.get(route);
            if (rospl == null && create) {
                rospl = newRouteSpecificPool(route);
                RouteSpecificPool put = this.routeToPool.put(route, rospl);
            }
            this.poolLock.unlock();
            return rospl;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.poolLock.unlock();
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    public int getConnectionsInPool(HttpRoute route) {
        this.poolLock.lock();
        try {
            RouteSpecificPool rospl = getRoutePool(route, false);
            int entryCount = rospl != null ? rospl.getEntryCount() : 0;
            this.poolLock.unlock();
            return entryCount;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.poolLock.unlock();
            throw th2;
        }
    }

    public PoolEntryRequest requestPoolEntry(HttpRoute route, Object state) {
        WaitingThreadAborter aborter;
        PoolEntryRequest poolEntryRequest;
        new WaitingThreadAborter();
        final WaitingThreadAborter waitingThreadAborter = aborter;
        final HttpRoute httpRoute = route;
        final Object obj = state;
        new PoolEntryRequest(this) {
            final /* synthetic */ ConnPoolByRoute this$0;

            {
                this.this$0 = r8;
            }

            public void abortRequest() {
                this.this$0.poolLock.lock();
                try {
                    waitingThreadAborter.abort();
                    this.this$0.poolLock.unlock();
                } catch (Throwable th) {
                    Throwable th2 = th;
                    this.this$0.poolLock.unlock();
                    throw th2;
                }
            }

            public BasicPoolEntry getPoolEntry(long timeout, TimeUnit tunit) throws InterruptedException, ConnectionPoolTimeoutException {
                return this.this$0.getEntryBlocking(httpRoute, obj, timeout, tunit, waitingThreadAborter);
            }
        };
        return poolEntryRequest;
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry getEntryBlocking(HttpRoute httpRoute, Object obj, long j, TimeUnit timeUnit, WaitingThreadAborter waitingThreadAborter) throws ConnectionPoolTimeoutException, InterruptedException {
        RouteSpecificPool rospl;
        WaitingThread waitingThread;
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        Date date;
        HttpRoute route = httpRoute;
        Object state = obj;
        long timeout = j;
        TimeUnit tunit = timeUnit;
        WaitingThreadAborter aborter = waitingThreadAborter;
        int maxTotalConnections = ConnManagerParams.getMaxTotalConnections(this.params);
        Date deadline = null;
        if (timeout > 0) {
            new Date(System.currentTimeMillis() + tunit.toMillis(timeout));
            deadline = date;
        }
        BasicPoolEntry entry = null;
        this.poolLock.lock();
        try {
            rospl = getRoutePool(route, true);
            waitingThread = null;
            while (entry == null) {
                if (this.isShutDown) {
                    Throwable th3 = th;
                    new IllegalStateException("Connection pool shut down.");
                    throw th3;
                }
                if (this.log.isDebugEnabled()) {
                    new StringBuilder();
                    this.log.debug(sb3.append("Total connections kept alive: ").append(this.freeConnections.size()).toString());
                    new StringBuilder();
                    this.log.debug(sb4.append("Total issued connections: ").append(this.leasedConnections.size()).toString());
                    new StringBuilder();
                    this.log.debug(sb5.append("Total allocated connection: ").append(this.numConnections).append(" out of ").append(maxTotalConnections).toString());
                }
                entry = getFreeEntry(rospl, state);
                if (entry != null) {
                    break;
                }
                boolean hasCapacity = rospl.getCapacity() > 0;
                if (this.log.isDebugEnabled()) {
                    new StringBuilder();
                    this.log.debug(sb2.append("Available capacity: ").append(rospl.getCapacity()).append(" out of ").append(rospl.getMaxEntries()).append(" [").append(route).append("][").append(state).append("]").toString());
                }
                if (hasCapacity) {
                    if (this.numConnections < maxTotalConnections) {
                        entry = createEntry(rospl, this.operator);
                    }
                }
                if (hasCapacity) {
                    if (!this.freeConnections.isEmpty()) {
                        deleteLeastUsedEntry();
                        entry = createEntry(rospl, this.operator);
                    }
                }
                if (this.log.isDebugEnabled()) {
                    new StringBuilder();
                    this.log.debug(sb.append("Need to wait for connection [").append(route).append("][").append(state).append("]").toString());
                }
                if (waitingThread == null) {
                    waitingThread = newWaitingThread(this.poolLock.newCondition(), rospl);
                    aborter.setWaitingThread(waitingThread);
                }
                rospl.queueThread(waitingThread);
                boolean add = this.waitingThreads.add(waitingThread);
                boolean success = waitingThread.await(deadline);
                rospl.removeThread(waitingThread);
                boolean remove = this.waitingThreads.remove(waitingThread);
                if (!success && deadline != null && deadline.getTime() <= System.currentTimeMillis()) {
                    Throwable th4 = th2;
                    new ConnectionPoolTimeoutException("Timeout waiting for connection");
                    throw th4;
                }
            }
            this.poolLock.unlock();
            return entry;
        } catch (Throwable th5) {
            Throwable th6 = th5;
            this.poolLock.unlock();
            throw th6;
        }
    }

    public void freeEntry(BasicPoolEntry basicPoolEntry, boolean z, long j, TimeUnit timeUnit) {
        StringBuilder sb;
        StringBuilder sb2;
        BasicPoolEntry entry = basicPoolEntry;
        boolean reusable = z;
        long validDuration = j;
        TimeUnit timeUnit2 = timeUnit;
        HttpRoute route = entry.getPlannedRoute();
        if (this.log.isDebugEnabled()) {
            Log log2 = this.log;
            new StringBuilder();
            log2.debug(sb2.append("Releasing connection [").append(route).append("][").append(entry.getState()).append("]").toString());
        }
        this.poolLock.lock();
        try {
            if (this.isShutDown) {
                closeConnection(entry.getConnection());
                this.poolLock.unlock();
                return;
            }
            boolean remove = this.leasedConnections.remove(entry);
            RouteSpecificPool rospl = getRoutePool(route, true);
            if (reusable) {
                if (this.log.isDebugEnabled()) {
                    Log log3 = this.log;
                    new StringBuilder();
                    log3.debug(sb.append("Pooling connection [").append(route).append("][").append(entry.getState()).append("]").append("; keep alive for ").append(validDuration).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(timeUnit2.toString()).toString());
                }
                rospl.freeEntry(entry);
                boolean add = this.freeConnections.add(entry);
                this.idleConnHandler.add(entry.getConnection(), validDuration, timeUnit2);
            } else {
                rospl.dropEntry();
                this.numConnections--;
            }
            notifyWaitingThread(rospl);
            this.poolLock.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.poolLock.unlock();
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry getFreeEntry(RouteSpecificPool routeSpecificPool, Object obj) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        RouteSpecificPool rospl = routeSpecificPool;
        Object state = obj;
        BasicPoolEntry entry = null;
        this.poolLock.lock();
        boolean done = false;
        while (!done) {
            try {
                entry = rospl.allocEntry(state);
                if (entry != null) {
                    if (this.log.isDebugEnabled()) {
                        Log log2 = this.log;
                        new StringBuilder();
                        log2.debug(sb3.append("Getting free connection [").append(rospl.getRoute()).append("][").append(state).append("]").toString());
                    }
                    boolean remove = this.freeConnections.remove(entry);
                    if (!this.idleConnHandler.remove(entry.getConnection())) {
                        if (this.log.isDebugEnabled()) {
                            Log log3 = this.log;
                            new StringBuilder();
                            log3.debug(sb2.append("Closing expired free connection [").append(rospl.getRoute()).append("][").append(state).append("]").toString());
                        }
                        closeConnection(entry.getConnection());
                        rospl.dropEntry();
                        this.numConnections--;
                    } else {
                        boolean add = this.leasedConnections.add(entry);
                        done = true;
                    }
                } else {
                    done = true;
                    if (this.log.isDebugEnabled()) {
                        Log log4 = this.log;
                        new StringBuilder();
                        log4.debug(sb.append("No free connections [").append(rospl.getRoute()).append("][").append(state).append("]").toString());
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                this.poolLock.unlock();
                throw th2;
            }
        }
        this.poolLock.unlock();
        return entry;
    }

    /* access modifiers changed from: protected */
    public BasicPoolEntry createEntry(RouteSpecificPool routeSpecificPool, ClientConnectionOperator clientConnectionOperator) {
        BasicPoolEntry basicPoolEntry;
        StringBuilder sb;
        RouteSpecificPool rospl = routeSpecificPool;
        ClientConnectionOperator op = clientConnectionOperator;
        if (this.log.isDebugEnabled()) {
            Log log2 = this.log;
            new StringBuilder();
            log2.debug(sb.append("Creating new connection [").append(rospl.getRoute()).append("]").toString());
        }
        new BasicPoolEntry(op, rospl.getRoute());
        BasicPoolEntry entry = basicPoolEntry;
        this.poolLock.lock();
        try {
            rospl.createdEntry(entry);
            this.numConnections++;
            boolean add = this.leasedConnections.add(entry);
            this.poolLock.unlock();
            return entry;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.poolLock.unlock();
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public void deleteEntry(BasicPoolEntry basicPoolEntry) {
        StringBuilder sb;
        BasicPoolEntry entry = basicPoolEntry;
        HttpRoute route = entry.getPlannedRoute();
        if (this.log.isDebugEnabled()) {
            Log log2 = this.log;
            new StringBuilder();
            log2.debug(sb.append("Deleting connection [").append(route).append("][").append(entry.getState()).append("]").toString());
        }
        this.poolLock.lock();
        try {
            closeConnection(entry.getConnection());
            RouteSpecificPool rospl = getRoutePool(route, true);
            boolean deleteEntry = rospl.deleteEntry(entry);
            this.numConnections--;
            if (rospl.isUnused()) {
                RouteSpecificPool remove = this.routeToPool.remove(route);
            }
            boolean remove2 = this.idleConnHandler.remove(entry.getConnection());
            this.poolLock.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.poolLock.unlock();
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public void deleteLeastUsedEntry() {
        try {
            this.poolLock.lock();
            BasicPoolEntry entry = this.freeConnections.remove();
            if (entry != null) {
                deleteEntry(entry);
            } else if (this.log.isDebugEnabled()) {
                this.log.debug("No free connection to delete.");
            }
            this.poolLock.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.poolLock.unlock();
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public void handleLostEntry(HttpRoute httpRoute) {
        HttpRoute route = httpRoute;
        this.poolLock.lock();
        try {
            RouteSpecificPool rospl = getRoutePool(route, true);
            rospl.dropEntry();
            if (rospl.isUnused()) {
                RouteSpecificPool remove = this.routeToPool.remove(route);
            }
            this.numConnections--;
            notifyWaitingThread(rospl);
            this.poolLock.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.poolLock.unlock();
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004f A[Catch:{ all -> 0x0093 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyWaitingThread(org.shaded.apache.http.impl.conn.tsccm.RouteSpecificPool r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r4 = 0
            r2 = r4
            r4 = r0
            java.util.concurrent.locks.Lock r4 = r4.poolLock
            r4.lock()
            r4 = r1
            if (r4 == 0) goto L_0x005a
            r4 = r1
            boolean r4 = r4.hasThread()     // Catch:{ all -> 0x0093 }
            if (r4 == 0) goto L_0x005a
            r4 = r0
            org.shaded.apache.commons.logging.Log r4 = r4.log     // Catch:{ all -> 0x0093 }
            boolean r4 = r4.isDebugEnabled()     // Catch:{ all -> 0x0093 }
            if (r4 == 0) goto L_0x0046
            r4 = r0
            org.shaded.apache.commons.logging.Log r4 = r4.log     // Catch:{ all -> 0x0093 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r7 = r5
            r5 = r7
            r6 = r7
            r6.<init>()     // Catch:{ all -> 0x0093 }
            java.lang.String r6 = "Notifying thread waiting on pool ["
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0093 }
            r6 = r1
            org.shaded.apache.http.conn.routing.HttpRoute r6 = r6.getRoute()     // Catch:{ all -> 0x0093 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0093 }
            java.lang.String r6 = "]"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0093 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0093 }
            r4.debug(r5)     // Catch:{ all -> 0x0093 }
        L_0x0046:
            r4 = r1
            org.shaded.apache.http.impl.conn.tsccm.WaitingThread r4 = r4.nextThread()     // Catch:{ all -> 0x0093 }
            r2 = r4
        L_0x004c:
            r4 = r2
            if (r4 == 0) goto L_0x0053
            r4 = r2
            r4.wakeup()     // Catch:{ all -> 0x0093 }
        L_0x0053:
            r4 = r0
            java.util.concurrent.locks.Lock r4 = r4.poolLock
            r4.unlock()
            return
        L_0x005a:
            r4 = r0
            java.util.Queue<org.shaded.apache.http.impl.conn.tsccm.WaitingThread> r4 = r4.waitingThreads     // Catch:{ all -> 0x0093 }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x0093 }
            if (r4 != 0) goto L_0x0080
            r4 = r0
            org.shaded.apache.commons.logging.Log r4 = r4.log     // Catch:{ all -> 0x0093 }
            boolean r4 = r4.isDebugEnabled()     // Catch:{ all -> 0x0093 }
            if (r4 == 0) goto L_0x0075
            r4 = r0
            org.shaded.apache.commons.logging.Log r4 = r4.log     // Catch:{ all -> 0x0093 }
            java.lang.String r5 = "Notifying thread waiting on any pool"
            r4.debug(r5)     // Catch:{ all -> 0x0093 }
        L_0x0075:
            r4 = r0
            java.util.Queue<org.shaded.apache.http.impl.conn.tsccm.WaitingThread> r4 = r4.waitingThreads     // Catch:{ all -> 0x0093 }
            java.lang.Object r4 = r4.remove()     // Catch:{ all -> 0x0093 }
            org.shaded.apache.http.impl.conn.tsccm.WaitingThread r4 = (org.shaded.apache.http.impl.conn.tsccm.WaitingThread) r4     // Catch:{ all -> 0x0093 }
            r2 = r4
            goto L_0x004c
        L_0x0080:
            r4 = r0
            org.shaded.apache.commons.logging.Log r4 = r4.log     // Catch:{ all -> 0x0093 }
            boolean r4 = r4.isDebugEnabled()     // Catch:{ all -> 0x0093 }
            if (r4 == 0) goto L_0x004c
            r4 = r0
            org.shaded.apache.commons.logging.Log r4 = r4.log     // Catch:{ all -> 0x0093 }
            java.lang.String r5 = "Notifying no-one, there are no waiting threads"
            r4.debug(r5)     // Catch:{ all -> 0x0093 }
            goto L_0x004c
        L_0x0093:
            r4 = move-exception
            r3 = r4
            r4 = r0
            java.util.concurrent.locks.Lock r4 = r4.poolLock
            r4.unlock()
            r4 = r3
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.conn.tsccm.ConnPoolByRoute.notifyWaitingThread(org.shaded.apache.http.impl.conn.tsccm.RouteSpecificPool):void");
    }

    public void deleteClosedConnections() {
        this.poolLock.lock();
        try {
            Iterator<BasicPoolEntry> iter = this.freeConnections.iterator();
            while (iter.hasNext()) {
                BasicPoolEntry entry = iter.next();
                if (!entry.getConnection().isOpen()) {
                    iter.remove();
                    deleteEntry(entry);
                }
            }
            this.poolLock.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.poolLock.unlock();
            throw th2;
        }
    }

    public void shutdown() {
        StringBuilder sb;
        this.poolLock.lock();
        try {
            super.shutdown();
            Iterator<BasicPoolEntry> ibpe = this.freeConnections.iterator();
            while (ibpe.hasNext()) {
                BasicPoolEntry entry = ibpe.next();
                ibpe.remove();
                if (this.log.isDebugEnabled()) {
                    Log log2 = this.log;
                    new StringBuilder();
                    log2.debug(sb.append("Closing connection [").append(entry.getPlannedRoute()).append("][").append(entry.getState()).append("]").toString());
                }
                closeConnection(entry.getConnection());
            }
            Iterator<WaitingThread> iwth = this.waitingThreads.iterator();
            while (iwth.hasNext()) {
                WaitingThread waiter = iwth.next();
                iwth.remove();
                waiter.wakeup();
            }
            this.routeToPool.clear();
            this.poolLock.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.poolLock.unlock();
            throw th2;
        }
    }
}
