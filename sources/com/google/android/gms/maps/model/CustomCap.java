package com.google.android.gms.maps.model;

import android.support.annotation.NonNull;

public final class CustomCap extends Cap {
    public final BitmapDescriptor bitmapDescriptor;
    public final float refWidth;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CustomCap(@android.support.annotation.NonNull com.google.android.gms.maps.model.BitmapDescriptor r11, float r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r4 = r0
            r5 = r1
            java.lang.String r6 = "bitmapDescriptor must not be null"
            java.lang.Object r5 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r5, r6)
            com.google.android.gms.maps.model.BitmapDescriptor r5 = (com.google.android.gms.maps.model.BitmapDescriptor) r5
            r6 = r2
            java.lang.String r7 = "refWidth must be positive"
            r3 = r7
            r7 = 0
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 > 0) goto L_0x0022
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r9 = r6
            r6 = r9
            r7 = r9
            r8 = r3
            r7.<init>(r8)
            throw r6
        L_0x0022:
            r6 = r2
            r4.<init>(r5, r6)
            r4 = r0
            r5 = r1
            r4.bitmapDescriptor = r5
            r4 = r0
            r5 = r2
            r4.refWidth = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.CustomCap.<init>(com.google.android.gms.maps.model.BitmapDescriptor, float):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CustomCap(@NonNull BitmapDescriptor bitmapDescriptor2) {
        this(bitmapDescriptor2, 10.0f);
    }

    public final String toString() {
        StringBuilder sb;
        String valueOf = String.valueOf(this.bitmapDescriptor);
        float f = this.refWidth;
        new StringBuilder(55 + String.valueOf(valueOf).length());
        return sb.append("[CustomCap: bitmapDescriptor=").append(valueOf).append(" refWidth=").append(f).append("]").toString();
    }
}
