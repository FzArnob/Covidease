package com.google.android.gms.tasks;

final class zzf implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zze zzi;

    zzf(zze zze, Task task) {
        this.zzi = zze;
        this.zzg = task;
    }

    public final void run() {
        Exception exc;
        try {
            Task task = (Task) this.zzi.zze.then(this.zzg);
            if (task == null) {
                new NullPointerException("Continuation returned null");
                this.zzi.onFailure(exc);
                return;
            }
            Task addOnSuccessListener = task.addOnSuccessListener(TaskExecutors.zzw, this.zzi);
            Task addOnFailureListener = task.addOnFailureListener(TaskExecutors.zzw, (OnFailureListener) this.zzi);
            Task addOnCanceledListener = task.addOnCanceledListener(TaskExecutors.zzw, (OnCanceledListener) this.zzi);
        } catch (RuntimeExecutionException e) {
            RuntimeExecutionException runtimeExecutionException = e;
            RuntimeExecutionException runtimeExecutionException2 = runtimeExecutionException;
            if (runtimeExecutionException.getCause() instanceof Exception) {
                this.zzi.zzf.setException((Exception) runtimeExecutionException2.getCause());
            } else {
                this.zzi.zzf.setException(runtimeExecutionException2);
            }
        } catch (Exception e2) {
            this.zzi.zzf.setException(e2);
        }
    }
}
