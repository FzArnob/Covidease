package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

final class zaaq extends zaau {
    private final /* synthetic */ zaak zagj;
    private final ArrayList<Api.Client> zagp;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zaaq(com.google.android.gms.common.api.internal.zaak r7, java.util.ArrayList<com.google.android.gms.common.api.Api.Client> r8) {
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
            r3.zagp = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zaaq.<init>(com.google.android.gms.common.api.internal.zaak, java.util.ArrayList):void");
    }

    @WorkerThread
    public final void zaan() {
        this.zagj.zaft.zaee.zaha = this.zagj.zaat();
        ArrayList arrayList = this.zagp;
        ArrayList arrayList2 = arrayList;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            i++;
            ((Api.Client) arrayList2.get(i)).getRemoteService(this.zagj.zagf, this.zagj.zaft.zaee.zaha);
        }
    }
}
