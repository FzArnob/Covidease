package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;

final class zzs extends zzab {
    private final /* synthetic */ LocationCallback zzcm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzs(zzq zzq, GoogleApiClient googleApiClient, LocationCallback locationCallback) {
        super(googleApiClient);
        zzq zzq2 = zzq;
        this.zzcm = locationCallback;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzaj zzaj;
        ListenerHolder.ListenerKey createListenerKey = ListenerHolders.createListenerKey(this.zzcm, LocationCallback.class.getSimpleName());
        new zzac(this);
        ((zzaz) anyClient).zzb(createListenerKey, zzaj);
    }
}
