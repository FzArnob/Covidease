package org.shaded.apache.http.impl.conn.tsccm;

import org.shaded.apache.http.conn.OperatedClientConnection;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.impl.conn.AbstractPoolEntry;

public class BasicPoolEntry extends AbstractPoolEntry {
    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BasicPoolEntry(org.shaded.apache.http.conn.ClientConnectionOperator r9, org.shaded.apache.http.conn.routing.HttpRoute r10, java.lang.ref.ReferenceQueue<java.lang.Object> r11) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r0
            r5 = r1
            r6 = r2
            r4.<init>(r5, r6)
            r4 = r2
            if (r4 != 0) goto L_0x0019
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            r7 = r4
            r4 = r7
            r5 = r7
            java.lang.String r6 = "HTTP route may not be null"
            r5.<init>(r6)
            throw r4
        L_0x0019:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry.<init>(org.shaded.apache.http.conn.ClientConnectionOperator, org.shaded.apache.http.conn.routing.HttpRoute, java.lang.ref.ReferenceQueue):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BasicPoolEntry(org.shaded.apache.http.conn.ClientConnectionOperator r8, org.shaded.apache.http.conn.routing.HttpRoute r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, r5)
            r3 = r2
            if (r3 != 0) goto L_0x0018
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r6 = r3
            r3 = r6
            r4 = r6
            java.lang.String r5 = "HTTP route may not be null"
            r4.<init>(r5)
            throw r3
        L_0x0018:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry.<init>(org.shaded.apache.http.conn.ClientConnectionOperator, org.shaded.apache.http.conn.routing.HttpRoute):void");
    }

    /* access modifiers changed from: protected */
    public final OperatedClientConnection getConnection() {
        return this.connection;
    }

    /* access modifiers changed from: protected */
    public final HttpRoute getPlannedRoute() {
        return this.route;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final BasicPoolEntryRef getWeakRef() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void shutdownEntry() {
        super.shutdownEntry();
    }
}
