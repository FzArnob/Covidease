package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

public final class zad implements zac {
    public zad() {
    }

    public final PendingResult<Status> zaa(GoogleApiClient googleApiClient) {
        BaseImplementation.ApiMethodImpl apiMethodImpl;
        GoogleApiClient googleApiClient2 = googleApiClient;
        new zae(this, googleApiClient2);
        return googleApiClient2.execute(apiMethodImpl);
    }
}
