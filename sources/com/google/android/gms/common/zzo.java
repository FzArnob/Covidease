package com.google.android.gms.common;

import java.util.concurrent.Callable;

final class zzo extends zzm {
    private final Callable<String> zzaf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzo(Callable<String> callable) {
        super(false, (String) null, (Throwable) null);
        this.zzaf = callable;
    }

    /* access modifiers changed from: package-private */
    public final String getErrorMessage() {
        Throwable th;
        try {
            return this.zzaf.call();
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new RuntimeException(exc);
            throw th2;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ zzo(Callable callable, zzn zzn) {
        this(callable);
        zzn zzn2 = zzn;
    }
}
