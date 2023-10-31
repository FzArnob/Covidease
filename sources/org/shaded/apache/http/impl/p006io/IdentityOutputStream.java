package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import java.io.OutputStream;
import org.shaded.apache.http.p007io.SessionOutputBuffer;

/* renamed from: org.shaded.apache.http.impl.io.IdentityOutputStream */
public class IdentityOutputStream extends OutputStream {
    private boolean closed = false;
    private final SessionOutputBuffer out;

    public IdentityOutputStream(SessionOutputBuffer sessionOutputBuffer) {
        Throwable th;
        SessionOutputBuffer out2 = sessionOutputBuffer;
        if (out2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Session output buffer may not be null");
            throw th2;
        }
        this.out = out2;
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.out.flush();
        }
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        Throwable th;
        byte[] b = bArr;
        int off = i;
        int len = i2;
        if (this.closed) {
            Throwable th2 = th;
            new IOException("Attempted write to closed stream.");
            throw th2;
        }
        this.out.write(b, off, len);
    }

    public void write(byte[] bArr) throws IOException {
        byte[] b = bArr;
        write(b, 0, b.length);
    }

    public void write(int i) throws IOException {
        Throwable th;
        int b = i;
        if (this.closed) {
            Throwable th2 = th;
            new IOException("Attempted write to closed stream.");
            throw th2;
        }
        this.out.write(b);
    }
}
