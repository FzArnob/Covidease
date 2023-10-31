package com.google.android.gms.common.api;

import android.support.annotation.Nullable;

public class ApiException extends Exception {
    protected final Status mStatus;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ApiException(@android.support.annotation.NonNull com.google.android.gms.common.api.Status r11) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r4 = r0
            r5 = r1
            int r5 = r5.getStatusCode()
            r2 = r5
            r5 = r1
            java.lang.String r5 = r5.getStatusMessage()
            if (r5 == 0) goto L_0x004d
            r5 = r1
            java.lang.String r5 = r5.getStatusMessage()
        L_0x0015:
            r3 = r5
            r5 = 13
            r6 = r3
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r6 = r6.length()
            int r5 = r5 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r8 = r5
            r9 = r6
            r5 = r9
            r6 = r8
            r7 = r9
            r8 = r6
            r9 = r7
            r6 = r9
            r7 = r8
            r6.<init>(r7)
            r6 = r2
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = ": "
            java.lang.StringBuilder r5 = r5.append(r6)
            r6 = r3
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            r4 = r0
            r5 = r1
            r4.mStatus = r5
            return
        L_0x004d:
            java.lang.String r5 = ""
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.ApiException.<init>(com.google.android.gms.common.api.Status):void");
    }

    public int getStatusCode() {
        return this.mStatus.getStatusCode();
    }

    @Nullable
    @Deprecated
    public String getStatusMessage() {
        return this.mStatus.getStatusMessage();
    }
}
