package com.google.android.gms.tasks;

final class zzs implements OnTokenCanceledListener {
    private final /* synthetic */ TaskCompletionSource zzv;

    zzs(TaskCompletionSource taskCompletionSource) {
        this.zzv = taskCompletionSource;
    }

    public final void onCanceled() {
        boolean zza = this.zzv.zza.zza();
    }
}
