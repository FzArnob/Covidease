package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzt extends zzab {
    private final /* synthetic */ boolean zzcn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzt(zzq zzq, GoogleApiClient googleApiClient, boolean z) {
        super(googleApiClient);
        zzq zzq2 = zzq;
        this.zzcn = z;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zza(this.zzcn);
        setResult(Status.RESULT_SUCCESS);
    }
}
