package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzc<TResult, TContinuationResult> implements zzq<TResult> {
    private final Executor zzd;
    /* access modifiers changed from: private */
    public final Continuation<TResult, TContinuationResult> zze;
    /* access modifiers changed from: private */
    public final zzu<TContinuationResult> zzf;

    public zzc(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation, @NonNull zzu<TContinuationResult> zzu) {
        this.zzd = executor;
        this.zze = continuation;
        this.zzf = zzu;
    }

    public final void onComplete(@NonNull Task<TResult> task) {
        Runnable runnable;
        new zzd(this, task);
        this.zzd.execute(runnable);
    }

    public final void cancel() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }
}
