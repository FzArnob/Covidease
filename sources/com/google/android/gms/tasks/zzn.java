package com.google.android.gms.tasks;

final class zzn implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzm zzq;

    zzn(zzm zzm, Task task) {
        this.zzq = zzm;
        this.zzg = task;
    }

    public final void run() {
        Object zza = this.zzq.mLock;
        Object obj = zza;
        synchronized (zza) {
            try {
                if (this.zzq.zzp != null) {
                    this.zzq.zzp.onSuccess(this.zzg.getResult());
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj2 = obj;
                throw th2;
            }
        }
    }
}
