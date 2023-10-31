package com.google.android.gms.signin;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.internal.SignInClientImpl;

final class zab extends Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> {
    zab() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Api.Client client;
        GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener2 = onConnectionFailedListener;
        GoogleApiClient.ConnectionCallbacks connectionCallbacks2 = connectionCallbacks;
        SignInOptions signInOptions = (SignInOptions) obj;
        ClientSettings clientSettings2 = clientSettings;
        Looper looper2 = looper;
        Context context2 = context;
        if (signInOptions == null) {
            signInOptions = SignInOptions.DEFAULT;
        }
        new SignInClientImpl(context2, looper2, true, clientSettings2, signInOptions, connectionCallbacks2, onConnectionFailedListener2);
        return client;
    }
}
