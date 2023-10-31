package com.google.android.gms.signin;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.signin.internal.SignInClientImpl;

public final class zaa {
    public static final Api<SignInOptions> API;
    private static final Api.ClientKey<SignInClientImpl> CLIENT_KEY;
    public static final Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> zaph;
    private static final Scope zar;
    @ShowFirstParty
    private static final Api.ClientKey<SignInClientImpl> zars;
    private static final Api.AbstractClientBuilder<SignInClientImpl, Object> zart;
    private static final Api<Object> zaru;
    private static final Scope zas;

    static {
        Api.ClientKey<SignInClientImpl> clientKey;
        Api.ClientKey<SignInClientImpl> clientKey2;
        Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> abstractClientBuilder;
        Api.AbstractClientBuilder<SignInClientImpl, Object> abstractClientBuilder2;
        Scope scope;
        Scope scope2;
        Api<SignInOptions> api;
        Api<Object> api2;
        new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        new Api.ClientKey<>();
        zars = clientKey2;
        new zab();
        zaph = abstractClientBuilder;
        new zac();
        zart = abstractClientBuilder2;
        new Scope(Scopes.PROFILE);
        zar = scope;
        new Scope("email");
        zas = scope2;
        new Api<>("SignIn.API", zaph, CLIENT_KEY);
        API = api;
        new Api<>("SignIn.INTERNAL_API", zart, zars);
        zaru = api2;
    }
}
