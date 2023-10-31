package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

final class zau implements zabt {
    private final /* synthetic */ zas zaeq;

    private zau(zas zas) {
        this.zaeq = zas;
    }

    public final void zab(@Nullable Bundle bundle) {
        Bundle bundle2 = bundle;
        this.zaeq.zaeo.lock();
        try {
            this.zaeq.zaa(bundle2);
            ConnectionResult zaa = zas.zaa(this.zaeq, ConnectionResult.RESULT_SUCCESS);
            this.zaeq.zax();
            this.zaeq.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeq.zaeo.unlock();
            throw th2;
        }
    }

    public final void zac(@NonNull ConnectionResult connectionResult) {
        ConnectionResult connectionResult2 = connectionResult;
        this.zaeq.zaeo.lock();
        try {
            ConnectionResult zaa = zas.zaa(this.zaeq, connectionResult2);
            this.zaeq.zax();
            this.zaeq.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeq.zaeo.unlock();
            throw th2;
        }
    }

    public final void zab(int i, boolean z) {
        int i2 = i;
        boolean z2 = z;
        this.zaeq.zaeo.lock();
        try {
            if (this.zaeq.zaen || this.zaeq.zaem == null || !this.zaeq.zaem.isSuccess()) {
                boolean zaa = zas.zaa(this.zaeq, false);
                this.zaeq.zaa(i2, z2);
                this.zaeq.zaeo.unlock();
                return;
            }
            boolean zaa2 = zas.zaa(this.zaeq, true);
            this.zaeq.zaeg.onConnectionSuspended(i2);
            this.zaeq.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeq.zaeo.unlock();
            throw th2;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ zau(zas zas, zat zat) {
        this(zas);
        zat zat2 = zat;
    }
}
