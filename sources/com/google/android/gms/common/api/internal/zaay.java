package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.concurrent.atomic.AtomicReference;

final class zaay implements GoogleApiClient.ConnectionCallbacks {
    private final /* synthetic */ zaaw zahh;
    private final /* synthetic */ AtomicReference zahi;
    private final /* synthetic */ StatusPendingResult zahj;

    zaay(zaaw zaaw, AtomicReference atomicReference, StatusPendingResult statusPendingResult) {
        this.zahh = zaaw;
        this.zahi = atomicReference;
        this.zahj = statusPendingResult;
    }

    public final void onConnected(Bundle bundle) {
        Bundle bundle2 = bundle;
        zaaw.zaa(this.zahh, (GoogleApiClient) this.zahi.get(), this.zahj, true);
    }

    public final void onConnectionSuspended(int i) {
    }
}
