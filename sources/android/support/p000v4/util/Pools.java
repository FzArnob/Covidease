package android.support.p000v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* renamed from: android.support.v4.util.Pools */
public final class Pools {

    /* renamed from: android.support.v4.util.Pools$Pool */
    public interface Pool<T> {
        @Nullable
        T acquire();

        boolean release(@NonNull T t);
    }

    private Pools() {
    }

    /* renamed from: android.support.v4.util.Pools$SimplePool */
    public static class SimplePool<T> implements Pool<T> {
        private final Object[] mPool;
        private int mPoolSize;

        public SimplePool(int i) {
            Throwable th;
            int maxPoolSize = i;
            if (maxPoolSize <= 0) {
                Throwable th2 = th;
                new IllegalArgumentException("The max pool size must be > 0");
                throw th2;
            }
            this.mPool = new Object[maxPoolSize];
        }

        public T acquire() {
            if (this.mPoolSize <= 0) {
                return null;
            }
            int lastPooledIndex = this.mPoolSize - 1;
            SimplePool<T> instance = this.mPool[lastPooledIndex];
            this.mPool[lastPooledIndex] = null;
            this.mPoolSize--;
            return instance;
        }

        public boolean release(@NonNull T t) {
            Throwable th;
            T instance = t;
            if (isInPool(instance)) {
                Throwable th2 = th;
                new IllegalStateException("Already in the pool!");
                throw th2;
            } else if (this.mPoolSize >= this.mPool.length) {
                return false;
            } else {
                this.mPool[this.mPoolSize] = instance;
                this.mPoolSize++;
                return true;
            }
        }

        private boolean isInPool(@NonNull T t) {
            T instance = t;
            for (int i = 0; i < this.mPoolSize; i++) {
                if (this.mPool[i] == instance) {
                    return true;
                }
            }
            return false;
        }
    }

    /* renamed from: android.support.v4.util.Pools$SynchronizedPool */
    public static class SynchronizedPool<T> extends SimplePool<T> {
        private final Object mLock;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SynchronizedPool(int maxPoolSize) {
            super(maxPoolSize);
            Object obj;
            new Object();
            this.mLock = obj;
        }

        public T acquire() {
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    T acquire = super.acquire();
                    return acquire;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }

        public boolean release(@NonNull T t) {
            T element = t;
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    boolean release = super.release(element);
                    return release;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }
}
