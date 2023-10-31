package com.bumptech.glide.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

public class ExceptionCatchingInputStream extends InputStream {
    private static final Queue<ExceptionCatchingInputStream> QUEUE = Util.createQueue(0);
    private IOException exception;
    private InputStream wrapped;

    public static ExceptionCatchingInputStream obtain(InputStream inputStream) {
        ExceptionCatchingInputStream exceptionCatchingInputStream;
        InputStream toWrap = inputStream;
        Queue<ExceptionCatchingInputStream> queue = QUEUE;
        Queue<ExceptionCatchingInputStream> queue2 = queue;
        synchronized (queue) {
            try {
                ExceptionCatchingInputStream result = QUEUE.poll();
                if (result == null) {
                    new ExceptionCatchingInputStream();
                    result = exceptionCatchingInputStream;
                }
                result.setInputStream(toWrap);
                return result;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Queue<ExceptionCatchingInputStream> queue3 = queue2;
                    throw th2;
                }
            }
        }
    }

    static void clearQueue() {
        while (!QUEUE.isEmpty()) {
            ExceptionCatchingInputStream remove = QUEUE.remove();
        }
    }

    ExceptionCatchingInputStream() {
    }

    /* access modifiers changed from: package-private */
    public void setInputStream(InputStream toWrap) {
        InputStream inputStream = toWrap;
        this.wrapped = inputStream;
    }

    public int available() throws IOException {
        return this.wrapped.available();
    }

    public void close() throws IOException {
        this.wrapped.close();
    }

    public void mark(int readlimit) {
        this.wrapped.mark(readlimit);
    }

    public boolean markSupported() {
        return this.wrapped.markSupported();
    }

    public int read(byte[] buffer) throws IOException {
        int read;
        try {
            read = this.wrapped.read(buffer);
        } catch (IOException e) {
            this.exception = e;
            read = -1;
        }
        return read;
    }

    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        int read;
        try {
            read = this.wrapped.read(buffer, byteOffset, byteCount);
        } catch (IOException e) {
            this.exception = e;
            read = -1;
        }
        return read;
    }

    public synchronized void reset() throws IOException {
        synchronized (this) {
            this.wrapped.reset();
        }
    }

    public long skip(long byteCount) throws IOException {
        long skipped;
        try {
            skipped = this.wrapped.skip(byteCount);
        } catch (IOException e) {
            this.exception = e;
            skipped = 0;
        }
        return skipped;
    }

    public int read() throws IOException {
        int result;
        try {
            result = this.wrapped.read();
        } catch (IOException e) {
            this.exception = e;
            result = -1;
        }
        return result;
    }

    public IOException getException() {
        return this.exception;
    }

    public void release() {
        this.exception = null;
        this.wrapped = null;
        Queue<ExceptionCatchingInputStream> queue = QUEUE;
        Queue<ExceptionCatchingInputStream> queue2 = queue;
        synchronized (queue) {
            try {
                boolean offer = QUEUE.offer(this);
            } catch (Throwable th) {
                Throwable th2 = th;
                Queue<ExceptionCatchingInputStream> queue3 = queue2;
                throw th2;
            }
        }
    }
}
