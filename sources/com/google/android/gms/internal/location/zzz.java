package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;

final class zzz extends zzab {
    private final /* synthetic */ LocationListener zzcl;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzz(zzq zzq, GoogleApiClient googleApiClient, LocationListener locationListener) {
        super(googleApiClient);
        zzq zzq2 = zzq;
        this.zzcl = locationListener;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzaj zzaj;
        ListenerHolder.ListenerKey createListenerKey = ListenerHolders.createListenerKey(this.zzcl, LocationListener.class.getSimpleName());
        new zzac(this);
        ((zzaz) anyClient).zza((ListenerHolder.ListenerKey<LocationListener>) createListenerKey, zzaj);
    }
}
