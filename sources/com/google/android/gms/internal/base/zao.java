package com.google.android.gms.internal.base;

import android.support.annotation.NonNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

final class zao implements zal {
    private zao() {
    }

    @NonNull
    public final ExecutorService zaa(int i, ThreadFactory threadFactory, int i2) {
        int i3 = i;
        int i4 = i2;
        return Executors.newFixedThreadPool(2, threadFactory);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ zao(zan zan) {
        this();
        zan zan2 = zan;
    }
}
