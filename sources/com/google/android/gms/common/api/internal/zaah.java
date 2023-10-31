package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;

public final class zaah implements zabd {
    /* access modifiers changed from: private */
    public final zabe zaft;
    private boolean zafu = false;

    public zaah(zabe zabe) {
        this.zaft = zabe;
    }

    public final void begin() {
    }

    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        return execute(t);
    }

    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        zabf zabf;
        Api.AnyClient anyClient;
        Status status;
        T t2 = t;
        T t3 = t2;
        try {
            this.zaft.zaee.zahf.zab(t3);
            Api.AnyClient anyClient2 = (Api.Client) this.zaft.zaee.zagz.get(t3.getClientKey());
            Object checkNotNull = Preconditions.checkNotNull(anyClient2, "Appropriate Api was not requested.");
            Api.AnyClient anyClient3 = anyClient2;
            Api.AnyClient anyClient4 = anyClient3;
            if (anyClient3.isConnected() || !this.zaft.zahp.containsKey(t3.getClientKey())) {
                if (anyClient4 instanceof SimpleClientAdapter) {
                    anyClient = ((SimpleClientAdapter) anyClient4).getClient();
                } else {
                    anyClient = anyClient4;
                }
                t3.run(anyClient);
                return t2;
            }
            new Status(17);
            t3.setFailedResult(status);
            return t2;
        } catch (DeadObjectException e) {
            new zaai(this, this);
            this.zaft.zaa(zabf);
        }
    }

    public final boolean disconnect() {
        if (this.zafu) {
            return false;
        }
        if (this.zaft.zaee.zaax()) {
            this.zafu = true;
            for (zacm zabv : this.zaft.zaee.zahe) {
                zabv.zabv();
            }
            return false;
        }
        this.zaft.zaf((ConnectionResult) null);
        return true;
    }

    public final void connect() {
        zabf zabf;
        if (this.zafu) {
            this.zafu = false;
            new zaaj(this, this);
            this.zaft.zaa(zabf);
        }
    }

    public final void onConnected(Bundle bundle) {
    }

    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    public final void onConnectionSuspended(int i) {
        this.zaft.zaf((ConnectionResult) null);
        this.zaft.zaht.zab(i, this.zafu);
    }

    /* access modifiers changed from: package-private */
    public final void zaam() {
        if (this.zafu) {
            this.zafu = false;
            this.zaft.zaee.zahf.release();
            boolean disconnect = disconnect();
        }
    }
}
