package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

final class zzr<TResult> {
    private final Object mLock;
    @GuardedBy("mLock")
    private Queue<zzq<TResult>> zzt;
    @GuardedBy("mLock")
    private boolean zzu;

    zzr() {
        Object obj;
        new Object();
        this.mLock = obj;
    }

    public final void zza(@NonNull zzq<TResult> zzq) {
        Queue<zzq<TResult>> queue;
        zzq<TResult> zzq2 = zzq;
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zzt == null) {
                    new ArrayDeque();
                    this.zzt = queue;
                }
                boolean add = this.zzt.add(zzq2);
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void zza(@NonNull Task<TResult> task) {
        Task<TResult> task2 = task;
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (this.zzt == null || this.zzu) {
                    return;
                }
                this.zzu = true;
                while (true) {
                    Object obj3 = this.mLock;
                    Object obj4 = obj3;
                    synchronized (obj3) {
                        try {
                            zzq poll = this.zzt.poll();
                            zzq zzq = poll;
                            if (poll == null) {
                                this.zzu = false;
                                return;
                            }
                            zzq.onComplete(task2);
                        } catch (Throwable th) {
                            while (true) {
                                Throwable th2 = th;
                                Object obj5 = obj4;
                                throw th2;
                            }
                        }
                    }
                }
            } catch (Throwable th3) {
                while (true) {
                    Throwable th4 = th3;
                    Object obj6 = obj2;
                    throw th4;
                }
            }
        }
    }
}
