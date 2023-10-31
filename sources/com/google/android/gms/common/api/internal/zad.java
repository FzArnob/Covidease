package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zad<T> extends zac {
    protected final TaskCompletionSource<T> zacn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zad(int i, TaskCompletionSource<T> taskCompletionSource) {
        super(i);
        this.zacn = taskCompletionSource;
    }

    /* access modifiers changed from: protected */
    public abstract void zad(GoogleApiManager.zaa<?> zaa) throws RemoteException;

    public void zaa(@NonNull Status status) {
        Exception exc;
        new ApiException(status);
        boolean trySetException = this.zacn.trySetException(exc);
    }

    public void zaa(@NonNull RuntimeException runtimeException) {
        boolean trySetException = this.zacn.trySetException(runtimeException);
    }

    public void zaa(@NonNull zaab zaab, boolean z) {
    }

    public final void zaa(GoogleApiManager.zaa<?> zaa) throws DeadObjectException {
        try {
            zad(zaa);
        } catch (DeadObjectException e) {
            DeadObjectException deadObjectException = e;
            zaa(zab.zaa((RemoteException) deadObjectException));
            throw deadObjectException;
        } catch (RemoteException e2) {
            zaa(zab.zaa(e2));
        } catch (RuntimeException e3) {
            zaa(e3);
        }
    }
}
