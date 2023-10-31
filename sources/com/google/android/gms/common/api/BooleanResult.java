package com.google.android.gms.common.api;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;

@KeepForSdk
public class BooleanResult implements Result {
    private final Status mStatus;
    private final boolean zabg;

    @ShowFirstParty
    @KeepForSdk
    public BooleanResult(Status status, boolean z) {
        this.mStatus = (Status) Preconditions.checkNotNull(status, "Status must not be null");
        this.zabg = z;
    }

    @KeepForSdk
    public Status getStatus() {
        return this.mStatus;
    }

    @KeepForSdk
    public boolean getValue() {
        return this.zabg;
    }

    @KeepForSdk
    public final int hashCode() {
        int hashCode = 527 + this.mStatus.hashCode();
        int i = hashCode;
        return (hashCode * 31) + (this.zabg ? 1 : 0);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r1
            r4 = r0
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            boolean r3 = r3 instanceof com.google.android.gms.common.api.BooleanResult
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.common.api.BooleanResult r3 = (com.google.android.gms.common.api.BooleanResult) r3
            r2 = r3
            r3 = r0
            com.google.android.gms.common.api.Status r3 = r3.mStatus
            r4 = r2
            com.google.android.gms.common.api.Status r4 = r4.mStatus
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x002c
            r3 = r0
            boolean r3 = r3.zabg
            r4 = r2
            boolean r4 = r4.zabg
            if (r3 != r4) goto L_0x002c
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x002c:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.BooleanResult.equals(java.lang.Object):boolean");
    }
}
