package com.google.android.gms.internal.location;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzu extends zzab {
    private final /* synthetic */ Location zzco;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzu(zzq zzq, GoogleApiClient googleApiClient, Location location) {
        super(googleApiClient);
        zzq zzq2 = zzq;
        this.zzco = location;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zza(this.zzco);
        setResult(Status.RESULT_SUCCESS);
    }
}
