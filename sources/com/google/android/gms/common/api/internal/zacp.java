package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zacp {
    public static final Status zakx;
    private static final BasePendingResult<?>[] zaky = new BasePendingResult[0];
    private final Map<Api.AnyClientKey<?>, Api.Client> zagz;
    @VisibleForTesting
    final Set<BasePendingResult<?>> zakz;
    private final zacs zala;

    public zacp(Map<Api.AnyClientKey<?>, Api.Client> map) {
        Map map2;
        zacs zacs;
        new WeakHashMap();
        this.zakz = Collections.synchronizedSet(Collections.newSetFromMap(map2));
        new zacq(this);
        this.zala = zacs;
        this.zagz = map;
    }

    /* access modifiers changed from: package-private */
    public final void zab(BasePendingResult<? extends Result> basePendingResult) {
        BasePendingResult<? extends Result> basePendingResult2 = basePendingResult;
        boolean add = this.zakz.add(basePendingResult2);
        basePendingResult2.zaa(this.zala);
    }

    /* JADX WARNING: type inference failed for: r10v18, types: [android.os.IBinder$DeathRecipient] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void release() {
        /*
            r16 = this;
            r0 = r16
            r9 = r0
            java.util.Set<com.google.android.gms.common.api.internal.BasePendingResult<?>> r9 = r9.zakz
            com.google.android.gms.common.api.internal.BasePendingResult<?>[] r10 = zaky
            java.lang.Object[] r9 = r9.toArray(r10)
            com.google.android.gms.common.api.internal.BasePendingResult[] r9 = (com.google.android.gms.common.api.internal.BasePendingResult[]) r9
            r15 = r9
            r9 = r15
            r10 = r15
            r1 = r10
            int r9 = r9.length
            r2 = r9
            r9 = 0
            r3 = r9
        L_0x0015:
            r9 = r3
            r10 = r2
            if (r9 >= r10) goto L_0x00cd
            r9 = r1
            r10 = r3
            r9 = r9[r10]
            r15 = r9
            r9 = r15
            r10 = r15
            r4 = r10
            r10 = 0
            r9.zaa((com.google.android.gms.common.api.internal.zacs) r10)
            r9 = r4
            java.lang.Integer r9 = r9.zam()
            if (r9 != 0) goto L_0x003e
            r9 = r4
            boolean r9 = r9.zat()
            if (r9 == 0) goto L_0x003b
            r9 = r0
            java.util.Set<com.google.android.gms.common.api.internal.BasePendingResult<?>> r9 = r9.zakz
            r10 = r4
            boolean r9 = r9.remove(r10)
        L_0x003b:
            int r3 = r3 + 1
            goto L_0x0015
        L_0x003e:
            r9 = r4
            r10 = 0
            r9.setResultCallback(r10)
            r9 = r0
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.api.Api$Client> r9 = r9.zagz
            r10 = r4
            com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl r10 = (com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl) r10
            com.google.android.gms.common.api.Api$AnyClientKey r10 = r10.getClientKey()
            java.lang.Object r9 = r9.get(r10)
            com.google.android.gms.common.api.Api$Client r9 = (com.google.android.gms.common.api.Api.Client) r9
            android.os.IBinder r9 = r9.getServiceBrokerBinder()
            r5 = r9
            r9 = r4
            r10 = r5
            r7 = r10
            r15 = r9
            r9 = r15
            r10 = r15
            r6 = r10
            boolean r9 = r9.isReady()
            if (r9 == 0) goto L_0x0080
            com.google.android.gms.common.api.internal.zacr r9 = new com.google.android.gms.common.api.internal.zacr
            r15 = r9
            r9 = r15
            r10 = r15
            r11 = r6
            r12 = 0
            r13 = r7
            r14 = 0
            r10.<init>(r11, r12, r13, r14)
            r8 = r9
            r9 = r6
            r10 = r8
            r9.zaa((com.google.android.gms.common.api.internal.zacs) r10)
        L_0x0077:
            r9 = r0
            java.util.Set<com.google.android.gms.common.api.internal.BasePendingResult<?>> r9 = r9.zakz
            r10 = r4
            boolean r9 = r9.remove(r10)
            goto L_0x003b
        L_0x0080:
            r9 = r7
            if (r9 == 0) goto L_0x00b6
            r9 = r7
            boolean r9 = r9.isBinderAlive()
            if (r9 == 0) goto L_0x00b6
            com.google.android.gms.common.api.internal.zacr r9 = new com.google.android.gms.common.api.internal.zacr
            r15 = r9
            r9 = r15
            r10 = r15
            r11 = r6
            r12 = 0
            r13 = r7
            r14 = 0
            r10.<init>(r11, r12, r13, r14)
            r8 = r9
            r9 = r6
            r10 = r8
            r9.zaa((com.google.android.gms.common.api.internal.zacs) r10)
            r9 = r7
            r10 = r8
            r11 = 0
            r9.linkToDeath(r10, r11)     // Catch:{ RemoteException -> 0x00a3 }
            goto L_0x0077
        L_0x00a3:
            r9 = move-exception
            r9 = r6
            r9.cancel()
            r9 = 0
            r10 = r6
            java.lang.Integer r10 = r10.zam()
            int r10 = r10.intValue()
            r9.remove(r10)
            goto L_0x0077
        L_0x00b6:
            r9 = r6
            r10 = 0
            r9.zaa((com.google.android.gms.common.api.internal.zacs) r10)
            r9 = r6
            r9.cancel()
            r9 = 0
            r10 = r6
            java.lang.Integer r10 = r10.zam()
            int r10 = r10.intValue()
            r9.remove(r10)
            goto L_0x0077
        L_0x00cd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zacp.release():void");
    }

    public final void zabx() {
        BasePendingResult[] basePendingResultArr = (BasePendingResult[]) this.zakz.toArray(zaky);
        BasePendingResult[] basePendingResultArr2 = basePendingResultArr;
        int length = basePendingResultArr.length;
        for (int i = 0; i < length; i++) {
            basePendingResultArr2[i].zab(zakx);
        }
    }

    static {
        Status status;
        new Status(8, "The connection to Google Play services was lost");
        zakx = status;
    }
}
