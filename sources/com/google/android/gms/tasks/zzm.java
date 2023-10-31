package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzm<TResult> implements zzq<TResult> {
    /* access modifiers changed from: private */
    public final Object mLock;
    private final Executor zzd;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public OnSuccessListener<? super TResult> zzp;

    public zzm(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        Object obj;
        new Object();
        this.mLock = obj;
        this.zzd = executor;
        this.zzp = onSuccessListener;
    }

    /* JADX INFO: finally extract failed */
    public final void onComplete(@NonNull Task<TResult> task) {
        Runnable runnable;
        Task<TResult> task2 = task;
        if (task2.isSuccessful()) {
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    if (this.zzp == null) {
                        return;
                    }
                    new zzn(this, task2);
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
                this.zzp = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }
}
