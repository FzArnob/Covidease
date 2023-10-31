package com.google.android.gms.tasks;

final class zzh implements Runnable {
    private final /* synthetic */ zzg zzk;

    zzh(zzg zzg) {
        this.zzk = zzg;
    }

    public final void run() {
        Object zza = this.zzk.mLock;
        Object obj = zza;
        synchronized (zza) {
            try {
                if (this.zzk.zzj != null) {
                    this.zzk.zzj.onCanceled();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj2 = obj;
                throw th2;
            }
        }
    }
}
