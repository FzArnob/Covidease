package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

final class zaan extends zaau {
    final /* synthetic */ zaak zagj;
    private final Map<Api.Client, zaam> zagl;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zaan(com.google.android.gms.common.api.internal.zaak r7, java.util.Map<com.google.android.gms.common.api.Api.Client, com.google.android.gms.common.api.internal.zaam> r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r3.zagj = r4
            r3 = r0
            r4 = r1
            r5 = 0
            r3.<init>(r4, r5)
            r3 = r0
            r4 = r2
            r3.zagl = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zaan.<init>(com.google.android.gms.common.api.internal.zaak, java.util.Map):void");
    }

    @WorkerThread
    @GuardedBy("mLock")
    public final void zaan() {
        GoogleApiAvailabilityCache googleApiAvailabilityCache;
        ArrayList arrayList;
        ArrayList arrayList2;
        zabf zabf;
        ConnectionResult connectionResult;
        zabf zabf2;
        new GoogleApiAvailabilityCache(this.zagj.zaey);
        GoogleApiAvailabilityCache googleApiAvailabilityCache2 = googleApiAvailabilityCache;
        new ArrayList();
        ArrayList arrayList3 = arrayList;
        new ArrayList();
        ArrayList arrayList4 = arrayList2;
        for (Api.Client next : this.zagl.keySet()) {
            Api.Client client = next;
            if (!next.requiresGooglePlayServices() || this.zagl.get(client).zaec) {
                boolean add = arrayList4.add(client);
            } else {
                boolean add2 = arrayList3.add(client);
            }
        }
        int i = -1;
        if (!arrayList3.isEmpty()) {
            ArrayList arrayList5 = arrayList3;
            ArrayList arrayList6 = arrayList5;
            int size = arrayList5.size();
            int i2 = 0;
            while (i2 < size) {
                i2++;
                int clientAvailability = googleApiAvailabilityCache2.getClientAvailability(this.zagj.mContext, (Api.Client) arrayList6.get(i2));
                i = clientAvailability;
                if (clientAvailability != 0) {
                    break;
                }
            }
        } else {
            ArrayList arrayList7 = arrayList4;
            ArrayList arrayList8 = arrayList7;
            int size2 = arrayList7.size();
            int i3 = 0;
            while (i3 < size2) {
                i3++;
                int clientAvailability2 = googleApiAvailabilityCache2.getClientAvailability(this.zagj.mContext, (Api.Client) arrayList8.get(i3));
                i = clientAvailability2;
                if (clientAvailability2 == 0) {
                    break;
                }
            }
        }
        if (i != 0) {
            new ConnectionResult(i, (PendingIntent) null);
            new zaao(this, this.zagj, connectionResult);
            this.zagj.zaft.zaa(zabf2);
            return;
        }
        if (this.zagj.zagd && this.zagj.zagb != null) {
            this.zagj.zagb.connect();
        }
        for (Api.Client next2 : this.zagl.keySet()) {
            BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks = this.zagl.get(next2);
            if (next2.requiresGooglePlayServices()) {
                if (googleApiAvailabilityCache2.getClientAvailability(this.zagj.mContext, next2) != 0) {
                    new zaap(this, this.zagj, connectionProgressReportCallbacks);
                    this.zagj.zaft.zaa(zabf);
                }
            }
            next2.connect(connectionProgressReportCallbacks);
        }
    }
}
