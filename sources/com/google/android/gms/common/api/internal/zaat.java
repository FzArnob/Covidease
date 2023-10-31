package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.signin.internal.zad;

final class zaat implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private final /* synthetic */ zaak zagj;

    private zaat(zaak zaak) {
        this.zagj = zaak;
    }

    public final void onConnected(Bundle bundle) {
        zad zad;
        zad zad2;
        Bundle bundle2 = bundle;
        if (this.zagj.zaet.isSignInClientDisconnectFixEnabled()) {
            this.zagj.zaeo.lock();
            try {
                if (this.zagj.zagb == null) {
                    this.zagj.zaeo.unlock();
                    return;
                }
                new zaar(this.zagj);
                this.zagj.zagb.zaa(zad2);
                this.zagj.zaeo.unlock();
            } catch (Throwable th) {
                Throwable th2 = th;
                this.zagj.zaeo.unlock();
                throw th2;
            }
        } else {
            new zaar(this.zagj);
            this.zagj.zagb.zaa(zad);
        }
    }

    public final void onConnectionSuspended(int i) {
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        ConnectionResult connectionResult2 = connectionResult;
        this.zagj.zaeo.lock();
        try {
            if (this.zagj.zad(connectionResult2)) {
                this.zagj.zaar();
                this.zagj.zaap();
            } else {
                this.zagj.zae(connectionResult2);
            }
            this.zagj.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zagj.zaeo.unlock();
            throw th2;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ zaat(zaak zaak, zaal zaal) {
        this(zaak);
        zaal zaal2 = zaal;
    }
}
