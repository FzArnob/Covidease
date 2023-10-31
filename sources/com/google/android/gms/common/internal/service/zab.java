package com.google.android.gms.common.internal.service;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

final class zab extends Api.AbstractClientBuilder<zai, Api.ApiOptions.NoOptions> {
    zab() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Api.Client client;
        Object obj2 = obj;
        GoogleApiClient.ConnectionCallbacks connectionCallbacks2 = connectionCallbacks;
        ClientSettings clientSettings2 = clientSettings;
        Context context2 = context;
        new zai(context2, looper, clientSettings2, connectionCallbacks2, onConnectionFailedListener);
        return client;
    }
}
