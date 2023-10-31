package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zah extends zad<Boolean> {
    private final ListenerHolder.ListenerKey<?> zact;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zah(ListenerHolder.ListenerKey<?> listenerKey, TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zact = listenerKey;
    }

    public final void zad(GoogleApiManager.zaa<?> zaa) throws RemoteException {
        GoogleApiManager.zaa<?> zaa2 = zaa;
        zabw remove = zaa2.zabk().remove(this.zact);
        zabw zabw = remove;
        if (remove != null) {
            zabw.zajy.unregisterListener(zaa2.zaab(), this.zacn);
            zabw.zajx.clearListener();
            return;
        }
        boolean trySetResult = this.zacn.trySetResult(false);
    }

    @Nullable
    public final Feature[] zab(GoogleApiManager.zaa<?> zaa) {
        zabw zabw = zaa.zabk().get(this.zact);
        zabw zabw2 = zabw;
        if (zabw == null) {
            return null;
        }
        return zabw2.zajx.getRequiredFeatures();
    }

    public final boolean zac(GoogleApiManager.zaa<?> zaa) {
        zabw zabw = zaa.zabk().get(this.zact);
        return zabw != null && zabw.zajx.shouldAutoResolveMissingFeatures();
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
