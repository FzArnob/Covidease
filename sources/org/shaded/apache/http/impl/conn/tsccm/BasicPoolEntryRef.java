package org.shaded.apache.http.impl.conn.tsccm;

import java.lang.ref.WeakReference;
import org.shaded.apache.http.annotation.Immutable;
import org.shaded.apache.http.conn.routing.HttpRoute;

@Immutable
public class BasicPoolEntryRef extends WeakReference<BasicPoolEntry> {
    private final HttpRoute route;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BasicPoolEntryRef(org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry r8, java.lang.ref.ReferenceQueue<java.lang.Object> r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, r5)
            r3 = r1
            if (r3 != 0) goto L_0x0018
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            r6 = r3
            r3 = r6
            r4 = r6
            java.lang.String r5 = "Pool entry must not be null."
            r4.<init>(r5)
            throw r3
        L_0x0018:
            r3 = r0
            r4 = r1
            org.shaded.apache.http.conn.routing.HttpRoute r4 = r4.getPlannedRoute()
            r3.route = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntryRef.<init>(org.shaded.apache.http.impl.conn.tsccm.BasicPoolEntry, java.lang.ref.ReferenceQueue):void");
    }

    public final HttpRoute getRoute() {
        return this.route;
    }
}
