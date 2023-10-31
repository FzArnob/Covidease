package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import java.util.Collections;

public final class zaav implements zabd {
    private final zabe zaft;

    public zaav(zabe zabe) {
        this.zaft = zabe;
    }

    public final void begin() {
        for (Api.Client disconnect : this.zaft.zagz.values()) {
            disconnect.disconnect();
        }
        this.zaft.zaee.zaha = Collections.emptySet();
    }

    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        T t2 = t;
        boolean add = this.zaft.zaee.zafc.add(t2);
        return t2;
    }

    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        Throwable th;
        T t2 = t;
        Throwable th2 = th;
        new IllegalStateException("GoogleApiClient is not connected yet.");
        throw th2;
    }

    public final boolean disconnect() {
        return true;
    }

    public final void connect() {
        this.zaft.zaaz();
    }

    public final void onConnected(Bundle bundle) {
    }

    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    public final void onConnectionSuspended(int i) {
    }
}
