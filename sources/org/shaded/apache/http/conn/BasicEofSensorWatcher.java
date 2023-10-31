package org.shaded.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;
import org.shaded.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class BasicEofSensorWatcher implements EofSensorWatcher {
    protected final boolean attemptReuse;
    protected final ManagedClientConnection managedConn;

    public BasicEofSensorWatcher(ManagedClientConnection managedClientConnection, boolean z) {
        Throwable th;
        ManagedClientConnection conn = managedClientConnection;
        boolean reuse = z;
        if (conn == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Connection may not be null.");
            throw th2;
        }
        this.managedConn = conn;
        this.attemptReuse = reuse;
    }

    public boolean eofDetected(InputStream inputStream) throws IOException {
        InputStream wrapped = inputStream;
        try {
            if (this.attemptReuse) {
                wrapped.close();
                this.managedConn.markReusable();
            }
            this.managedConn.releaseConnection();
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.managedConn.releaseConnection();
            throw th2;
        }
    }

    public boolean streamClosed(InputStream inputStream) throws IOException {
        InputStream wrapped = inputStream;
        try {
            if (this.attemptReuse) {
                wrapped.close();
                this.managedConn.markReusable();
            }
            this.managedConn.releaseConnection();
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.managedConn.releaseConnection();
            throw th2;
        }
    }

    public boolean streamAbort(InputStream inputStream) throws IOException {
        InputStream inputStream2 = inputStream;
        this.managedConn.abortConnection();
        return false;
    }
}
