package org.shaded.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;
import org.shaded.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class EofSensorInputStream extends InputStream implements ConnectionReleaseTrigger {
    private final EofSensorWatcher eofWatcher;
    private boolean selfClosed;
    protected InputStream wrappedStream;

    public EofSensorInputStream(InputStream inputStream, EofSensorWatcher eofSensorWatcher) {
        Throwable th;
        InputStream in = inputStream;
        EofSensorWatcher watcher = eofSensorWatcher;
        if (in == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Wrapped stream may not be null.");
            throw th2;
        }
        this.wrappedStream = in;
        this.selfClosed = false;
        this.eofWatcher = watcher;
    }

    /* access modifiers changed from: protected */
    public boolean isReadAllowed() throws IOException {
        Throwable th;
        if (this.selfClosed) {
            Throwable th2 = th;
            new IOException("Attempted read on closed stream.");
            throw th2;
        }
        return this.wrappedStream != null;
    }

    public int read() throws IOException {
        int l = -1;
        if (isReadAllowed()) {
            try {
                l = this.wrappedStream.read();
                checkEOF(l);
            } catch (IOException e) {
                IOException ex = e;
                checkAbort();
                throw ex;
            }
        }
        return l;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        byte[] b = bArr;
        int off = i;
        int len = i2;
        int l = -1;
        if (isReadAllowed()) {
            try {
                l = this.wrappedStream.read(b, off, len);
                checkEOF(l);
            } catch (IOException e) {
                IOException ex = e;
                checkAbort();
                throw ex;
            }
        }
        return l;
    }

    public int read(byte[] bArr) throws IOException {
        byte[] b = bArr;
        int l = -1;
        if (isReadAllowed()) {
            try {
                l = this.wrappedStream.read(b);
                checkEOF(l);
            } catch (IOException e) {
                IOException ex = e;
                checkAbort();
                throw ex;
            }
        }
        return l;
    }

    public int available() throws IOException {
        int a = 0;
        if (isReadAllowed()) {
            try {
                a = this.wrappedStream.available();
            } catch (IOException e) {
                IOException ex = e;
                checkAbort();
                throw ex;
            }
        }
        return a;
    }

    public void close() throws IOException {
        this.selfClosed = true;
        checkClose();
    }

    /* access modifiers changed from: protected */
    public void checkEOF(int i) throws IOException {
        int eof = i;
        if (this.wrappedStream != null && eof < 0) {
            boolean scws = true;
            try {
                if (this.eofWatcher != null) {
                    scws = this.eofWatcher.eofDetected(this.wrappedStream);
                }
                if (scws) {
                    this.wrappedStream.close();
                }
                this.wrappedStream = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                this.wrappedStream = null;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void checkClose() throws IOException {
        if (this.wrappedStream != null) {
            boolean scws = true;
            try {
                if (this.eofWatcher != null) {
                    scws = this.eofWatcher.streamClosed(this.wrappedStream);
                }
                if (scws) {
                    this.wrappedStream.close();
                }
                this.wrappedStream = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                this.wrappedStream = null;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void checkAbort() throws IOException {
        if (this.wrappedStream != null) {
            boolean scws = true;
            try {
                if (this.eofWatcher != null) {
                    scws = this.eofWatcher.streamAbort(this.wrappedStream);
                }
                if (scws) {
                    this.wrappedStream.close();
                }
                this.wrappedStream = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                this.wrappedStream = null;
                throw th2;
            }
        }
    }

    public void releaseConnection() throws IOException {
        close();
    }

    public void abortConnection() throws IOException {
        this.selfClosed = true;
        checkAbort();
    }
}
