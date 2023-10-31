package com.google.android.gms.tasks;

import androidx.annotation.NonNull;

final class zza extends CancellationToken {
    private final zzu<Void> zza;

    zza() {
        zzu<Void> zzu;
        new zzu<>();
        this.zza = zzu;
    }

    public final boolean isCancellationRequested() {
        return this.zza.isComplete();
    }

    public final CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
        OnSuccessListener onSuccessListener;
        new zzb(this, onTokenCanceledListener);
        Task<Void> addOnSuccessListener = this.zza.addOnSuccessListener(onSuccessListener);
        return this;
    }

    public final void cancel() {
        boolean trySetResult = this.zza.trySetResult(null);
    }
}
