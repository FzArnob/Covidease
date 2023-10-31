package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzk<TResult> implements zzq<TResult> {
    /* access modifiers changed from: private */
    public final Object mLock;
    private final Executor zzd;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public OnFailureListener zzn;

    public zzk(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        Object obj;
        new Object();
        this.mLock = obj;
        this.zzd = executor;
        this.zzn = onFailureListener;
    }

    /* JADX INFO: finally extract failed */
    public final void onComplete(@NonNull Task<TResult> task) {
        Runnable runnable;
        Task<TResult> task2 = task;
        if (!task2.isSuccessful() && !task2.isCanceled()) {
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    if (this.zzn == null) {
                        return;
                    }
                    new zzl(this, task2);
                    this.zzd.execute(runnable);
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        Object obj3 = obj2;
                        throw th2;
                    }
                }
            }
        }
    }

    public final void cancel() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                this.zzn = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }
}
