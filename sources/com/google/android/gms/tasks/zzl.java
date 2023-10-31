package com.google.android.gms.tasks;

final class zzl implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzk zzo;

    zzl(zzk zzk, Task task) {
        this.zzo = zzk;
        this.zzg = task;
    }

    public final void run() {
        Object zza = this.zzo.mLock;
        Object obj = zza;
        synchronized (zza) {
            try {
                if (this.zzo.zzn != null) {
                    this.zzo.zzn.onFailure(this.zzg.getException());
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj2 = obj;
                throw th2;
            }
        }
    }
}
