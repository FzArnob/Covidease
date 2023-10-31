package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zag<ResultT> extends zac {
    private final TaskCompletionSource<ResultT> zacn;
    private final TaskApiCall<Api.AnyClient, ResultT> zacr;
    private final StatusExceptionMapper zacs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zag(int i, TaskApiCall<Api.AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        super(i);
        this.zacn = taskCompletionSource;
        this.zacr = taskApiCall;
        this.zacs = statusExceptionMapper;
    }

    public final void zaa(GoogleApiManager.zaa<?> zaa) throws DeadObjectException {
        try {
            this.zacr.doExecute(zaa.zaab(), this.zacn);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e2) {
            zaa(zab.zaa(e2));
        } catch (RuntimeException e3) {
            zaa(e3);
        }
    }

    public final void zaa(@NonNull Status status) {
        boolean trySetException = this.zacn.trySetException(this.zacs.getException(status));
    }

    public final void zaa(@NonNull RuntimeException runtimeException) {
        boolean trySetException = this.zacn.trySetException(runtimeException);
    }

    public final void zaa(@NonNull zaab zaab, boolean z) {
        zaab.zaa(this.zacn, z);
    }

    @Nullable
    public final Feature[] zab(GoogleApiManager.zaa<?> zaa) {
        GoogleApiManager.zaa<?> zaa2 = zaa;
        return this.zacr.zabt();
    }

    public final boolean zac(GoogleApiManager.zaa<?> zaa) {
        GoogleApiManager.zaa<?> zaa2 = zaa;
        return this.zacr.shouldAutoResolveMissingFeatures();
    }
}
