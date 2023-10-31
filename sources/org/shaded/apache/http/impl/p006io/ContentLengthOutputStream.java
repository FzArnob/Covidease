package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import java.io.OutputStream;
import org.shaded.apache.http.p007io.SessionOutputBuffer;

/* renamed from: org.shaded.apache.http.impl.io.ContentLengthOutputStream */
public class ContentLengthOutputStream extends OutputStream {
    private boolean closed = false;
    private final long contentLength;
    private final SessionOutputBuffer out;
    private long total = 0;

    public ContentLengthOutputStream(SessionOutputBuffer sessionOutputBuffer, long j) {
        Throwable th;
        Throwable th2;
        SessionOutputBuffer out2 = sessionOutputBuffer;
        long contentLength2 = j;
        if (out2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Session output buffer may not be null");
            throw th3;
        } else if (contentLength2 < 0) {
            Throwable th4 = th;
            new IllegalArgumentException("Content length may not be negative");
            throw th4;
        } else {
            this.out = out2;
            this.contentLength = contentLength2;
        }
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
        } else if (this.total < this.contentLength) {
            long max = this.contentLength - this.total;
            if (((long) len) > max) {
                len = (int) max;
            }
            this.out.write(b, off, len);
            this.total += (long) len;
        }
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
        } else if (this.total < this.contentLength) {
            this.out.write(b);
            this.total++;
        }
    }
}
