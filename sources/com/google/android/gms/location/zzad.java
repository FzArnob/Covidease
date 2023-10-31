package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.location.zzaz;

final class zzad extends Api.AbstractClientBuilder<zzaz, Api.ApiOptions.NoOptions> {
    zzad() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Api.Client client;
        Object obj2 = obj;
        GoogleApiClient.ConnectionCallbacks connectionCallbacks2 = connectionCallbacks;
        Context context2 = context;
        new zzaz(context2, looper, connectionCallbacks2, onConnectionFailedListener, "locationServices", clientSettings);
        return client;
    }
}
