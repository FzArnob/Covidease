package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.Objects;

public final class zai<O extends Api.ApiOptions> {
    private final Api<O> mApi;
    private final O zabh;
    private final boolean zacu = true;
    private final int zacv;

    private zai(Api<O> api, O o) {
        this.mApi = api;
        this.zabh = o;
        Object[] objArr = new Object[2];
        objArr[0] = this.mApi;
        Object[] objArr2 = objArr;
        objArr2[1] = this.zabh;
        this.zacv = Objects.hashCode(objArr2);
    }

    private zai(Api<O> api) {
        this.mApi = api;
        this.zabh = null;
        this.zacv = System.identityHashCode(this);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <O extends com.google.android.gms.common.api.Api.ApiOptions> com.google.android.gms.common.api.internal.zai<O> zaa(com.google.android.gms.common.api.Api<O> r7, O r8) {
        /*
            r0 = r7
            r1 = r8
            com.google.android.gms.common.api.internal.zai r2 = new com.google.android.gms.common.api.internal.zai
            r6 = r2
            r2 = r6
            r3 = r6
            r4 = r0
            r5 = r1
            r3.<init>(r4, r5)
            r0 = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zai.zaa(com.google.android.gms.common.api.Api, com.google.android.gms.common.api.Api$ApiOptions):com.google.android.gms.common.api.internal.zai");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <O extends com.google.android.gms.common.api.Api.ApiOptions> com.google.android.gms.common.api.internal.zai<O> zaa(com.google.android.gms.common.api.Api<O> r5) {
        /*
            r0 = r5
            com.google.android.gms.common.api.internal.zai r1 = new com.google.android.gms.common.api.internal.zai
            r4 = r1
            r1 = r4
            r2 = r4
            r3 = r0
            r2.<init>(r3)
            r0 = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zai.zaa(com.google.android.gms.common.api.Api):com.google.android.gms.common.api.internal.zai");
    }

    public final String zan() {
        return this.mApi.getName();
    }

    public final int hashCode() {
        return this.zacv;
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
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
            boolean r3 = r3 instanceof com.google.android.gms.common.api.internal.zai
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.common.api.internal.zai r3 = (com.google.android.gms.common.api.internal.zai) r3
            r2 = r3
            r3 = r0
            boolean r3 = r3.zacu
            if (r3 != 0) goto L_0x003a
            r3 = r2
            boolean r3 = r3.zacu
            if (r3 != 0) goto L_0x003a
            r3 = r0
            com.google.android.gms.common.api.Api<O> r3 = r3.mApi
            r4 = r2
            com.google.android.gms.common.api.Api<O> r4 = r4.mApi
            boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
            if (r3 == 0) goto L_0x003a
            r3 = r0
            O r3 = r3.zabh
            r4 = r2
            O r4 = r4.zabh
            boolean r3 = com.google.android.gms.common.internal.Objects.equal(r3, r4)
            if (r3 == 0) goto L_0x003a
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x003a:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zai.equals(java.lang.Object):boolean");
    }
}
