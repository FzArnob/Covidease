package com.google.android.gms.tasks;

import androidx.annotation.NonNull;

public class TaskCompletionSource<TResult> {
    /* access modifiers changed from: private */
    public final zzu<TResult> zza;

    public TaskCompletionSource() {
        zzu<TResult> zzu;
        new zzu<>();
        this.zza = zzu;
    }

    public TaskCompletionSource(@NonNull CancellationToken cancellationToken) {
        zzu<TResult> zzu;
        OnTokenCanceledListener onTokenCanceledListener;
        new zzu<>();
        this.zza = zzu;
        new zzs(this);
        CancellationToken onCanceledRequested = cancellationToken.onCanceledRequested(onTokenCanceledListener);
    }

    public void setResult(TResult tresult) {
        this.zza.setResult(tresult);
    }

    public boolean trySetResult(TResult tresult) {
        return this.zza.trySetResult(tresult);
    }

    public void setException(@NonNull Exception exc) {
        this.zza.setException(exc);
    }

    public boolean trySetException(@NonNull Exception exc) {
        return this.zza.trySetException(exc);
    }

    @NonNull
    public Task<TResult> getTask() {
        return this.zza;
    }
}
