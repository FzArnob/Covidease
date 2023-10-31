package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;

final class zzbl extends LocationServices.zza<LocationSettingsResult> {
    private final /* synthetic */ LocationSettingsRequest zzdp;
    private final /* synthetic */ String zzdq = null;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbl(zzbk zzbk, GoogleApiClient googleApiClient, LocationSettingsRequest locationSettingsRequest, String str) {
        super(googleApiClient);
        zzbk zzbk2 = zzbk;
        String str2 = str;
        this.zzdp = locationSettingsRequest;
    }

    public final /* synthetic */ Result createFailedResult(Status status) {
        Result result;
        new LocationSettingsResult(status);
        return result;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzaz) anyClient).zza(this.zzdp, (BaseImplementation.ResultHolder<LocationSettingsResult>) this, this.zzdq);
    }
}
