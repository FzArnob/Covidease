package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

public final class Common {
    @KeepForSdk
    public static final Api<Api.ApiOptions.NoOptions> API;
    @KeepForSdk
    public static final Api.ClientKey<zai> CLIENT_KEY;
    private static final Api.AbstractClientBuilder<zai, Api.ApiOptions.NoOptions> zaph;
    public static final zac zapi;

    public Common() {
    }

    static {
        Api.ClientKey<zai> clientKey;
        Api.AbstractClientBuilder<zai, Api.ApiOptions.NoOptions> abstractClientBuilder;
        Api<Api.ApiOptions.NoOptions> api;
        zac zac;
        new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        new zab();
        zaph = abstractClientBuilder;
        new Api<>("Common.API", zaph, CLIENT_KEY);
        API = api;
        new zad();
        zapi = zac;
    }
}
