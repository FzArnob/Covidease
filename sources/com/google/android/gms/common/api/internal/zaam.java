package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

final class zaam implements BaseGmsClient.ConnectionProgressReportCallbacks {
    private final Api<?> mApi;
    /* access modifiers changed from: private */
    public final boolean zaec;
    private final WeakReference<zaak> zagk;

    public zaam(zaak zaak, Api<?> api, boolean z) {
        WeakReference<zaak> weakReference;
        new WeakReference<>(zaak);
        this.zagk = weakReference;
        this.mApi = api;
        this.zaec = z;
    }

    public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
        ConnectionResult connectionResult2 = connectionResult;
        zaak zaak = (zaak) this.zagk.get();
        zaak zaak2 = zaak;
        if (zaak != null) {
            Preconditions.checkState(Looper.myLooper() == zaak2.zaft.zaee.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            zaak2.zaeo.lock();
            try {
                if (!zaak.zaa(zaak2, 0)) {
                    zaak2.zaeo.unlock();
                    return;
                }
                if (!connectionResult2.isSuccess()) {
                    zaak2.zab(connectionResult2, this.mApi, this.zaec);
                }
                if (zaak2.zaao()) {
                    zaak2.zaap();
                }
                zaak2.zaeo.unlock();
            } catch (Throwable th) {
                Throwable th2 = th;
                zaak2.zaeo.unlock();
                throw th2;
            }
        }
    }
}
