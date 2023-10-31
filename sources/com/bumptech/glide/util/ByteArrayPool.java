package com.bumptech.glide.util;

import android.util.Log;
import java.util.Queue;

public final class ByteArrayPool {
    private static final ByteArrayPool BYTE_ARRAY_POOL;
    private static final int MAX_BYTE_ARRAY_COUNT = 32;
    private static final int MAX_SIZE = 2146304;
    private static final String TAG = "ByteArrayPool";
    private static final int TEMP_BYTES_SIZE = 65536;
    private final Queue<byte[]> tempQueue = Util.createQueue(0);

    static {
        ByteArrayPool byteArrayPool;
        new ByteArrayPool();
        BYTE_ARRAY_POOL = byteArrayPool;
    }

    public static ByteArrayPool get() {
        return BYTE_ARRAY_POOL;
    }

    private ByteArrayPool() {
    }

    public void clear() {
        Queue<byte[]> queue = this.tempQueue;
        Queue<byte[]> queue2 = queue;
        synchronized (queue) {
            try {
                this.tempQueue.clear();
            } catch (Throwable th) {
                Throwable th2 = th;
                Queue<byte[]> queue3 = queue2;
                throw th2;
            }
        }
    }

    public byte[] getBytes() {
        Queue<byte[]> queue = this.tempQueue;
        Queue<byte[]> queue2 = queue;
        synchronized (queue) {
            try {
                byte[] result = this.tempQueue.poll();
                if (result == null) {
                    result = new byte[65536];
                    if (Log.isLoggable(TAG, 3)) {
                        int d = Log.d(TAG, "Created temp bytes");
                    }
                }
                return result;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Queue<byte[]> queue3 = queue2;
                    throw th2;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public boolean releaseBytes(byte[] bArr) {
        byte[] bytes = bArr;
        if (bytes.length != 65536) {
            return false;
        }
        boolean accepted = false;
        Queue<byte[]> queue = this.tempQueue;
        Queue<byte[]> queue2 = queue;
        synchronized (queue) {
            try {
                if (this.tempQueue.size() < 32) {
                    accepted = true;
                    boolean offer = this.tempQueue.offer(bytes);
                }
                return accepted;
            } catch (Throwable th) {
                Throwable th2 = th;
                Queue<byte[]> queue3 = queue2;
                throw th2;
            }
        }
    }
}
