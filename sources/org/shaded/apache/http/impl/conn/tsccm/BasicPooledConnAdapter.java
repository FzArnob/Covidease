package org.shaded.apache.http.impl.conn.tsccm;

import org.shaded.apache.http.conn.ClientConnectionManager;
import org.shaded.apache.http.impl.conn.AbstractPoolEntry;
import org.shaded.apache.http.impl.conn.AbstractPooledConnAdapter;

public class BasicPooledConnAdapter extends AbstractPooledConnAdapter {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BasicPooledConnAdapter(ThreadSafeClientConnManager tsccm, AbstractPoolEntry entry) {
        super(tsccm, entry);
        markReusable();
    }

    /* access modifiers changed from: protected */
    public ClientConnectionManager getManager() {
        return super.getManager();
    }

    /* access modifiers changed from: protected */
    public AbstractPoolEntry getPoolEntry() {
        return this.poolEntry;
    }

    /* access modifiers changed from: protected */
    public void detach() {
        super.detach();
    }
}
