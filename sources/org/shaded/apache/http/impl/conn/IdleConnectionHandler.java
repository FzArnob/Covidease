package org.shaded.apache.http.impl.conn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.shaded.apache.commons.logging.Log;
import org.shaded.apache.commons.logging.LogFactory;
import org.shaded.apache.http.HttpConnection;
import org.shaded.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class IdleConnectionHandler {
    private final Map<HttpConnection, TimeValues> connectionToTimes;
    private final Log log = LogFactory.getLog((Class) getClass());

    public IdleConnectionHandler() {
        Map<HttpConnection, TimeValues> map;
        new HashMap();
        this.connectionToTimes = map;
    }

    public void add(HttpConnection httpConnection, long j, TimeUnit timeUnit) {
        Object obj;
        StringBuilder sb;
        HttpConnection connection = httpConnection;
        long validDuration = j;
        TimeUnit unit = timeUnit;
        long timeAdded = System.currentTimeMillis();
        if (this.log.isDebugEnabled()) {
            Log log2 = this.log;
            new StringBuilder();
            log2.debug(sb.append("Adding connection at: ").append(timeAdded).toString());
        }
        new TimeValues(timeAdded, validDuration, unit);
        TimeValues put = this.connectionToTimes.put(connection, obj);
    }

    public boolean remove(HttpConnection connection) {
        TimeValues times = this.connectionToTimes.remove(connection);
        if (times == null) {
            this.log.warn("Removing a connection that never existed!");
            return true;
        }
        return System.currentTimeMillis() <= times.timeExpires;
    }

    public void removeAll() {
        this.connectionToTimes.clear();
    }

    public void closeIdleConnections(long idleTime) {
        StringBuilder sb;
        StringBuilder sb2;
        long idleTimeout = System.currentTimeMillis() - idleTime;
        if (this.log.isDebugEnabled()) {
            Log log2 = this.log;
            new StringBuilder();
            log2.debug(sb2.append("Checking for connections, idle timeout: ").append(idleTimeout).toString());
        }
        for (HttpConnection conn : this.connectionToTimes.keySet()) {
            long connectionTime = this.connectionToTimes.get(conn).timeAdded;
            if (connectionTime <= idleTimeout) {
                if (this.log.isDebugEnabled()) {
                    Log log3 = this.log;
                    new StringBuilder();
                    log3.debug(sb.append("Closing idle connection, connection time: ").append(connectionTime).toString());
                }
                try {
                    conn.close();
                } catch (IOException e) {
                    this.log.debug("I/O error closing connection", e);
                }
            }
        }
    }

    public void closeExpiredConnections() {
        StringBuilder sb;
        StringBuilder sb2;
        long now = System.currentTimeMillis();
        if (this.log.isDebugEnabled()) {
            Log log2 = this.log;
            new StringBuilder();
            log2.debug(sb2.append("Checking for expired connections, now: ").append(now).toString());
        }
        for (HttpConnection conn : this.connectionToTimes.keySet()) {
            TimeValues times = this.connectionToTimes.get(conn);
            if (times.timeExpires <= now) {
                if (this.log.isDebugEnabled()) {
                    Log log3 = this.log;
                    new StringBuilder();
                    log3.debug(sb.append("Closing connection, expired @: ").append(times.timeExpires).toString());
                }
                try {
                    conn.close();
                } catch (IOException e) {
                    this.log.debug("I/O error closing connection", e);
                }
            }
        }
    }

    private static class TimeValues {
        /* access modifiers changed from: private */
        public final long timeAdded;
        /* access modifiers changed from: private */
        public final long timeExpires;

        TimeValues(long j, long j2, TimeUnit timeUnit) {
            long now = j;
            long validDuration = j2;
            TimeUnit validUnit = timeUnit;
            this.timeAdded = now;
            if (validDuration > 0) {
                this.timeExpires = now + validUnit.toMillis(validDuration);
                return;
            }
            this.timeExpires = Long.MAX_VALUE;
        }
    }
}
