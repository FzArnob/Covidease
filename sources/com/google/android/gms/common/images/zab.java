package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.Objects;

final class zab {
    public final Uri uri;

    public zab(Uri uri2) {
        this.uri = uri2;
    }

    public final int hashCode() {
        return Objects.hashCode(this.uri);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r1
            boolean r2 = r2 instanceof com.google.android.gms.common.images.zab
            if (r2 != 0) goto L_0x000a
            r2 = 0
            r0 = r2
        L_0x0009:
            return r0
        L_0x000a:
            r2 = r0
            r3 = r1
            if (r2 != r3) goto L_0x0011
            r2 = 1
            r0 = r2
            goto L_0x0009
        L_0x0011:
            r2 = r1
            com.google.android.gms.common.images.zab r2 = (com.google.android.gms.common.images.zab) r2
            android.net.Uri r2 = r2.uri
            r3 = r0
            android.net.Uri r3 = r3.uri
            boolean r2 = com.google.android.gms.common.internal.Objects.equal(r2, r3)
            r0 = r2
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.zab.equals(java.lang.Object):boolean");
    }
}
