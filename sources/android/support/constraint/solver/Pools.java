package android.support.constraint.solver;

final class Pools {
    private static final boolean DEBUG = false;

    interface Pool<T> {
        T acquire();

        boolean release(T t);

        void releaseAll(T[] tArr, int i);
    }

    private Pools() {
    }

    static class SimplePool<T> implements Pool<T> {
        private final Object[] mPool;
        private int mPoolSize;

        SimplePool(int i) {
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

        public boolean release(T t) {
            T instance = t;
            if (this.mPoolSize >= this.mPool.length) {
                return false;
            }
            this.mPool[this.mPoolSize] = instance;
            this.mPoolSize++;
            return true;
        }

        public void releaseAll(T[] tArr, int i) {
            T[] variables = tArr;
            int count = i;
            if (count > variables.length) {
                count = variables.length;
            }
            for (int i2 = 0; i2 < count; i2++) {
                T instance = variables[i2];
                if (this.mPoolSize < this.mPool.length) {
                    this.mPool[this.mPoolSize] = instance;
                    this.mPoolSize++;
                }
            }
        }

        private boolean isInPool(T t) {
            T instance = t;
            for (int i = 0; i < this.mPoolSize; i++) {
                if (this.mPool[i] == instance) {
                    return true;
                }
            }
            return false;
        }
    }
}
