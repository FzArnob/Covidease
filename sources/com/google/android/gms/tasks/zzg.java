package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

final class zzg<TResult> implements zzq<TResult> {
    /* access modifiers changed from: private */
    public final Object mLock;
    private final Executor zzd;
    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public OnCanceledListener zzj;

    public zzg(@NonNull Executor executor, @NonNull OnCanceledListener onCanceledListener) {
        Object obj;
        new Object();
        this.mLock = obj;
        this.zzd = executor;
        this.zzj = onCanceledListener;
    }

    /* JADX INFO: finally extract failed */
    public final void onComplete(@NonNull Task task) {
        Runnable runnable;
        if (task.isCanceled()) {
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    if (this.zzj == null) {
                        return;
                    }
                    new zzh(this);
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
                this.zzj = null;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }
}
