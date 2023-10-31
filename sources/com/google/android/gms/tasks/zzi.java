package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzi<TResult> implements zzq<TResult> {
    /* access modifiers changed from: private */
    public final Object mLock;
    private final Executor zzd;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public OnCompleteListener<TResult> zzl;

    public zzi(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        Object obj;
        new Object();
        this.mLock = obj;
        this.zzd = executor;
        this.zzl = onCompleteListener;
    }

    /* JADX INFO: finally extract failed */
    public final void onComplete(@NonNull Task<TResult> task) {
        Runnable runnable;
        Task<TResult> task2 = task;
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zzl == null) {
                    return;
                }
                new zzj(this, task2);
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

    public final void cancel() {
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                this.zzl = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }
}
