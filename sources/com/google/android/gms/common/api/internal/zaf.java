package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zaf extends zad<Void> {
    private final RegisterListenerMethod<Api.AnyClient, ?> zacp;
    private final UnregisterListenerMethod<Api.AnyClient, ?> zacq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zaf(zabw zabw, TaskCompletionSource<Void> taskCompletionSource) {
        super(3, taskCompletionSource);
        zabw zabw2 = zabw;
        this.zacp = zabw2.zajx;
        this.zacq = zabw2.zajy;
    }

    public final void zad(GoogleApiManager.zaa<?> zaa) throws RemoteException {
        Object obj;
        GoogleApiManager.zaa<?> zaa2 = zaa;
        this.zacp.registerListener(zaa2.zaab(), this.zacn);
        if (this.zacp.getListenerKey() != null) {
            new zabw(this.zacp, this.zacq);
            zabw put = zaa2.zabk().put(this.zacp.getListenerKey(), obj);
        }
    }

    @Nullable
    public final Feature[] zab(GoogleApiManager.zaa<?> zaa) {
        GoogleApiManager.zaa<?> zaa2 = zaa;
        return this.zacp.getRequiredFeatures();
    }

    public final boolean zac(GoogleApiManager.zaa<?> zaa) {
        GoogleApiManager.zaa<?> zaa2 = zaa;
        return this.zacp.shouldAutoResolveMissingFeatures();
    }

    public final /* bridge */ /* synthetic */ void zaa(@NonNull zaab zaab, boolean z) {
    }

    public final /* bridge */ /* synthetic */ void zaa(@NonNull RuntimeException runtimeException) {
        super.zaa(runtimeException);
    }

    public final /* bridge */ /* synthetic */ void zaa(@NonNull Status status) {
        super.zaa(status);
    }
}
