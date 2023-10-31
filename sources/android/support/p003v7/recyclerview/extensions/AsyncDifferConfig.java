package android.support.p003v7.recyclerview.extensions;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.p003v7.util.DiffUtil;
import java.util.concurrent.Executor;

/* renamed from: android.support.v7.recyclerview.extensions.AsyncDifferConfig */
public final class AsyncDifferConfig<T> {
    @NonNull
    private final Executor mBackgroundThreadExecutor;
    @NonNull
    private final DiffUtil.ItemCallback<T> mDiffCallback;
    @NonNull
    private final Executor mMainThreadExecutor;

    AsyncDifferConfig(@NonNull Executor mainThreadExecutor, @NonNull Executor backgroundThreadExecutor, @NonNull DiffUtil.ItemCallback<T> diffCallback) {
        this.mMainThreadExecutor = mainThreadExecutor;
        this.mBackgroundThreadExecutor = backgroundThreadExecutor;
        this.mDiffCallback = diffCallback;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NonNull
    public Executor getMainThreadExecutor() {
        return this.mMainThreadExecutor;
    }

    @NonNull
    public Executor getBackgroundThreadExecutor() {
        return this.mBackgroundThreadExecutor;
    }

    @NonNull
    public DiffUtil.ItemCallback<T> getDiffCallback() {
        return this.mDiffCallback;
    }

    /* renamed from: android.support.v7.recyclerview.extensions.AsyncDifferConfig$Builder */
    public static final class Builder<T> {
        private static Executor sDiffExecutor = null;
        private static final Object sExecutorLock;
        private Executor mBackgroundThreadExecutor;
        private final DiffUtil.ItemCallback<T> mDiffCallback;
        private Executor mMainThreadExecutor;

        public Builder(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
            this.mDiffCallback = diffCallback;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @NonNull
        public Builder<T> setMainThreadExecutor(Executor executor) {
            this.mMainThreadExecutor = executor;
            return this;
        }

        @NonNull
        public Builder<T> setBackgroundThreadExecutor(Executor executor) {
            this.mBackgroundThreadExecutor = executor;
            return this;
        }

        /* JADX WARNING: Multi-variable type inference failed */
        @android.support.annotation.NonNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.support.p003v7.recyclerview.extensions.AsyncDifferConfig<T> build() {
            /*
                r9 = this;
                r0 = r9
                r3 = r0
                java.util.concurrent.Executor r3 = r3.mBackgroundThreadExecutor
                if (r3 != 0) goto L_0x001f
                java.lang.Object r3 = sExecutorLock
                r8 = r3
                r3 = r8
                r4 = r8
                r1 = r4
                monitor-enter(r3)
                java.util.concurrent.Executor r3 = sDiffExecutor     // Catch:{ all -> 0x0032 }
                if (r3 != 0) goto L_0x0018
                r3 = 2
                java.util.concurrent.ExecutorService r3 = java.util.concurrent.Executors.newFixedThreadPool(r3)     // Catch:{ all -> 0x0032 }
                sDiffExecutor = r3     // Catch:{ all -> 0x0032 }
            L_0x0018:
                r3 = r1
                monitor-exit(r3)     // Catch:{ all -> 0x0032 }
                r3 = r0
                java.util.concurrent.Executor r4 = sDiffExecutor
                r3.mBackgroundThreadExecutor = r4
            L_0x001f:
                android.support.v7.recyclerview.extensions.AsyncDifferConfig r3 = new android.support.v7.recyclerview.extensions.AsyncDifferConfig
                r8 = r3
                r3 = r8
                r4 = r8
                r5 = r0
                java.util.concurrent.Executor r5 = r5.mMainThreadExecutor
                r6 = r0
                java.util.concurrent.Executor r6 = r6.mBackgroundThreadExecutor
                r7 = r0
                android.support.v7.util.DiffUtil$ItemCallback<T> r7 = r7.mDiffCallback
                r4.<init>(r5, r6, r7)
                r0 = r3
                return r0
            L_0x0032:
                r3 = move-exception
                r2 = r3
                r3 = r1
                monitor-exit(r3)     // Catch:{ all -> 0x0032 }
                r3 = r2
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.recyclerview.extensions.AsyncDifferConfig.Builder.build():android.support.v7.recyclerview.extensions.AsyncDifferConfig");
        }

        static {
            Object obj;
            new Object();
            sExecutorLock = obj;
        }
    }
}
