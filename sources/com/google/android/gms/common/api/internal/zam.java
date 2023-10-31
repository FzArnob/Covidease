package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

final class zam {
    private final int zadh;
    private final ConnectionResult zadi;

    zam(ConnectionResult connectionResult, int i) {
        ConnectionResult connectionResult2 = connectionResult;
        Object checkNotNull = Preconditions.checkNotNull(connectionResult2);
        this.zadi = connectionResult2;
        this.zadh = i;
    }

    /* access modifiers changed from: package-private */
    public final int zar() {
        return this.zadh;
    }

    /* access modifiers changed from: package-private */
    public final ConnectionResult getConnectionResult() {
        return this.zadi;
    }
}
