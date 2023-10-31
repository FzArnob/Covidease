package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import java.io.InputStream;
import org.shaded.apache.http.p007io.SessionInputBuffer;

/* renamed from: org.shaded.apache.http.impl.io.IdentityInputStream */
public class IdentityInputStream extends InputStream {
    private boolean closed = false;

    /* renamed from: in */
    private final SessionInputBuffer f294in;

    public IdentityInputStream(SessionInputBuffer sessionInputBuffer) {
        Throwable th;
        SessionInputBuffer in = sessionInputBuffer;
        if (in == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Session input buffer may not be null");
            throw th2;
        }
        this.f294in = in;
    }

    public int available() throws IOException {
        if (this.closed || !this.f294in.isDataAvailable(10)) {
            return 0;
        }
        return 1;
    }

    public void close() throws IOException {
        this.closed = true;
    }

    public int read() throws IOException {
        if (this.closed) {
            return -1;
        }
        return this.f294in.read();
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        byte[] b = bArr;
        int off = i;
        int len = i2;
        if (this.closed) {
            return -1;
        }
        return this.f294in.read(b, off, len);
    }
}
