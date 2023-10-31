package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;

final class zzp implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzo zzs;

    zzp(zzo zzo, Task task) {
        this.zzs = zzo;
        this.zzg = task;
    }

    public final void run() {
        Exception exc;
        try {
            Task then = this.zzs.zzr.then(this.zzg.getResult());
            if (then == null) {
                new NullPointerException("Continuation returned null");
                this.zzs.onFailure(exc);
                return;
            }
            Task addOnSuccessListener = then.addOnSuccessListener(TaskExecutors.zzw, this.zzs);
            Task addOnFailureListener = then.addOnFailureListener(TaskExecutors.zzw, (OnFailureListener) this.zzs);
            Task addOnCanceledListener = then.addOnCanceledListener(TaskExecutors.zzw, (OnCanceledListener) this.zzs);
        } catch (RuntimeExecutionException e) {
            RuntimeExecutionException runtimeExecutionException = e;
            RuntimeExecutionException runtimeExecutionException2 = runtimeExecutionException;
            if (runtimeExecutionException.getCause() instanceof Exception) {
                this.zzs.onFailure((Exception) runtimeExecutionException2.getCause());
            } else {
                this.zzs.onFailure(runtimeExecutionException2);
            }
        } catch (CancellationException e2) {
            this.zzs.onCanceled();
        } catch (Exception e3) {
            this.zzs.onFailure(e3);
        }
    }
}
