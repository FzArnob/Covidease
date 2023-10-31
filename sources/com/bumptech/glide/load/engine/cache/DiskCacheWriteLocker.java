package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class DiskCacheWriteLocker {
    private final Map<Key, WriteLock> locks;
    private final WriteLockPool writeLockPool;

    DiskCacheWriteLocker() {
        Map<Key, WriteLock> map;
        WriteLockPool writeLockPool2;
        new HashMap();
        this.locks = map;
        new WriteLockPool((C15151) null);
        this.writeLockPool = writeLockPool2;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void acquire(Key key) {
        Key key2 = key;
        synchronized (this) {
            try {
                WriteLock writeLock = this.locks.get(key2);
                if (writeLock == null) {
                    writeLock = this.writeLockPool.obtain();
                    WriteLock put = this.locks.put(key2, writeLock);
                }
                writeLock.interestedThreads++;
                writeLock.lock.lock();
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    throw th2;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void release(Key key) {
        Throwable th;
        StringBuilder sb;
        int i;
        Throwable th2;
        StringBuilder sb2;
        Key key2 = key;
        synchronized (this) {
            try {
                WriteLock writeLock = this.locks.get(key2);
                if (writeLock == null || writeLock.interestedThreads <= 0) {
                    Throwable th3 = th;
                    new StringBuilder();
                    StringBuilder append = sb.append("Cannot release a lock that is not held, key: ").append(key2).append(", interestedThreads: ");
                    if (writeLock == null) {
                        i = 0;
                    } else {
                        i = writeLock.interestedThreads;
                    }
                    new IllegalArgumentException(append.append(i).toString());
                    throw th3;
                }
                WriteLock writeLock2 = writeLock;
                int i2 = writeLock2.interestedThreads - 1;
                int i3 = i2;
                writeLock2.interestedThreads = i2;
                if (i3 == 0) {
                    WriteLock removed = this.locks.remove(key2);
                    if (!removed.equals(writeLock)) {
                        Throwable th4 = th2;
                        new StringBuilder();
                        new IllegalStateException(sb2.append("Removed the wrong lock, expected to remove: ").append(writeLock).append(", but actually removed: ").append(removed).append(", key: ").append(key2).toString());
                        throw th4;
                    }
                    this.writeLockPool.offer(removed);
                }
                writeLock.lock.unlock();
            } catch (Throwable th5) {
                Throwable th6 = th5;
                throw th6;
            }
        }
    }

    private static class WriteLock {
        int interestedThreads;
        final Lock lock;

        private WriteLock() {
            Lock lock2;
            new ReentrantLock();
            this.lock = lock2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ WriteLock(C15151 r4) {
            this();
            C15151 r1 = r4;
        }
    }

    private static class WriteLockPool {
        private static final int MAX_POOL_SIZE = 10;
        private final Queue<WriteLock> pool;

        private WriteLockPool() {
            Queue<WriteLock> queue;
            new ArrayDeque();
            this.pool = queue;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ WriteLockPool(C15151 r4) {
            this();
            C15151 r1 = r4;
        }

        /* access modifiers changed from: package-private */
        public WriteLock obtain() {
            WriteLock writeLock;
            Queue<WriteLock> queue = this.pool;
            Queue<WriteLock> queue2 = queue;
            synchronized (queue) {
                try {
                    WriteLock result = this.pool.poll();
                    if (result == null) {
                        new WriteLock((C15151) null);
                        result = writeLock;
                    }
                    return result;
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        Queue<WriteLock> queue3 = queue2;
                        throw th2;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void offer(WriteLock writeLock) {
            WriteLock writeLock2 = writeLock;
            Queue<WriteLock> queue = this.pool;
            Queue<WriteLock> queue2 = queue;
            synchronized (queue) {
                try {
                    if (this.pool.size() < 10) {
                        boolean offer = this.pool.offer(writeLock2);
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Queue<WriteLock> queue3 = queue2;
                    throw th2;
                }
            }
        }
    }
}
