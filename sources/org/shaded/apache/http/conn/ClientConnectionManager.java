package org.shaded.apache.http.conn;

import java.util.concurrent.TimeUnit;
import org.shaded.apache.http.conn.routing.HttpRoute;
import org.shaded.apache.http.conn.scheme.SchemeRegistry;

public interface ClientConnectionManager {
    void closeExpiredConnections();

    void closeIdleConnections(long j, TimeUnit timeUnit);

    SchemeRegistry getSchemeRegistry();

    void releaseConnection(ManagedClientConnection managedClientConnection, long j, TimeUnit timeUnit);

    ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object obj);

    void shutdown();
}
