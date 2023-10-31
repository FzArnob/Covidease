package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

final class zze<TResult, TContinuationResult> implements OnCanceledListener, OnFailureListener, OnSuccessListener<TContinuationResult>, zzq<TResult> {
    private final Executor zzd;
    /* access modifiers changed from: private */
    public final Continuation<TResult, Task<TContinuationResult>> zze;
    /* access modifiers changed from: private */
    public final zzu<TContinuationResult> zzf;

    public zze(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation, @NonNull zzu<TContinuationResult> zzu) {
        this.zzd = executor;
        this.zze = continuation;
        this.zzf = zzu;
    }

    public final void onComplete(@NonNull Task<TResult> task) {
        Runnable runnable;
        new zzf(this, task);
        this.zzd.execute(runnable);
    }

    public final void onSuccess(TContinuationResult tcontinuationresult) {
        this.zzf.setResult(tcontinuationresult);
    }

    public final void onFailure(@NonNull Exception exc) {
        this.zzf.setException(exc);
    }

    public final void onCanceled() {
        boolean zza = this.zzf.zza();
    }

    public final void cancel() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }
}
