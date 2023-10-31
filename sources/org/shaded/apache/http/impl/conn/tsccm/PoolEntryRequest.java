package org.shaded.apache.http.impl.conn.tsccm;

import java.util.concurrent.TimeUnit;
import org.shaded.apache.http.conn.ConnectionPoolTimeoutException;

public interface PoolEntryRequest {
    void abortRequest();

    BasicPoolEntry getPoolEntry(long j, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException;
}
