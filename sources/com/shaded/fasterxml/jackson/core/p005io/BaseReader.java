package com.shaded.fasterxml.jackson.core.p005io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* renamed from: com.shaded.fasterxml.jackson.core.io.BaseReader */
abstract class BaseReader extends Reader {
    protected static final int LAST_VALID_UNICODE_CHAR = 1114111;
    protected static final char NULL_BYTE = 0;
    protected static final char NULL_CHAR = 0;
    protected byte[] _buffer;
    protected final IOContext _context;
    protected InputStream _in;
    protected int _length;
    protected int _ptr;
    protected char[] _tmpBuf = null;

    protected BaseReader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2) {
        this._context = iOContext;
        this._in = inputStream;
        this._buffer = bArr;
        this._ptr = i;
        this._length = i2;
    }

    public void close() throws IOException {
        InputStream inputStream = this._in;
        if (inputStream != null) {
            this._in = null;
            freeBuffers();
            inputStream.close();
        }
    }

    public int read() throws IOException {
        if (this._tmpBuf == null) {
            this._tmpBuf = new char[1];
        }
        if (read(this._tmpBuf, 0, 1) < 1) {
            return -1;
        }
        return this._tmpBuf[0];
    }

    public final void freeBuffers() {
        byte[] bArr = this._buffer;
        if (bArr != null) {
            this._buffer = null;
            this._context.releaseReadIOBuffer(bArr);
        }
    }

    /* access modifiers changed from: protected */
    public void reportBounds(char[] cArr, int i, int i2) throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new ArrayIndexOutOfBoundsException(sb.append("read(buf,").append(i).append(",").append(i2).append("), cbuf[").append(cArr.length).append("]").toString());
        throw th2;
    }

    /* access modifiers changed from: protected */
    public void reportStrangeStream() throws IOException {
        Throwable th;
        Throwable th2 = th;
        new IOException("Strange I/O stream, returned 0 bytes on read");
        throw th2;
    }
}
