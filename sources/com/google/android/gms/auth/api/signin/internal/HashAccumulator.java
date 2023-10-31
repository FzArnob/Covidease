package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;

public class HashAccumulator {
    @VisibleForTesting
    private static int zaah = 31;
    private int zaai = 1;

    public HashAccumulator() {
    }

    @KeepForSdk
    public HashAccumulator addObject(Object obj) {
        Object obj2 = obj;
        this.zaai = (zaah * this.zaai) + (obj2 == null ? 0 : obj2.hashCode());
        return this;
    }

    public final HashAccumulator zaa(boolean z) {
        this.zaai = (zaah * this.zaai) + (z ? 1 : 0);
        return this;
    }

    @KeepForSdk
    public int hash() {
        return this.zaai;
    }
}
