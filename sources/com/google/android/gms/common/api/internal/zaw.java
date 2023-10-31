package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;

public final class zaw<O extends Api.ApiOptions> extends GoogleApi<O> {
    private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
    private final Api.Client zaer;
    private final zaq zaes;
    private final ClientSettings zaet;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zaw(@NonNull Context context, Api<O> api, Looper looper, @NonNull Api.Client client, @NonNull zaq zaq, ClientSettings clientSettings, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder) {
        super(context, api, looper);
        this.zaer = client;
        this.zaes = zaq;
        this.zaet = clientSettings;
        this.zace = abstractClientBuilder;
        this.zabm.zaa((GoogleApi<?>) this);
    }

    public final Api.Client zaab() {
        return this.zaer;
    }

    public final Api.Client zaa(Looper looper, GoogleApiManager.zaa<O> zaa) {
        Looper looper2 = looper;
        this.zaes.zaa(zaa);
        return this.zaer;
    }

    public final zace zaa(Context context, Handler handler) {
        zace zace2;
        new zace(context, handler, this.zaet, this.zace);
        return zace2;
    }
}
