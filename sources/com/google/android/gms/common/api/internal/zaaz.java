package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zaaz implements GoogleApiClient.OnConnectionFailedListener {
    private final /* synthetic */ StatusPendingResult zahj;

    zaaz(zaaw zaaw, StatusPendingResult statusPendingResult) {
        zaaw zaaw2 = zaaw;
        this.zahj = statusPendingResult;
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Result result;
        ConnectionResult connectionResult2 = connectionResult;
        new Status(8);
        this.zahj.setResult(result);
    }
}
