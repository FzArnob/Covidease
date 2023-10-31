package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zaj;
import com.google.android.gms.signin.zaa;
import com.google.android.gms.signin.zad;
import java.util.Set;

public final class zace extends zac implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static Api.AbstractClientBuilder<? extends zad, SignInOptions> zaki = zaa.zaph;
    private final Context mContext;
    private final Handler mHandler;
    private Set<Scope> mScopes;
    private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zaau;
    private ClientSettings zaet;
    private zad zagb;
    /* access modifiers changed from: private */
    public zach zakj;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @WorkerThread
    public zace(Context context, Handler handler, @NonNull ClientSettings clientSettings) {
        this(context, handler, clientSettings, zaki);
    }

    @WorkerThread
    public zace(Context context, Handler handler, @NonNull ClientSettings clientSettings, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder) {
        ClientSettings clientSettings2 = clientSettings;
        this.mContext = context;
        this.mHandler = handler;
        this.zaet = (ClientSettings) Preconditions.checkNotNull(clientSettings2, "ClientSettings must not be null");
        this.mScopes = clientSettings2.getRequiredScopes();
        this.zaau = abstractClientBuilder;
    }

    @WorkerThread
    public final void zaa(zach zach) {
        Runnable runnable;
        zach zach2 = zach;
        if (this.zagb != null) {
            this.zagb.disconnect();
        }
        this.zaet.setClientSessionId(Integer.valueOf(System.identityHashCode(this)));
        this.zagb = (zad) this.zaau.buildClient(this.mContext, this.mHandler.getLooper(), this.zaet, this.zaet.getSignInOptions(), this, this);
        this.zakj = zach2;
        if (this.mScopes == null || this.mScopes.isEmpty()) {
            new zacf(this);
            boolean post = this.mHandler.post(runnable);
            return;
        }
        this.zagb.connect();
    }

    public final zad zabq() {
        return this.zagb;
    }

    public final void zabs() {
        if (this.zagb != null) {
            this.zagb.disconnect();
        }
    }

    @WorkerThread
    public final void onConnected(@Nullable Bundle bundle) {
        Bundle bundle2 = bundle;
        this.zagb.zaa(this);
    }

    @WorkerThread
    public final void onConnectionSuspended(int i) {
        int i2 = i;
        this.zagb.disconnect();
    }

    @WorkerThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zakj.zag(connectionResult);
    }

    @BinderThread
    public final void zab(zaj zaj) {
        Runnable runnable;
        new zacg(this, zaj);
        boolean post = this.mHandler.post(runnable);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zac(zaj zaj) {
        StringBuilder sb;
        Throwable th;
        zaj zaj2 = zaj;
        ConnectionResult connectionResult = zaj2.getConnectionResult();
        ConnectionResult connectionResult2 = connectionResult;
        if (connectionResult.isSuccess()) {
            ResolveAccountResponse zacx = zaj2.zacx();
            ResolveAccountResponse resolveAccountResponse = zacx;
            ConnectionResult connectionResult3 = zacx.getConnectionResult();
            ConnectionResult connectionResult4 = connectionResult3;
            if (!connectionResult3.isSuccess()) {
                String valueOf = String.valueOf(connectionResult4);
                new StringBuilder(48 + String.valueOf(valueOf).length());
                new Exception();
                int wtf = Log.wtf("SignInCoordinator", sb.append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), th);
                this.zakj.zag(connectionResult4);
                this.zagb.disconnect();
                return;
            }
            this.zakj.zaa(resolveAccountResponse.getAccountAccessor(), this.mScopes);
        } else {
            this.zakj.zag(connectionResult2);
        }
        this.zagb.disconnect();
    }
}
