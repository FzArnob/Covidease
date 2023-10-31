package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import javax.annotation.concurrent.GuardedBy;

final class zaap extends zabf {
    private final /* synthetic */ BaseGmsClient.ConnectionProgressReportCallbacks zago;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zaap(zaan zaan, zabd zabd, BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        super(zabd);
        zaan zaan2 = zaan;
        this.zago = connectionProgressReportCallbacks;
    }

    @GuardedBy("mLock")
    public final void zaan() {
        ConnectionResult connectionResult;
        new ConnectionResult(16, (PendingIntent) null);
        this.zago.onReportServiceBinding(connectionResult);
    }
}
