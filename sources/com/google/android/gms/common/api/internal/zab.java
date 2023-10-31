package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.util.PlatformVersion;

public abstract class zab {
    private final int type;

    public zab(int i) {
        this.type = i;
    }

    public abstract void zaa(@NonNull Status status);

    public abstract void zaa(GoogleApiManager.zaa<?> zaa) throws DeadObjectException;

    public abstract void zaa(@NonNull zaab zaab, boolean z);

    public abstract void zaa(@NonNull RuntimeException runtimeException);

    /* access modifiers changed from: private */
    public static Status zaa(RemoteException remoteException) {
        StringBuilder sb;
        Status status;
        RemoteException remoteException2 = remoteException;
        new StringBuilder();
        StringBuilder sb2 = sb;
        if (PlatformVersion.isAtLeastIceCreamSandwichMR1() && (remoteException2 instanceof TransactionTooLargeException)) {
            StringBuilder append = sb2.append("TransactionTooLargeException: ");
        }
        StringBuilder append2 = sb2.append(remoteException2.getLocalizedMessage());
        new Status(8, sb2.toString());
        return status;
    }
}
