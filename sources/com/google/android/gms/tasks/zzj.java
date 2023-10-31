package com.google.android.gms.tasks;

final class zzj implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzi zzm;

    zzj(zzi zzi, Task task) {
        this.zzm = zzi;
        this.zzg = task;
    }

    public final void run() {
        Object zza = this.zzm.mLock;
        Object obj = zza;
        synchronized (zza) {
            try {
                if (this.zzm.zzl != null) {
                    this.zzm.zzl.onComplete(this.zzg);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj2 = obj;
                throw th2;
            }
        }
    }
}
