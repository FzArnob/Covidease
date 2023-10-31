package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzv extends zzab {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzv(zzq zzq, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        zzq zzq2 = zzq;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzaj zzaj;
        new zzac(this);
        ((zzaz) anyClient).zza(zzaj);
    }
}
