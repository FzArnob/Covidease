package org.shaded.apache.http.impl.p006io;

import java.io.IOException;
import java.io.OutputStream;
import org.shaded.apache.http.p007io.SessionOutputBuffer;

/* renamed from: org.shaded.apache.http.impl.io.ChunkedOutputStream */
public class ChunkedOutputStream extends OutputStream {
    private byte[] cache;
    private int cachePosition;
    private boolean closed;
    private final SessionOutputBuffer out;
    private boolean wroteLastChunk;

    public ChunkedOutputStream(SessionOutputBuffer out2, int bufferSize) throws IOException {
        this.cachePosition = 0;
        this.wroteLastChunk = false;
        this.closed = false;
        this.cache = new byte[bufferSize];
        this.out = out2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ChunkedOutputStream(SessionOutputBuffer out2) throws IOException {
        this(out2, 2048);
    }

    /* access modifiers changed from: protected */
    public void flushCache() throws IOException {
        if (this.cachePosition > 0) {
            this.out.writeLine(Integer.toHexString(this.cachePosition));
            this.out.write(this.cache, 0, this.cachePosition);
            this.out.writeLine("");
            this.cachePosition = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void flushCacheWithAppend(byte[] bufferToAppend, int off, int i) throws IOException {
        int len = i;
        this.out.writeLine(Integer.toHexString(this.cachePosition + len));
        this.out.write(this.cache, 0, this.cachePosition);
        this.out.write(bufferToAppend, off, len);
        this.out.writeLine("");
        this.cachePosition = 0;
    }

    /* access modifiers changed from: protected */
    public void writeClosingChunk() throws IOException {
        this.out.writeLine("0");
        this.out.writeLine("");
    }

    public void finish() throws IOException {
        if (!this.wroteLastChunk) {
            flushCache();
            writeClosingChunk();
            this.wroteLastChunk = true;
        }
    }

    public void write(int i) throws IOException {
        Throwable th;
        int b = i;
        if (this.closed) {
            Throwable th2 = th;
            new IOException("Attempted write to closed stream.");
            throw th2;
        }
        this.cache[this.cachePosition] = (byte) b;
        this.cachePosition++;
        if (this.cachePosition == this.cache.length) {
            flushCache();
        }
    }

    public void write(byte[] bArr) throws IOException {
        byte[] b = bArr;
        write(b, 0, b.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        Throwable th;
        byte[] src = bArr;
        int off = i;
        int len = i2;
        if (this.closed) {
            Throwable th2 = th;
            new IOException("Attempted write to closed stream.");
            throw th2;
        } else if (len >= this.cache.length - this.cachePosition) {
            flushCacheWithAppend(src, off, len);
        } else {
            System.arraycopy(src, off, this.cache, this.cachePosition, len);
            this.cachePosition += len;
        }
    }

    public void flush() throws IOException {
        flushCache();
        this.out.flush();
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            finish();
            this.out.flush();
        }
    }
}
