package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

abstract class zag<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zai> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zag(GoogleApiClient googleApiClient) {
        super((Api<?>) Common.API, googleApiClient);
    }
}
