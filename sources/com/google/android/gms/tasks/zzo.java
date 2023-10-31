package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzo<TResult, TContinuationResult> implements OnCanceledListener, OnFailureListener, OnSuccessListener<TContinuationResult>, zzq<TResult> {
    private final Executor zzd;
    private final zzu<TContinuationResult> zzf;
    /* access modifiers changed from: private */
    public final SuccessContinuation<TResult, TContinuationResult> zzr;

    public zzo(@NonNull Executor executor, @NonNull SuccessContinuation<TResult, TContinuationResult> successContinuation, @NonNull zzu<TContinuationResult> zzu) {
        this.zzd = executor;
        this.zzr = successContinuation;
        this.zzf = zzu;
    }

    public final void onComplete(@NonNull Task<TResult> task) {
        Runnable runnable;
        new zzp(this, task);
        this.zzd.execute(runnable);
    }

    public final void cancel() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
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
}
