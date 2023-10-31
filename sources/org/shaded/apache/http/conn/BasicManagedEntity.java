package org.shaded.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.annotation.NotThreadSafe;
import org.shaded.apache.http.entity.HttpEntityWrapper;

@NotThreadSafe
public class BasicManagedEntity extends HttpEntityWrapper implements ConnectionReleaseTrigger, EofSensorWatcher {
    protected final boolean attemptReuse;
    protected ManagedClientConnection managedConn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BasicManagedEntity(HttpEntity entity, ManagedClientConnection managedClientConnection, boolean z) {
        super(entity);
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

    public boolean isRepeatable() {
        return false;
    }

    public InputStream getContent() throws IOException {
        InputStream inputStream;
        new EofSensorInputStream(this.wrappedEntity.getContent(), this);
        return inputStream;
    }

    public void consumeContent() throws IOException {
        if (this.managedConn != null) {
            try {
                if (this.attemptReuse) {
                    this.wrappedEntity.consumeContent();
                    this.managedConn.markReusable();
                }
                releaseManagedConnection();
            } catch (Throwable th) {
                Throwable th2 = th;
                releaseManagedConnection();
                throw th2;
            }
        }
    }

    public void writeTo(OutputStream outstream) throws IOException {
        super.writeTo(outstream);
        consumeContent();
    }

    public void releaseConnection() throws IOException {
        consumeContent();
    }

    public void abortConnection() throws IOException {
        if (this.managedConn != null) {
            try {
                this.managedConn.abortConnection();
                this.managedConn = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                this.managedConn = null;
                throw th2;
            }
        }
    }

    public boolean eofDetected(InputStream inputStream) throws IOException {
        InputStream wrapped = inputStream;
        try {
            if (this.attemptReuse && this.managedConn != null) {
                wrapped.close();
                this.managedConn.markReusable();
            }
            releaseManagedConnection();
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            releaseManagedConnection();
            throw th2;
        }
    }

    public boolean streamClosed(InputStream inputStream) throws IOException {
        InputStream wrapped = inputStream;
        try {
            if (this.attemptReuse && this.managedConn != null) {
                wrapped.close();
                this.managedConn.markReusable();
            }
            releaseManagedConnection();
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            releaseManagedConnection();
            throw th2;
        }
    }

    public boolean streamAbort(InputStream inputStream) throws IOException {
        InputStream inputStream2 = inputStream;
        if (this.managedConn != null) {
            this.managedConn.abortConnection();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void releaseManagedConnection() throws IOException {
        if (this.managedConn != null) {
            try {
                this.managedConn.releaseConnection();
                this.managedConn = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                this.managedConn = null;
                throw th2;
            }
        }
    }
}
