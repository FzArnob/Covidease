package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Collections;

final class zabo implements Runnable {
    private final /* synthetic */ ConnectionResult zaiz;
    private final /* synthetic */ GoogleApiManager.zac zajg;

    zabo(GoogleApiManager.zac zac, ConnectionResult connectionResult) {
        this.zajg = zac;
        this.zaiz = connectionResult;
    }

    public final void run() {
        ConnectionResult connectionResult;
        if (this.zaiz.isSuccess()) {
            boolean zaa = GoogleApiManager.zac.zaa(this.zajg, true);
            if (this.zajg.zaio.requiresSignIn()) {
                this.zajg.zabr();
                return;
            }
            try {
                this.zajg.zaio.getRemoteService((IAccountAccessor) null, Collections.emptySet());
            } catch (SecurityException e) {
                int e2 = Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
                new ConnectionResult(10);
                ((GoogleApiManager.zaa) this.zajg.zaim.zaii.get(this.zajg.zafq)).onConnectionFailed(connectionResult);
            }
        } else {
            ((GoogleApiManager.zaa) this.zajg.zaim.zaii.get(this.zajg.zafq)).onConnectionFailed(this.zaiz);
        }
    }
}
