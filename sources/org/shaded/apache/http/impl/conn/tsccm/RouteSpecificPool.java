package org.shaded.apache.http.impl.conn.tsccm;

import java.util.LinkedList;
import java.util.Queue;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.conn.routing.HttpRoute;

@NotThreadSafe
public class RouteSpecificPool {
    protected final LinkedList<BasicPoolEntry> freeEntries;
    private final Log log = LogFactory.getLog((Class) getClass());
    protected final int maxEntries;
    protected int numEntries;
    protected final HttpRoute route;
    protected final Queue<WaitingThread> waitingThreads;

    public RouteSpecificPool(HttpRoute route2, int maxEntries2) {
        LinkedList<BasicPoolEntry> linkedList;
        Queue<WaitingThread> queue;
        this.route = route2;
        this.maxEntries = maxEntries2;
        new LinkedList<>();
        this.freeEntries = linkedList;
        new LinkedList();
        this.waitingThreads = queue;
        this.numEntries = 0;
    }

    public final HttpRoute getRoute() {
        return this.route;
    }

    public final int getMaxEntries() {
        return this.maxEntries;
    }

    public boolean isUnused() {
        return this.numEntries < 1 && this.waitingThreads.isEmpty();
    }

    public int getCapacity() {
        return this.maxEntries - this.numEntries;
    }

    public final int getEntryCount() {
        return this.numEntries;
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry allocEntry(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r5 = r0
            java.util.LinkedList<org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry> r5 = r5.freeEntries
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0044
            r5 = r0
            java.util.LinkedList<org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry> r5 = r5.freeEntries
            r6 = r0
            java.util.LinkedList<org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry> r6 = r6.freeEntries
            int r6 = r6.size()
            java.util.ListIterator r5 = r5.listIterator(r6)
            r2 = r5
        L_0x001a:
            r5 = r2
            boolean r5 = r5.hasPrevious()
            if (r5 == 0) goto L_0x0044
            r5 = r2
            java.lang.Object r5 = r5.previous()
            org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry r5 = (org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry) r5
            r3 = r5
            r5 = r3
            java.lang.Object r5 = r5.getState()
            if (r5 == 0) goto L_0x003c
            r5 = r1
            r6 = r3
            java.lang.Object r6 = r6.getState()
            boolean r5 = org.shaded.apache.http.util.LangUtils.equals((java.lang.Object) r5, (java.lang.Object) r6)
            if (r5 == 0) goto L_0x0043
        L_0x003c:
            r5 = r2
            r5.remove()
            r5 = r3
            r0 = r5
        L_0x0042:
            return r0
        L_0x0043:
            goto L_0x001a
        L_0x0044:
            r5 = r0
            int r5 = r5.getCapacity()
            if (r5 != 0) goto L_0x007c
            r5 = r0
            java.util.LinkedList<org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry> r5 = r5.freeEntries
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x007c
            r5 = r0
            java.util.LinkedList<org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry> r5 = r5.freeEntries
            java.lang.Object r5 = r5.remove()
            org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry r5 = (org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry) r5
            r2 = r5
            r5 = r2
            r5.shutdownEntry()
            r5 = r2
            org.shaded.apache.http.conn.OperatedClientConnection r5 = r5.getConnection()
            r3 = r5
            r5 = r3
            r5.close()     // Catch:{ IOException -> 0x006f }
        L_0x006c:
            r5 = r2
            r0 = r5
            goto L_0x0042
        L_0x006f:
            r5 = move-exception
            r4 = r5
            r5 = r0
            org.shaded.apache.commons.logging.Log r5 = r5.log
            java.lang.String r6 = "I/O error closing connection"
            r7 = r4
            r5.debug(r6, r7)
            goto L_0x006c
        L_0x007c:
            r5 = 0
            r0 = r5
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.conn.tsccm.RouteSpecificPool.allocEntry(java.lang.Object):org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry");
    }

    public void freeEntry(BasicPoolEntry basicPoolEntry) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        BasicPoolEntry entry = basicPoolEntry;
        if (this.numEntries < 1) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalStateException(sb2.append("No entry created for this pool. ").append(this.route).toString());
            throw th3;
        } else if (this.numEntries <= this.freeEntries.size()) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("No entry allocated from this pool. ").append(this.route).toString());
            throw th4;
        } else {
            boolean add = this.freeEntries.add(entry);
        }
    }

    public void createdEntry(BasicPoolEntry basicPoolEntry) {
        Throwable th;
        StringBuilder sb;
        BasicPoolEntry entry = basicPoolEntry;
        if (!this.route.equals(entry.getPlannedRoute())) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Entry not planned for this pool.\npool: ").append(this.route).append("\nplan: ").append(entry.getPlannedRoute()).toString());
            throw th2;
        }
        this.numEntries++;
    }

    public boolean deleteEntry(BasicPoolEntry entry) {
        boolean found = this.freeEntries.remove(entry);
        if (found) {
            this.numEntries--;
        }
        return found;
    }

    public void dropEntry() {
        Throwable th;
        if (this.numEntries < 1) {
            Throwable th2 = th;
            new IllegalStateException("There is no entry that could be dropped.");
            throw th2;
        }
        this.numEntries--;
    }

    public void queueThread(WaitingThread waitingThread) {
        Throwable th;
        WaitingThread wt = waitingThread;
        if (wt == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Waiting thread must not be null.");
            throw th2;
        }
        boolean add = this.waitingThreads.add(wt);
    }

    public boolean hasThread() {
        return !this.waitingThreads.isEmpty();
    }

    public WaitingThread nextThread() {
        return this.waitingThreads.peek();
    }

    public void removeThread(WaitingThread waitingThread) {
        WaitingThread wt = waitingThread;
        if (wt != null) {
            boolean remove = this.waitingThreads.remove(wt);
        }
    }
}
