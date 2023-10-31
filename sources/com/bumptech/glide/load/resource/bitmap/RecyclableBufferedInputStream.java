package com.bumptech.glide.load.resource.bitmap;

import android.util.Log;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecyclableBufferedInputStream extends FilterInputStream {
    private static final String TAG = "BufferedIs";
    private volatile byte[] buf;
    private int count;
    private int marklimit;
    private int markpos = -1;
    private int pos;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RecyclableBufferedInputStream(InputStream in, byte[] bArr) {
        super(in);
        Throwable th;
        byte[] buffer = bArr;
        if (buffer == null || buffer.length == 0) {
            Throwable th2 = th;
            new IllegalArgumentException("buffer is null or empty");
            throw th2;
        }
        this.buf = buffer;
    }

    public synchronized int available() throws IOException {
        int available;
        synchronized (this) {
            InputStream localIn = this.in;
            if (this.buf == null || localIn == null) {
                throw streamClosed();
            }
            available = (this.count - this.pos) + localIn.available();
        }
        return available;
    }

    private static IOException streamClosed() throws IOException {
        Throwable th;
        Throwable th2 = th;
        new IOException("BufferedInputStream is closed");
        throw th2;
    }

    public synchronized void fixMarkLimit() {
        synchronized (this) {
            this.marklimit = this.buf.length;
        }
    }

    public void close() throws IOException {
        this.buf = null;
        InputStream localIn = this.in;
        this.in = null;
        if (localIn != null) {
            localIn.close();
        }
    }

    private int fillbuf(InputStream inputStream, byte[] bArr) throws IOException {
        StringBuilder sb;
        InputStream localIn = inputStream;
        byte[] localBuf = bArr;
        if (this.markpos == -1 || this.pos - this.markpos >= this.marklimit) {
            int result = localIn.read(localBuf);
            if (result > 0) {
                this.markpos = -1;
                this.pos = 0;
                this.count = result;
            }
            return result;
        }
        if (this.markpos == 0 && this.marklimit > localBuf.length && this.count == localBuf.length) {
            int newLength = localBuf.length * 2;
            if (newLength > this.marklimit) {
                newLength = this.marklimit;
            }
            if (Log.isLoggable(TAG, 3)) {
                new StringBuilder();
                int d = Log.d(TAG, sb.append("allocate buffer of length: ").append(newLength).toString());
            }
            byte[] newbuf = new byte[newLength];
            System.arraycopy(localBuf, 0, newbuf, 0, localBuf.length);
            byte[] bArr2 = newbuf;
            this.buf = bArr2;
            localBuf = bArr2;
        } else if (this.markpos > 0) {
            System.arraycopy(localBuf, this.markpos, localBuf, 0, localBuf.length - this.markpos);
        }
        this.pos -= this.markpos;
        this.markpos = 0;
        this.count = 0;
        int bytesread = localIn.read(localBuf, this.pos, localBuf.length - this.pos);
        this.count = bytesread <= 0 ? this.pos : this.pos + bytesread;
        return bytesread;
    }

    public synchronized void mark(int i) {
        int readlimit = i;
        synchronized (this) {
            this.marklimit = Math.max(this.marklimit, readlimit);
            this.markpos = this.pos;
        }
    }

    public boolean markSupported() {
        return true;
    }

    public synchronized int read() throws IOException {
        byte b;
        synchronized (this) {
            byte[] localBuf = this.buf;
            InputStream localIn = this.in;
            if (localBuf == null || localIn == null) {
                throw streamClosed();
            } else if (this.pos < this.count || fillbuf(localIn, localBuf) != -1) {
                if (localBuf != this.buf) {
                    localBuf = this.buf;
                    if (localBuf == null) {
                        throw streamClosed();
                    }
                }
                if (this.count - this.pos > 0) {
                    int i = this.pos;
                    int i2 = i + 1;
                    this.pos = i2;
                    b = localBuf[i] & Ev3Constants.Opcode.TST;
                } else {
                    b = -1;
                }
            } else {
                b = -1;
            }
        }
        return b;
    }

    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        int required;
        int read;
        int i3;
        int i4;
        byte[] buffer = bArr;
        int offset = i;
        int byteCount = i2;
        synchronized (this) {
            byte[] localBuf = this.buf;
            if (localBuf == null) {
                throw streamClosed();
            } else if (byteCount == 0) {
                i3 = 0;
            } else {
                InputStream localIn = this.in;
                if (localIn == null) {
                    throw streamClosed();
                }
                if (this.pos < this.count) {
                    int copylength = this.count - this.pos >= byteCount ? byteCount : this.count - this.pos;
                    System.arraycopy(localBuf, this.pos, buffer, offset, copylength);
                    this.pos += copylength;
                    if (copylength == byteCount || localIn.available() == 0) {
                        i3 = copylength;
                    } else {
                        offset += copylength;
                        required = byteCount - copylength;
                    }
                } else {
                    required = byteCount;
                }
                while (true) {
                    if (this.markpos == -1 && required >= localBuf.length) {
                        read = localIn.read(buffer, offset, required);
                        if (read == -1) {
                            if (required == byteCount) {
                                i4 = -1;
                            } else {
                                i4 = byteCount - required;
                            }
                            i3 = i4;
                        }
                    } else if (fillbuf(localIn, localBuf) == -1) {
                        i3 = required == byteCount ? -1 : byteCount - required;
                    } else {
                        if (localBuf != this.buf) {
                            localBuf = this.buf;
                            if (localBuf == null) {
                                throw streamClosed();
                            }
                        }
                        read = this.count - this.pos >= required ? required : this.count - this.pos;
                        System.arraycopy(localBuf, this.pos, buffer, offset, read);
                        this.pos += read;
                    }
                    required -= read;
                    if (required == 0) {
                        i3 = byteCount;
                        break;
                    } else if (localIn.available() == 0) {
                        i3 = byteCount - required;
                        break;
                    } else {
                        offset += read;
                    }
                }
            }
        }
        return i3;
    }

    public synchronized void reset() throws IOException {
        Throwable th;
        Throwable th2;
        synchronized (this) {
            if (this.buf == null) {
                Throwable th3 = th2;
                new IOException("Stream is closed");
                throw th3;
            } else if (-1 == this.markpos) {
                Throwable th4 = th;
                new InvalidMarkException("Mark has been invalidated");
                throw th4;
            } else {
                this.pos = this.markpos;
            }
        }
    }

    public synchronized long skip(long j) throws IOException {
        long skip;
        long byteCount = j;
        synchronized (this) {
            byte[] localBuf = this.buf;
            InputStream localIn = this.in;
            if (localBuf == null) {
                throw streamClosed();
            } else if (byteCount < 1) {
                skip = 0;
            } else if (localIn == null) {
                throw streamClosed();
            } else if (((long) (this.count - this.pos)) >= byteCount) {
                this.pos = (int) (((long) this.pos) + byteCount);
                skip = byteCount;
            } else {
                long read = (long) (this.count - this.pos);
                this.pos = this.count;
                if (this.markpos == -1 || byteCount > ((long) this.marklimit)) {
                    skip = read + localIn.skip(byteCount - read);
                } else if (fillbuf(localIn, localBuf) == -1) {
                    skip = read;
                } else if (((long) (this.count - this.pos)) >= byteCount - read) {
                    this.pos = (int) (((long) this.pos) + (byteCount - read));
                    skip = byteCount;
                } else {
                    long read2 = (read + ((long) this.count)) - ((long) this.pos);
                    this.pos = this.count;
                    skip = read2;
                }
            }
        }
        return skip;
    }

    public static class InvalidMarkException extends RuntimeException {
        private static final long serialVersionUID = -4338378848813561757L;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InvalidMarkException(String detailMessage) {
            super(detailMessage);
        }
    }
}
