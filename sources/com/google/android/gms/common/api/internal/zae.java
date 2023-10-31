package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.GoogleApiManager;

public final class zae<A extends BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>> extends zab {
    private final A zaco;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zae(int i, A a) {
        super(i);
        this.zaco = a;
    }

    public final void zaa(GoogleApiManager.zaa<?> zaa) throws DeadObjectException {
        try {
            this.zaco.run(zaa.zaab());
        } catch (RuntimeException e) {
            zaa(e);
        }
    }

    public final void zaa(@NonNull Status status) {
        this.zaco.setFailedResult(status);
    }

    public final void zaa(@NonNull RuntimeException runtimeException) {
        Status status;
        StringBuilder sb;
        RuntimeException runtimeException2 = runtimeException;
        Status status2 = status;
        String simpleName = runtimeException2.getClass().getSimpleName();
        String localizedMessage = runtimeException2.getLocalizedMessage();
        new StringBuilder(2 + String.valueOf(simpleName).length() + String.valueOf(localizedMessage).length());
        new Status(10, sb.append(simpleName).append(": ").append(localizedMessage).toString());
        Status status3 = status2;
        this.zaco.setFailedResult(status3);
    }

    public final void zaa(@NonNull zaab zaab, boolean z) {
        zaab.zaa((BasePendingResult<? extends Result>) this.zaco, z);
    }
}
