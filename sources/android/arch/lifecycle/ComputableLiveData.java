package android.arch.lifecycle;

import android.arch.core.executor.ArchTaskExecutor;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class ComputableLiveData<T> {
    /* access modifiers changed from: private */
    public AtomicBoolean mComputing;
    /* access modifiers changed from: private */
    public final Executor mExecutor;
    /* access modifiers changed from: private */
    public AtomicBoolean mInvalid;
    @VisibleForTesting
    final Runnable mInvalidationRunnable;
    /* access modifiers changed from: private */
    public final LiveData<T> mLiveData;
    @VisibleForTesting
    final Runnable mRefreshRunnable;

    /* access modifiers changed from: protected */
    @WorkerThread
    public abstract T compute();

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ComputableLiveData() {
        this(ArchTaskExecutor.getIOThreadExecutor());
    }

    public ComputableLiveData(@NonNull Executor executor) {
        AtomicBoolean atomicBoolean;
        AtomicBoolean atomicBoolean2;
        Runnable runnable;
        Runnable runnable2;
        LiveData<T> liveData;
        new AtomicBoolean(true);
        this.mInvalid = atomicBoolean;
        new AtomicBoolean(false);
        this.mComputing = atomicBoolean2;
        new Runnable(this) {
            final /* synthetic */ ComputableLiveData this$0;

            {
                this.this$0 = this$0;
            }

            /* JADX WARNING: Removed duplicated region for block: B:3:0x0012  */
            @android.support.annotation.WorkerThread
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    r0 = r7
                L_0x0001:
                    r4 = 0
                    r1 = r4
                    r4 = r0
                    android.arch.lifecycle.ComputableLiveData r4 = r4.this$0
                    java.util.concurrent.atomic.AtomicBoolean r4 = r4.mComputing
                    r5 = 0
                    r6 = 1
                    boolean r4 = r4.compareAndSet(r5, r6)
                    if (r4 == 0) goto L_0x0047
                    r4 = 0
                    r2 = r4
                L_0x0014:
                    r4 = r0
                    android.arch.lifecycle.ComputableLiveData r4 = r4.this$0     // Catch:{ all -> 0x0058 }
                    java.util.concurrent.atomic.AtomicBoolean r4 = r4.mInvalid     // Catch:{ all -> 0x0058 }
                    r5 = 1
                    r6 = 0
                    boolean r4 = r4.compareAndSet(r5, r6)     // Catch:{ all -> 0x0058 }
                    if (r4 == 0) goto L_0x002e
                    r4 = 1
                    r1 = r4
                    r4 = r0
                    android.arch.lifecycle.ComputableLiveData r4 = r4.this$0     // Catch:{ all -> 0x0058 }
                    java.lang.Object r4 = r4.compute()     // Catch:{ all -> 0x0058 }
                    r2 = r4
                    goto L_0x0014
                L_0x002e:
                    r4 = r1
                    if (r4 == 0) goto L_0x003c
                    r4 = r0
                    android.arch.lifecycle.ComputableLiveData r4 = r4.this$0     // Catch:{ all -> 0x0058 }
                    android.arch.lifecycle.LiveData r4 = r4.mLiveData     // Catch:{ all -> 0x0058 }
                    r5 = r2
                    r4.postValue(r5)     // Catch:{ all -> 0x0058 }
                L_0x003c:
                    r4 = r0
                    android.arch.lifecycle.ComputableLiveData r4 = r4.this$0
                    java.util.concurrent.atomic.AtomicBoolean r4 = r4.mComputing
                    r5 = 0
                    r4.set(r5)
                L_0x0047:
                    r4 = r1
                    if (r4 == 0) goto L_0x0057
                    r4 = r0
                    android.arch.lifecycle.ComputableLiveData r4 = r4.this$0
                    java.util.concurrent.atomic.AtomicBoolean r4 = r4.mInvalid
                    boolean r4 = r4.get()
                    if (r4 != 0) goto L_0x0001
                L_0x0057:
                    return
                L_0x0058:
                    r4 = move-exception
                    r3 = r4
                    r4 = r0
                    android.arch.lifecycle.ComputableLiveData r4 = r4.this$0
                    java.util.concurrent.atomic.AtomicBoolean r4 = r4.mComputing
                    r5 = 0
                    r4.set(r5)
                    r4 = r3
                    throw r4
                */
                throw new UnsupportedOperationException("Method not decompiled: android.arch.lifecycle.ComputableLiveData.C00032.run():void");
            }
        };
        this.mRefreshRunnable = runnable;
        new Runnable(this) {
            final /* synthetic */ ComputableLiveData this$0;

            {
                this.this$0 = this$0;
            }

            @MainThread
            public void run() {
                boolean isActive = this.this$0.mLiveData.hasActiveObservers();
                if (this.this$0.mInvalid.compareAndSet(false, true) && isActive) {
                    this.this$0.mExecutor.execute(this.this$0.mRefreshRunnable);
                }
            }
        };
        this.mInvalidationRunnable = runnable2;
        this.mExecutor = executor;
        new LiveData<T>(this) {
            final /* synthetic */ ComputableLiveData this$0;

            {
                this.this$0 = this$0;
            }

            /* access modifiers changed from: protected */
            public void onActive() {
                this.this$0.mExecutor.execute(this.this$0.mRefreshRunnable);
            }
        };
        this.mLiveData = liveData;
    }

    @NonNull
    public LiveData<T> getLiveData() {
        return this.mLiveData;
    }

    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(this.mInvalidationRunnable);
    }
}
