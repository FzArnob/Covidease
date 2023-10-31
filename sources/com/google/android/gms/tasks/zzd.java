package com.google.android.gms.tasks;

final class zzd implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzc zzh;

    zzd(zzc zzc, Task task) {
        this.zzh = zzc;
        this.zzg = task;
    }

    public final void run() {
        if (this.zzg.isCanceled()) {
            boolean zza = this.zzh.zzf.zza();
            return;
        }
        try {
            this.zzh.zzf.setResult(this.zzh.zze.then(this.zzg));
        } catch (RuntimeExecutionException e) {
            RuntimeExecutionException runtimeExecutionException = e;
            RuntimeExecutionException runtimeExecutionException2 = runtimeExecutionException;
            if (runtimeExecutionException.getCause() instanceof Exception) {
                this.zzh.zzf.setException((Exception) runtimeExecutionException2.getCause());
            } else {
                this.zzh.zzf.setException(runtimeExecutionException2);
            }
        } catch (Exception e2) {
            this.zzh.zzf.setException(e2);
        }
    }
}
