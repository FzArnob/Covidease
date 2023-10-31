package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

final class zae extends zah {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zae(zad zad, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        zad zad2 = zad;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zaj zaj;
        new zaf(this);
        ((zal) ((zai) anyClient).getService()).zaa(zaj);
    }
}
