package com.shaded.fasterxml.jackson.core.p005io;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.shaded.fasterxml.jackson.core.io.MergedStream */
public final class MergedStream extends InputStream {
    byte[] _buffer;
    protected final IOContext _context;
    final int _end;
    final InputStream _in;
    int _ptr;

    public MergedStream(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2) {
        this._context = iOContext;
        this._in = inputStream;
        this._buffer = bArr;
        this._ptr = i;
        this._end = i2;
    }

    public int available() throws IOException {
        if (this._buffer != null) {
            return this._end - this._ptr;
        }
        return this._in.available();
    }

    public void close() throws IOException {
        freeMergedBuffer();
        this._in.close();
    }

    public void mark(int i) {
        int i2 = i;
        if (this._buffer == null) {
            this._in.mark(i2);
        }
    }

    public boolean markSupported() {
        return this._buffer == null && this._in.markSupported();
    }

    public int read() throws IOException {
        if (this._buffer == null) {
            return this._in.read();
        }
        byte[] bArr = this._buffer;
        int i = this._ptr;
        int i2 = i + 1;
        this._ptr = i2;
        byte b = bArr[i] & Ev3Constants.Opcode.TST;
        if (this._ptr >= this._end) {
            freeMergedBuffer();
        }
        return b;
    }

    public int read(byte[] bArr) throws IOException {
        byte[] bArr2 = bArr;
        return read(bArr2, 0, bArr2.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (this._buffer == null) {
            return this._in.read(bArr2, i3, i4);
        }
        int i5 = this._end - this._ptr;
        if (i4 > i5) {
            i4 = i5;
        }
        System.arraycopy(this._buffer, this._ptr, bArr2, i3, i4);
        this._ptr += i4;
        if (this._ptr >= this._end) {
            freeMergedBuffer();
        }
        return i4;
    }

    public void reset() throws IOException {
        if (this._buffer == null) {
            this._in.reset();
        }
    }

    public long skip(long j) throws IOException {
        long j2 = j;
        long j3 = 0;
        if (this._buffer != null) {
            int i = this._end - this._ptr;
            if (((long) i) > j2) {
                this._ptr += (int) j2;
                return j2;
            }
            freeMergedBuffer();
            j3 = 0 + ((long) i);
            j2 -= (long) i;
        }
        if (j2 > 0) {
            j3 += this._in.skip(j2);
        }
        return j3;
    }

    private void freeMergedBuffer() {
        byte[] bArr = this._buffer;
        if (bArr != null) {
            this._buffer = null;
            if (this._context != null) {
                this._context.releaseReadIOBuffer(bArr);
            }
        }
    }
}
