package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

final class zacn implements Runnable {
    private final /* synthetic */ Result zakv;
    private final /* synthetic */ zacm zakw;

    zacn(zacm zacm, Result result) {
        this.zakw = zacm;
        this.zakv = result;
    }

    @WorkerThread
    public final void run() {
        try {
            BasePendingResult.zadn.set(true);
            boolean sendMessage = this.zakw.zakt.sendMessage(this.zakw.zakt.obtainMessage(0, this.zakw.zako.onSuccess(this.zakv)));
            BasePendingResult.zadn.set(false);
            zacm.zaa(this.zakw, this.zakv);
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zakw.zadq.get();
            GoogleApiClient googleApiClient2 = googleApiClient;
            if (googleApiClient != null) {
                googleApiClient2.zab(this.zakw);
            }
        } catch (RuntimeException e) {
            boolean sendMessage2 = this.zakw.zakt.sendMessage(this.zakw.zakt.obtainMessage(1, e));
            BasePendingResult.zadn.set(false);
            zacm.zaa(this.zakw, this.zakv);
            GoogleApiClient googleApiClient3 = (GoogleApiClient) this.zakw.zadq.get();
            GoogleApiClient googleApiClient4 = googleApiClient3;
            if (googleApiClient3 != null) {
                googleApiClient4.zab(this.zakw);
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            BasePendingResult.zadn.set(false);
            zacm.zaa(this.zakw, this.zakv);
            GoogleApiClient googleApiClient5 = (GoogleApiClient) this.zakw.zadq.get();
            GoogleApiClient googleApiClient6 = googleApiClient5;
            if (googleApiClient5 != null) {
                googleApiClient6.zab(this.zakw);
            }
            throw th2;
        }
    }
}
