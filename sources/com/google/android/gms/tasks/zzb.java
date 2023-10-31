package com.google.android.gms.tasks;

final class zzb implements OnSuccessListener<Void> {
    private final /* synthetic */ OnTokenCanceledListener zzb;

    zzb(zza zza, OnTokenCanceledListener onTokenCanceledListener) {
        zza zza2 = zza;
        this.zzb = onTokenCanceledListener;
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        Object obj2 = obj;
        this.zzb.onCanceled();
    }
}
